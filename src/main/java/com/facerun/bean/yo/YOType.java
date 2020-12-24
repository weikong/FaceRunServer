package com.facerun.bean.yo;

public enum YOType {

    Pig(1, "猪肉类"),
    Duck(2, "鸡鸭类"),
    Vegetable(3, "素菜类");

    private int code;
    private String key;

    YOType(int code, String key) {
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
