package com.facerun.util.translate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by maesinfo on 2018/9/5.
 */
public class GoogleApi {
    private static final String PATH = "E:\\workspace_server\\keep\\FaceRunServer\\src\\main\\java\\com\\facerun\\util\\translate" + File.separator + "gettk.js";

    static ScriptEngine engine = null;

//    private Browser browser = null;

    static {
        ScriptEngineManager maneger = new ScriptEngineManager();
        engine = maneger.getEngineByName("javascript");
        FileInputStream fileInputStream = null;
        Reader scriptReader = null;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(PATH));
            scriptReader = new InputStreamReader(is, "utf-8");
//            scriptReader = new InputStreamReader(GoogleApi.class.getResourceAsStream(PATH), "utf-8");
            engine.eval(scriptReader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scriptReader != null) {
                try {
                    scriptReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public GoogleApi(){
//        this.browser = new Browser();
//    }
//
//    public GoogleApi(String ip, Integer port){
//        this.browser = new Browser();
//        this.browser.setProxy(ip, port);
//    }

    public static String getTKK() {
//        browser.setUrl("https://translate.google.cn/");
        try {
//            String result = browser.executeGet();
            String result = sendGet("https://translate.google.cn/");
            if (StringUtils.isNotBlank(result)) {
                if (result.indexOf("TKK") > -1) {
                    String tkk = result.split("TKK")[1];
                    tkk = tkk.split("\\)\\;")[0];
                    tkk = tkk + ");";
                    tkk = tkk.substring(1, tkk.length());
                    ScriptEngineManager manager = new ScriptEngineManager();
                    ScriptEngine engine = manager.getEngineByName("javascript");
                    return (String) engine.eval(tkk);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTK(String word, String tkk) {
        String result = null;
        try {
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                result = (String) invocable.invokeFunction("tk", new Object[]{word, tkk});
//                result = (String) invocable.invokeFunction("tk", URLEncoder.encode(word), tkk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String translate(String word, String from, String to) {
        if (StringUtils.isBlank(word)) {
            return null;
        }
        String _tkk = getTKK();
        if (StringUtils.isBlank(_tkk)) {
            return null;
        }
        String _tk = getTK(word, _tkk);
        try {
            word = URLEncoder.encode(word, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer("https://translate.google.cn/translate_a/single?client=t");
        buffer.append("&sl=" + from);
        buffer.append("&tl=" + to);
//        buffer.append("&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&source=btn&kc=0");
        buffer.append("&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&source=btn&tsel=0&kc=0");
        buffer.append("&tk=" + _tk);
        buffer.append("&q=" + word);
//        browser.setUrl(buffer.toString());
        try {
//            String result = browser.executeGet();
            String result = sendGet(buffer.toString());
            JSONArray array = (JSONArray) JSONArray.parse(result);
            JSONArray r_array = array.getJSONArray(0);
            StringBuffer r_buffer = new StringBuffer();
            for (int i = 0; i < r_array.size(); i++) {
                String _r = r_array.getJSONArray(i).getString(0);
                if (StringUtils.isNotBlank(_r)) {
                    r_buffer.append(_r);
                }
            }
            return r_buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(5000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*GoogleApi googleApi = new GoogleApi();*/
//        GoogleApi googleApi = new GoogleApi("122.224.227.202", 3128);
//        GoogleApi googleApi = new GoogleApi();
        String result = GoogleApi.translate("你好", "", "jp");
        System.out.println(result);
    }
}
