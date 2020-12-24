//package com.facerun.util.dcm4che3;
//
//import ij.plugin.DICOM;
//import org.dcm4che3.data.Attributes;
//import org.dcm4che3.data.UID;
//import org.dcm4che3.imageio.codec.Transcoder;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.Files;
//
///**
// * Created by maesinfo on 2020/11/27.
// */
//public class Dcm4cheTranscode {
//
//    public static void main(String[] args) {
////        System.loadLibrary("opencv_java");
//        String srcFile = "D:\\pcm\\168.dcm";
//        String destFile = "D:\\pcm\\168Test.dcm";
//        File src = new File(srcFile); //带有压缩协议的dicom原始文件
//        File dest = new File(destFile); //解压成未压缩的dicom目标文件
//        try {
//            transcodeWithTranscoder(src, dest);
//            createImage(destFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 解压Dicom文件，并输出生成未压缩的dicom目标文件
//     * */
//    public static void transcodeWithTranscoder(final File src, final File dest) throws IOException {
//        try (Transcoder transcoder = new Transcoder(src)) {
//            transcoder.setDestinationTransferSyntax(UID.ExplicitVRLittleEndian);
//            transcoder.transcode(new Transcoder.Handler() {
//                @Override
//                public OutputStream newOutputStream(Transcoder transcoder, Attributes dataset) throws IOException {
//                    return new FileOutputStream(dest);
//                }
//            });
//        } catch (Exception e) {
//            Files.deleteIfExists(dest.toPath());
//            throw e;
//        }
//    }
//
//    /**
//     * 输入一个dicom文件的绝对路径和名字
//     * 获取一个jpg文件
//     */
//    private static void createImage(String filePath) {
//        try {
//            DICOM dicom = new DICOM();
//            dicom.run(filePath);
//            BufferedImage bi = (BufferedImage) dicom.getImage();
////            int width = bi.getWidth();
////            int height = dicom.getHeight();
////            System.out.println("width: " + width + "\n" + "height: " + height);
//            String imagePath = filePath + ".jpg";
//            ImageIO.write(bi, "jpg", new File(imagePath));
////            System.out.println("Hehe,Game over!!!");
//        } catch (Exception e) {
//            System.out.println("错误" + e.getMessage());
//        }
//    }
//}
