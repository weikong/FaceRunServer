package com.facerun.util;

public enum Code {

    FAIL(-1, "error.unknown"),
    FORBIDDEN(-2, "error.forbidden"),
    EMPTY_USER(-3, "error.empty_user"),
    DATA_AUTH_ERROR(-4, "error.data_auth_error"),
    DATA_ERROR(-5, "error.data_error"),
    USER_NOT_EXIST(-6, "user.not.exist"),
    USER_EXIST(-7, "user.exist"),

    FILE_EMPTY(-4001, "File is empty"),
    FILE_UPLOAD_FAIL(-4002, "File upload fail"),

    FAIL_DATABASE_INSERT(-1001, "error.database.insert"),
    FAIL_DATABASE_UPDATE(-1002, "error.database.update"),
    FAIL_DATABASE_DEL(-1003, "error.database.del"),
    FAIL_DATABASE_QUERY(-1004, "error.database.query"),
    FAIL_DATABASE(-1005, "error.database"),
    PARAMS_MISS(-1006, "params.miss");

    private int code;
    private String key;

    Code(int code, String key) {
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
