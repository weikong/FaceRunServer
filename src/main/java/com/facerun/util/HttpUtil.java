package com.facerun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * User: huige
 * Date: 2017/12/4
 * Time: 11:05
 * Description:
 */
public class HttpUtil {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
            .setSocketTimeout(5000).build();

    private static String resultData(CloseableHttpResponse response){
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpGet请求
     * @param url
     *
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

     * @return
     */

    public static String sendGet(String url) {

        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet(url);

            httpget.setConfig(requestConfig);
            response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();//响应实体类
            StringBuilder result = new StringBuilder();//响应正文
            if (entity != null) {
                InputStream instream = entity.getContent();
                byte[] bytes = new byte[4096];
                int size = 0;
                try {
                    while ((size = instream.read(bytes)) > 0) {
                        String str = new String(bytes, 0, size, "utf-8");
                        result.append(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    /**
     * 发送HttpPost请求，参数为map
     * @return
     */
    public static String sendPost(String url) {
        CloseableHttpResponse response = null;
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
            httppost.setConfig(requestConfig);
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String httpRequestToString(String url,
                                             Map<String,String> params) {
        String result = null;
        try {
            InputStream is = httpRequestToStream(url,  params);
            BufferedReader in = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    private static InputStream httpRequestToStream(String url,
                                                   Map<String, String> params) {
        InputStream is = null;
        try {
            String parameters = "";
            boolean hasParams = false;
            for(String key : params.keySet()){
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                parameters += key +"="+ value +"&";
                hasParams = true;
            }
            if(hasParams){
                parameters = parameters.substring(0, parameters.length()-1);
                url += "?"+ parameters;
            }
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setDoInput(true);
            //设置请求方式，默认为GET
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * post请求
     * @param url
     * @param json
     * @return
     */

    public static String doPost(String url, String json){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String result = null;
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}