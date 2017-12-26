package com.facerun.bean;

import java.io.Serializable;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class FileUploadImageBean extends AbsDomain implements Serializable {

    private String fileName;
    private String suffix;
    private String fileNameMD5;
    private String fileNameThumb;

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

    public String getFileNameMD5() {
        return fileNameMD5;
    }

    public void setFileNameMD5(String fileNameMD5) {
        this.fileNameMD5 = fileNameMD5;
    }

    public String getFileNameThumb() {
        return fileNameThumb;
    }

    public void setFileNameThumb(String fileNameThumb) {
        this.fileNameThumb = fileNameThumb;
    }
}
