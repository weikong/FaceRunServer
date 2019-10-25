package com.facerun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class ResGifBean extends AbsDomain implements Serializable {

    public String zip;
    public String thumb;
    public String name;
    public List<MediaSrc> list;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MediaSrc> getList() {
        return list;
    }

    public void setList(List<MediaSrc> list) {
        this.list = list;
    }
}
