package com.facerun.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xinzhendi-031 on 2017/8/24.
 */
public class FileUtil {

    public static void downloadFile(File file, String fileName, HttpServletResponse response) {
        if (file.exists()) {
            //response.setContentType("application/force-download");// 设置强制下载不打开
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.addHeader("Content-type","application/txt");
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getNetInfo(String strUrl) {
        StringBuffer info = new StringBuffer();
        URL url = null;//要调用的url
        try {
            url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");//设置get方式获取数据
            if (conn.getResponseCode() == 200) {//如果连接成功
                BufferedReader br = new BufferedReader(new InputStreamReader(conn
                        .getInputStream()));//创建流
                String lines = null;
                while ((lines = br.readLine()) != null) {
                    info.append(lines);
                }
                br.close();
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info.toString();
    }
//
//    /**
//     * 获取图片宽度
//     *
//     * @param file 图片文件
//     * @return 宽度
//     */
//    public static int[] getImgWidth(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int result[] = {0, 0};
//        try {
//            is = new FileInputStream(file);
//            src = javax.imageio.ImageIO.read(is);
//            result[0] = src.getWidth(null); // 得到源图宽
//            result[1] = src.getHeight(null); // 得到源图高
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 采用指定宽度、高度 的方式对图片进行压缩
//     *
//     * @param imgsrc     源图片地址
//     * @param imgdist    目标图片地址
//     * @param widthdist  压缩后图片宽度（当rate==null时，必传）
//     * @param heightdist 压缩后图片高度（当rate==null时，必传）
//     */
//    public static void reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist) {
//        try {
//            File srcfile = new File(imgsrc);
//            // 检查文件是否存在
//            if (!srcfile.exists() || srcfile.length() <= 0)
//                return;
//            int[] results = getImgWidth(srcfile);
//            // 开始读取文件并进行压缩
//            Image src = javax.imageio.ImageIO.read(srcfile);
//            BufferedImage tag = new BufferedImage((int) widthdist,
//                    (int) heightdist, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(
//                    src.getScaledInstance(widthdist, heightdist,
//                            Image.SCALE_SMOOTH), 0, 0, null);
//            FileOutputStream out = new FileOutputStream(imgdist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 采用压缩比例 的方式对图片进行压缩
//     *
//     * @param imgsrc  源图片地址
//     * @param imgdist 目标图片地址
//     */
//    public static void reduceImg(String imgsrc, String imgdist) {
//        try {
//            int mWH = 400;
//            Float rate = 0f;
//            int widthdist = 0;
//            int heightdist = 0;
//            File srcfile = new File(imgsrc);
//            // 检查文件是否存在
//            if (!srcfile.exists() || srcfile.length() <= 0) {
//                return;
//            }
//            int[] results = getImgWidth(srcfile);
//            // 如果rate不为空说明是按比例压缩
//            // 获取文件高度和宽度
//            if (results == null || results[0] == 0 || results[1] == 0) {
//                return;
//            }
//            int minWH = Math.min(results[0], results[1]);
//            int maxWH = Math.max(results[0], results[1]);
//            if (maxWH > mWH) {
//                rate = mWH * 1.0f / maxWH;
//                widthdist = (int) (results[0] * rate);
//                heightdist = (int) (results[1] * rate);
//            }
//            // 开始读取文件并进行压缩
//            Image src = javax.imageio.ImageIO.read(srcfile);
//            BufferedImage tag = new BufferedImage((int) widthdist,
//                    (int) heightdist, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(
//                    src.getScaledInstance(widthdist, heightdist,
//                            Image.SCALE_SMOOTH), 0, 0, null);
//            FileOutputStream out = new FileOutputStream(imgdist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 采用压缩比例 的方式对图片进行压缩
//     *
//     * @param imgsrc  源图片地址
//     * @param imgdist 目标图片地址
//     * @param rate    压缩比例
//     */
//    public static void reduceImg(String imgsrc, String imgdist, Float rate) {
//        try {
//            int widthdist = 0;
//            int heightdist = 0;
//            File srcfile = new File(imgsrc);
//            // 检查文件是否存在
//            if (!srcfile.exists() || srcfile.length() <= 0) {
//                return;
//            }
//            int[] results = getImgWidth(srcfile);
//            // 如果rate不为空说明是按比例压缩
//            if (rate != null && rate > 0) {
//                // 获取文件高度和宽度
//
//                if (results == null || results[0] == 0 || results[1] == 0) {
//                    return;
//                } else {
//                    widthdist = (int) (results[0] * rate);
//                    heightdist = (int) (results[1] * rate);
//                }
//            }
//            // 开始读取文件并进行压缩
//            Image src = javax.imageio.ImageIO.read(srcfile);
//            BufferedImage tag = new BufferedImage((int) widthdist,
//                    (int) heightdist, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(
//                    src.getScaledInstance(widthdist, heightdist,
//                            Image.SCALE_SMOOTH), 0, 0, null);
//            FileOutputStream out = new FileOutputStream(imgdist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
//     *
//     * @param imgsrc     源图片地址
//     * @param imgdist    目标图片地址
//     * @param widthdist  压缩后图片宽度（当rate==null时，必传）
//     * @param heightdist 压缩后图片高度（当rate==null时，必传）
//     * @param rate       压缩比例
//     */
//    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
//                                 int heightdist, Float rate) {
//        try {
//            File srcfile = new File(imgsrc);
//            // 检查文件是否存在
//            if (!srcfile.exists()) {
//                return;
//            }
//            // 如果rate不为空说明是按比例压缩
//            if (rate != null && rate > 0) {
//                // 获取文件高度和宽度
//                int[] results = getImgWidth(srcfile);
//                if (results == null || results[0] == 0 || results[1] == 0) {
//                    return;
//                } else {
//                    widthdist = (int) (results[0] * rate);
//                    heightdist = (int) (results[1] * rate);
//                }
//            }
//            // 开始读取文件并进行压缩
//            Image src = javax.imageio.ImageIO.read(srcfile);
//            BufferedImage tag = new BufferedImage((int) widthdist,
//                    (int) heightdist, BufferedImage.TYPE_INT_RGB);
//
//            tag.getGraphics().drawImage(
//                    src.getScaledInstance(widthdist, heightdist,
//                            Image.SCALE_SMOOTH), 0, 0, null);
//
//            FileOutputStream out = new FileOutputStream(imgdist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    //按指定大小把图片进行缩和放（会遵循原图高宽比例）
    //此处把图片压成400×400的缩略图
    public static void cropImage(String fromPic, String toPic) {
        try {
            File srcfile = new File(fromPic);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            Thumbnails.of(fromPic).size(400, 400).toFile(toPic);//变为400*300,遵循原图比例缩或放到400*某个高度
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //按指定大小把图片进行缩和放（会遵循原图高宽比例）
    //此处把图片压成400×400的缩略图
    public static void cropImage(String fromPic, String toPic, int max) {
        try {
            File srcfile = new File(fromPic);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            Thumbnails.of(fromPic).size(max, max).toFile(toPic);//变为max*max,遵循原图比例缩或放到max*某个高度
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //按照比例进行缩小和放大
    public static void scaleImage(String fromPic, String toPic) {
        try {
            File srcfile = new File(fromPic);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            Thumbnails.of(fromPic).scale(0.2f).toFile(toPic);//按比例缩小
//            Thumbnails.of(fromPic).scale(2f);//按比例放大
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
    public static void scaleQutityImage(String fromPic, String toPic) {
        try {
            File srcfile = new File(fromPic);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
