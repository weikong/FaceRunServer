package com.facerun.util;

/**
 * Created by maesinfo on 2019/8/23.
 */

import com.facerun.config.Constant;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HONGLINCHEN
 * @Description:获取视频宽高大小时间
 * @Date: 2017-9-29 14:02
 */

public class ReadVideo {

    public static String image_type = "jpg";

    public static void main(String[] args) {
        try {
//            fetchFrame(Constant.HTTP_ROOT + "4a0a1c62-b1ad-4956-9ffc-6911b868ba94.mp4", Constant.UPLOAD_FILE_PATH + "videoThumb.jpg");
//            fetchFrame(Constant.UPLOAD_FILE_PATH + "4a0a1c62-b1ad-4956-9ffc-6911b868ba94.mp4", Constant.UPLOAD_FILE_PATH + "videoThumb.jpg");
//            getScreenshot(Constant.UPLOAD_FILE_PATH + "4a0a1c62-b1ad-4956-9ffc-6911b868ba94.mp4","4a0a1c62-b1ad-4956-9ffc-6911b868ba94.jpg");
            getScreenshot(Constant.UPLOAD_FILE_PATH + "4e736d29-1b85-453a-93af-102f7d75fc4b.mp4","4e736d29-1b85-453a-93af-102f7d75fc4b.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 获取视频截图
     * @throws IOException  void
     */
    public static Map<String, Object> getScreenshot(String filePath,String imageName) throws Exception{

        System.out.println("截取视频截图开始："+ System.currentTimeMillis());
        Map<String, Object> result = new HashMap<String, Object>();
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(filePath);

        // 第一帧图片存储位置
//        String targerFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
        String targerFilePath = Constant.UPLOAD_FILE_PATH;
        // 视频文件名
//        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        // 图片名称
//        String targetFileName = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println("视频路径是：" + targerFilePath);
        System.out.println("视频文件名：" + filePath);
        System.out.println("图片名称是：" + imageName);

        grabber.start();
        //设置视频截取帧（默认取第一帧）
        Frame frame = grabber.grabImage();
        //视频旋转度
        String rotate = grabber.getVideoMetadata("rotate");
        Java2DFrameConverter converter = new Java2DFrameConverter();
        //绘制图片
        BufferedImage bi = converter.getBufferedImage(frame);
        if (rotate != null) {
            // 旋转图片
            bi = rotate(bi, Integer.parseInt(rotate));
        }
        //图片的类型
        String imageMat = "jpg";
        //图片的完整路径
        String imagePath = targerFilePath + File.separator + imageName;
        //创建文件
        File output = new File(imagePath);
        ImageIO.write(bi, imageMat, output);
        grabber.stop();
//        //拼接Map信息
//        result.put("videoWide", bi.getWidth());
//        result.put("videoHigh", bi.getHeight());
//        long duration = grabber.getLengthInTime() / (1000 * 1000);
//        result.put("rotate", StringUtils.isBlank(rotate)? "0" : rotate);
//        result.put("format", grabber.getFormat());
//        result.put("imgPath", output.getPath());
//        System.out.println("视频的宽:" + bi.getWidth());
//        System.out.println("视频的高:" + bi.getHeight());
//        System.out.println("视频的旋转度：" + rotate);
//        System.out.println("视频的格式：" + grabber.getFormat());
//        System.out.println("此视频时长（s/秒）：" + duration);
//        grabber.stop();
//        System.out.println("截取视频截图结束："+ System.currentTimeMillis());
        return result;
    }

    /**
     * @Description: 根据视频旋转度来调整图片
     * @param src
     * @param angel	视频旋转度
     * @return  BufferedImage
     */
    public static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }


    /**
     * @Description: 计算图片旋转大小
     * @param src
     * @param angel
     * @return  Rectangle
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param videofile 源视频文件路径
     * @param framefile 截取帧的图片存放路径
     * @throws Exception
     */
    public static void fetchFrame(String videofile, String framefile)
            throws Exception {
        long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
//        String rotate_old = ff.getVideoMetadata("rotate");//视频旋转角度，可能是null
        int lenght = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            i++;
        }
//        opencv_core.IplImage img = f.image;
//        int width = img.width();
//        int height = img.height();

        // 对截取的帧进行等比例缩放
//        int owidth = f.image.width();
//        int oheight = f.image.height();
        int owidth = f.imageWidth;
        int oheight = f.imageHeight;
        int width = 400;
        int height = (int) (((double) width / owidth) * oheight);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
//                0, 0, null);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage fecthedImage = converter.getBufferedImage(f);
        bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
        ImageIO.write(bi, image_type, targetFile);
        //ff.flush();
        ff.stop();
//        //有需要旋转
//        if (rotate_old != null && !rotate_old.isEmpty()) {
//            int rotate = Integer.parseInt(rotate_old);
//            rotatePhonePhoto(framefile, rotate);
//        }
        System.out.println("ReadVideo fetchFrame time = " + (System.currentTimeMillis() - start));
    }

    /**
     * 旋转照片
     *
     * @return
     */
    public static String rotatePhonePhoto(String fullPath, int angel) {
        BufferedImage src;
        try {
            src = ImageIO.read(new File(fullPath));
            int src_width = src.getWidth(null);
            int src_height = src.getHeight(null);

            int swidth = src_width;
            int sheight = src_height;

            if (angel == 90 || angel == 270) {
                swidth = src_height;
                sheight = src_width;
            }
            Rectangle rect_des = new Rectangle(new Dimension(swidth, sheight));
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = res.createGraphics();
            g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
            g2.drawImage(src, null, null);
            ImageIO.write(res, image_type, new File(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullPath;

    }
}