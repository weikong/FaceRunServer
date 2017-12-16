package com.facerun.util;

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

    public static String getNetInfo(String strUrl){
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
}
