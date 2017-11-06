package com.facerun.util;

public enum Code {

    FAIL(-1, "error.unknown"),
    FORBIDDEN(-2, "error.forbidden"),
    EMPTY_USER(-3, "error.empty_user"),
    DATA_AUTH_ERROR(-4, "error.data_auth_error"),
    DATA_ERROR(-5, "error.data_error"),

    ORDER_ITEM_IS_EMPTY(-1001, "order.item.is.empty"),
    ORDER_ITEM_EXIST(-1002, "order.item.exist"),
    ORDER_DOMAIN_NOT_FOUND(-1003, "order.domain.not.found"),
    ORDER_DOMAIN_NOT_AVAILABLE(-1004, "order.domain.not.available"),
    ORDER_SALES_NOT_EXIST(-1005, "order.sales.not.exist"),
    ORDER_BUY_IT_NOW_ONLY(-1006, "order.bug.it.now.only"),
    ORDER_YOUR_OFFER_ONLY(-1007, "order.your.offer.only"),
    ORDER_ITEM_ERROR_PRICE(-1008, "order.item.error.price"),
    ORDER_HAS_BEEN_ASSIGNED(-1009, "order.has.been.assigned"),
    ORDER_MAY_BE_ASSIGNED(-1010, "order.may.be.assigned"),

    OFFER_NOT_YOUR_TURN(-1020, "offer.not.your.turn"),
    OFFER_PRICE_INVALID(-1021, "offer.price.invalid"),

    BIN_DOMAIN_NOT_FOUND(-1030, "domain.not.found"),
    BIN_QUOTED_CREATE_PRICE_ERROR(-1031, "BIN price must be greater or equal to EPP Create Price."),

    DOMAIN_NOT_BUY(-1040, "domain.not.buy"),
    TLDS_NOT_IN_INVENTORY(-1041, "tlds.not.in.inventory"),

    CART_EXIST(2000, "cart.error.exist"),
    CART_ITEM_EXIST(-2001, "cart.item.error.exist"),
    CART_ITEM_STRATUS(-2002, "cart.item.error.stratus"),
    CART_DOMAIN_NOT_FOUND(-2003, "cart.domain.not.found"),
    CART_DOMAIN_NOT_AVAILABLE(-2004, "art.domain.not.available"),

    USER_ALREADY_ACTIVATED(-3000, "user.error.activated"),
    USER_NOT_EXIST(-3001, "user.not.exist"),

    FILE_EMPTY(-4001, "File is empty"),
    FILE_UPLOAD_FAIL(-4002, "File upload fail"),

    FAIL_DATABASE(-9998, "error.database"),
    PARAMS_MISS(-9999, "params.miss");

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
