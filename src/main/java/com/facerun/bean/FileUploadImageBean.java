package com.facerun.bean;

import java.io.Serializable;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class FileUploadImageBean extends AbsDomain implements Serializable {

    private String fileName;
    private String suffix;
    private String filePathMD5;
    private String filePathMD5Thumb;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFilePathMD5() {
        return filePathMD5;
    }

    public void setFilePathMD5(String filePathMD5) {
        this.filePathMD5 = filePathMD5;
    }

    public String getFilePathMD5Thumb() {
        return filePathMD5Thumb;
    }

    public void setFilePathMD5Thumb(String filePathMD5Thumb) {
        this.filePathMD5Thumb = filePathMD5Thumb;
    }
}
