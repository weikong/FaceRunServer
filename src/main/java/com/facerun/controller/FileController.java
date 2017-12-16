package com.facerun.controller;

import com.facerun.util.Config;
import com.facerun.util.FileUtil;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/7/14.
 */
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
        File uploadFilePath = new File(filePath);
        if (!uploadFilePath.exists())
            uploadFilePath.mkdirs();
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.info("上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("上传失败");
        }
        return "上传失败";
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public void downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) {
        String fileName = "logo.png";
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");
            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            File file = new File(filePath, fileName);
            FileUtil.downloadFile(file, fileName, response);
        }
        return;
    }

    //文件下载相关代码
    @RequestMapping("/download_file")
    public void downloadFile(@RequestParam Map map, HttpServletResponse response) {
        String fileName = MapUtils.getString(map, "filename", "");
        if (fileName != null) {
            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            File file = new File(filePath, fileName);
            FileUtil.downloadFile(file, fileName, response);
        }
        return;
    }

    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;

        // 文件上传后的路径
        String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
        File uploadFilePath = new File(filePath);
        if (!uploadFilePath.exists())
            uploadFilePath.mkdirs();

        StringBuffer resultMsg = new StringBuffer();

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                String fileName = "";
                try {
                    // 解决中文问题，liunx下中文路径，图片显示问题
                    // fileName = UUID.randomUUID() + suffixName;
                    fileName = file.getOriginalFilename();
                    File dest = new File(filePath + fileName);
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(dest);
                        logger.info("上传成功");
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        logger.info("上传失败");
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.info("上传失败");
                    }
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(file.getOriginalFilename())));
//                    stream.write(bytes);
//                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    resultMsg.append(fileName + " 上传失败");
                }
            } else {
                resultMsg.append(i + " because the file was empty.");
            }
        }
        if (resultMsg.length() == 0)
            return "上传成功";
        else
            return resultMsg.toString();
    }
}
