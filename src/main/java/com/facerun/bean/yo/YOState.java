package com.facerun.bean.yo;

public enum YOState {

    OnSale(1, "在售"),
    SellOut(2, "售罄"),
    LowerShelf(3, "下架");

    private int code;
    private String key;

    YOState(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }

}
