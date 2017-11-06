package com.facerun.bean;

import java.util.HashMap;

public class Record extends HashMap {

    @Override
    public Object put(Object key, Object value) {
        if (key == null) {
            return super.put(key, value);
        }
        return super.put(key.toString().toLowerCase(), value);
    }

}
