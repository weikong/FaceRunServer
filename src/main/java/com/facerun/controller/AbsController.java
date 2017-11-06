package com.facerun.controller;

import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsController {

    public ResponseEntity ajax() {
        return ajax(0, null, null);
    }

    public ResponseEntity ajax(Object data) {
        return ajax(0, null, data);
    }

    public ResponseEntity ajax(Code code) {
        return ajax(new BizException(code));
    }

    public ResponseEntity ajax(BizException e) {
        return ajax(e.getCode(), e.getMessage());
    }

    public ResponseEntity ajax(Exception e) {
        return ajax(Code.FAIL);
    }

    public ResponseEntity ajax(int code, String message) {
        return ajax(code, message, null);
    }

    public ResponseEntity ajax(int code, String message, Object data) {
        Map body = new HashMap(3);
        body.put("code", code);
        if (message != null) {
            body.put("message", message);
        }
        if (data != null) {
            body.put("data", data);
        }
        return ResponseEntity.ok(body);
    }

}
