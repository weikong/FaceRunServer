package com.facerun.exception;

import com.alibaba.fastjson.JSON;
import com.facerun.util.Code;
import com.facerun.util.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BizException extends RuntimeException {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BizException(Code code) {
        this(code, null);
    }

    public BizException(Code code, Object[] objects) {
        super();
        this.code = code.getCode();
        String key = code.getKey();
        if (key != null) {
            MessageSource bean = SpringUtil.getBean(MessageSource.class);
            this.message = bean.getMessage(key, objects, LocaleContextHolder.getLocale());
        }
    }

    public BizException(Code code, boolean flag) {
        super();
        this.code = code.getCode();
        String key = code.getKey();
        if (key != null) {
            MessageSource bean = SpringUtil.getBean(MessageSource.class);
            this.message = key;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
