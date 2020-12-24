package com.facerun.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Activity栈管理
 *
 * @author luomin
 */
public class LogUtil {

    private Log log = null;

    private static class LogUtilHolder{
        private static final LogUtil INSTANCE = new LogUtil();
    }

    /**
     * 单一实例
     */
    public static final LogUtil getInstance(){
        return LogUtilHolder.INSTANCE;
    }

    public LogUtil() {
        if (log ==  null){
            log = LogFactory.getLog(LogUtil.class);
        }
    }

    private static int LOG_LEVEL = 6;

    private static final int INFO = 3;
    private static final int ERROR = 1;
    private static final int WARN = 2;
    private static final int DEBUG = 4;

    private static String TAG = "LogUtil";

    /**
     * 不打印tag,自动把TAG合msg打印
     */
    public boolean isPrintTagInInfo = false;

    public void i(String tag, String msg) {
        if (LOG_LEVEL > INFO) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.info(msg);
        }
    }

    public void e(String tag, String msg) {
        if (LOG_LEVEL > ERROR) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.error(msg);
        }
    }

    public void e(String tag, String msg, Exception e) {
        if (LOG_LEVEL > ERROR) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.error(msg, e);
        }
    }

    public void d(String tag, String msg) {
        if (LOG_LEVEL > DEBUG) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.debug(msg);
        }
    }

    public void w(String tag, String msg) {
        if (LOG_LEVEL > WARN) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.warn(msg);
        }
    }

    public void w(String tag, String msg, Exception e) {
        if (LOG_LEVEL > WARN) {
            if (isPrintTagInInfo) {
                msg = "tag:" + tag + ", " + msg;
            }
            log.warn(msg, e);
        }
    }

    public void i(String msg) {
        i(TAG, msg);
    }

    public void e(String msg) {
        e(TAG, msg);
    }

    public void d(String msg) {
        d(TAG, msg);
    }

    public void w(String msg) {
        w(TAG, msg);
    }
}
