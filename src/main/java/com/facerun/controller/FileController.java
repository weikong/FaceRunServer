package com.facerun.controller;

import com.facerun.bean.FileUploadImageBean;
import com.facerun.config.Constant;
import com.facerun.util.Code;
import com.facerun.util.FileUtil;
import com.facerun.util.ReadVideo;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xinzhendi-031 on 2017/7/14.
 */
@RestController
public class FileController extends AbsController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private static final ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    //文件上传相关代码
    @RequestMapping(value = "/upload")
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
//        String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
        String filePath = Constant.UPLOAD_FILE_PATH;
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
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "logo.png";
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
//            String realPath = request.getServletContext().getRealPath(
//                    "//WEB-INF//");
//            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            String filePath = Constant.UPLOAD_FILE_PATH;
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
//            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            String filePath = Constant.UPLOAD_FILE_PATH;
            File file = new File(filePath, fileName);
            if (file.exists() && file.length() > 0)
                FileUtil.downloadFile(file, fileName, response);
        }
        return;
    }

    //文件下载相关代码
    @RequestMapping("/file/{fileName}")
    public void downloadFile(@PathVariable("fileName") String fileName, @RequestParam Map map, HttpServletResponse response) {
//        String fileName = MapUtils.getString(map, "filename", "");
        if (fileName != null) {
//            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            String filePath = Constant.UPLOAD_FILE_PATH;
            File file = new File(filePath, fileName);
            if (file.exists() && file.length() > 0)
                FileUtil.downloadFile(file, fileName, response);
        }
        return;
    }

    //文件下载相关代码
    @RequestMapping("/download_scale_file")
    public void downloadScaleFile(@RequestParam Map map, HttpServletResponse response) {
        String fileName = MapUtils.getString(map, "filename", "");
        if (fileName != null) {
//            String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
            String filePath = Constant.UPLOAD_FILE_PATH;
            File file = new File(filePath, fileName);
            if (file.exists() && file.length() > 0) {
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                String dist = filePath + name + "_thumb" + suffixName;
                File distFile = new File(dist);
                if (distFile != null) {
                    if (!distFile.exists() || distFile.length() <= 0) {
                        FileUtil.cropImage(file.getAbsolutePath(), dist);
                    }
                }
                FileUtil.downloadFile(distFile, name + "_thumb" + suffixName, response);
            }
        }
        return;
    }

    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object handleFileUpload(@RequestParam Map params, HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        if (files != null && files.size() > 0) {
        } else {
            if (files != null) {
                files.clear();
                files = null;
                files = new ArrayList<>();
            }
            MultiValueMap files2 = ((MultipartHttpServletRequest) request).getMultiFileMap();
            Set<String> set = files2.keySet();
            for (Iterator it = set.iterator(); it.hasNext(); ) {
                LinkedList<MultipartFile> list = (LinkedList<MultipartFile>) files2.get(it.next().toString());
                for (MultipartFile file : list) {
                    files.add(file);
                }
            }
        }
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<FileUploadImageBean> fileList = new ArrayList<>();
        // 文件上传后的路径
//        final String filePath = Config.DEFAULT_UPLOAD_FILE_PATH;
        String filePath = Constant.UPLOAD_FILE_PATH;
        StringBuffer resultMsg = new StringBuffer();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                final FileUploadImageBean bean = new FileUploadImageBean();
                String fileName = "";
                try {
                    fileName = file.getOriginalFilename();
                    // 获取文件的后缀名
                    final String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    // 解决中文问题，liunx下中文路径，图片显示问题
                    final String strUUID = UUID.randomUUID().toString();
                    final String mFileName = strUUID + suffixName;
                    final File dest = new File(filePath + mFileName);
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    long t1 = System.currentTimeMillis();
                    file.transferTo(dest);
                    long t2 = System.currentTimeMillis();
                    logger.error("文件上传耗时：" + (t2 - t1));
                    bean.setFileName(fileName);
                    bean.setFilePathMD5(Constant.HTTP_ROOT + mFileName);
                    bean.setSuffix(suffixName);
                    if (!StringUtils.isEmpty(suffixName) && suffixName.toLowerCase().endsWith("mp4")) {
                        bean.setFilePathMD5Thumb(Constant.HTTP_ROOT + strUUID + ".jpg");
                    } else {
//                        bean.setFilePathMD5Thumb(Constant.HTTP_ROOT+strUUID + "_thumb" + suffixName);
                        bean.setFilePathMD5Thumb(Constant.HTTP_ROOT + mFileName);
                    }
                    executors.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                FileUtil.cropImage(dest.getAbsolutePath(), filePath + strUUID + "_thumb" + suffixName);
                                if (!StringUtils.isEmpty(suffixName) && suffixName.toLowerCase().endsWith("mp4")) {
//                                    ReadVideo.fetchFrame2(Constant.UPLOAD_FILE_PATH + mFileName, Constant.UPLOAD_FILE_PATH);
                                    ReadVideo.getScreenshot(dest.getAbsolutePath(),strUUID+".jpg");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    fileList.add(bean);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    logger.info("File upload fail");
                    return ajax(Code.FILE_UPLOAD_FAIL);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("File upload fail");
                    return ajax(Code.FILE_UPLOAD_FAIL);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("File upload fail");
                    return ajax(Code.FILE_UPLOAD_FAIL);
                }
            } else {
                logger.info("File is empty");
                return ajax(Code.FILE_EMPTY);
            }
        }
        if (resultMsg.length() > 0)
            resultMsg.setLength(resultMsg.length() - 1);
        return ajax(fileList);
    }
}
