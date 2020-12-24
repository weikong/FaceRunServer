package com.facerun.controller;


import com.facerun.bean.*;
import com.facerun.config.Constant;
import com.facerun.dao.FoodMapper;
import com.facerun.dao.MediaSrcMapper;
import com.facerun.dao.RunMapper;
import com.facerun.exception.BizException;
import com.facerun.service.order.OrderService;
import com.facerun.util.Code;
import com.facerun.util.HttpUtil;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关操作
 */
@RequestMapping("test_rest")
@RestController
public class TestRestController extends AbsController {

    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);

    @Autowired
    private RunMapper runMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private OrderService orderService;

    @Autowired
    private MediaSrcMapper mediaSrcMapper;

    String parentDir = "gif\\res";

    /**
     */
    @PostMapping("gifZipFiles")
    @ResponseBody
    public Object gifZipFiles(@RequestParam Map params, Model model) {
        try {
            List<ResGifBean> list = new ArrayList<>();
            String domainPath = Constant.HTTP_ROOT;
            File gifDir = new File(Constant.RES_GIF_PATH + "res");
            if (gifDir.exists()) {
                File[] gifChildDirs = gifDir.listFiles();
                for (File gifChild : gifChildDirs){
                    ResGifBean resGifBean = new ResGifBean();
                    String name = gifChild.getName();
                    resGifBean.setName(name);
                    String urlPath = domainPath + "/gif/zip/" + name +".zip";
                    resGifBean.setZip(urlPath);
                    MediaSrcExample example = new MediaSrcExample();
                    example.createCriteria().andMedianameEqualTo(name);
                    List<MediaSrc> mediaSrcList = mediaSrcMapper.selectByExample(example);
                    resGifBean.setList(mediaSrcList);
                    if (mediaSrcList != null && mediaSrcList.size() > 0)
                        resGifBean.setThumb(mediaSrcList.get(0).getMediapath());
                    list.add(resGifBean);
                }
            }
            return ajax(list);
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     */
    @GetMapping("gif")
    @ResponseBody
    public Object gif(@RequestParam Map params, Model model) {
        try {
            List<ResGifBean> zipGifList = new ArrayList<>();
            String domainPath = Constant.HTTP_ROOT;
            File rootDir = new File(Constant.UPLOAD_FILE_PATH);
            File gifDir = new File(Constant.RES_GIF_PATH + "res");
            if (gifDir.exists()) {
                File[] gifChildDirs = gifDir.listFiles();
                if (gifChildDirs != null && gifChildDirs.length > 0) {
                    for (File childDir : gifChildDirs) {
                        if (childDir.isDirectory()) {
                            String name = childDir.getName();
                            ResGifBean zipBean = new ResGifBean();
                            zipBean.setName(name);
                            zipBean.setZip(Constant.RES_GIF_PATH+"zip");
                            File[] gifFiles = childDir.listFiles();
                            for (File file : gifFiles) {
                                if (file.exists() && file.length() > 0) {
                                    String path = file.getAbsolutePath();
                                    String urlPath = path.replace(rootDir.getAbsolutePath(), "").replace("\\", "/");
                                    urlPath = domainPath + urlPath;
                                    MediaSrc mediaSrc = new MediaSrc();
                                    mediaSrc.setMedianame(name);
                                    mediaSrc.setMediapath(urlPath);
                                    mediaSrc.setMediatype("gif");
                                    mediaSrcMapper.insert(mediaSrc);
                                }
                            }
                        }
                    }
                }
            }
            return ajax();
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    @RequestMapping(value = "/query_order", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object queryOrder(@RequestParam Map params, Model model) {
        try {
            return ajax(orderService.queryList(params));
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object add(@RequestParam Map params, Model model) {
        try {
            Object o = orderService.createOrder(params);
            try {
                orderService.queryOrdersList(params);
            } catch (Exception e) {

            }
            return ajax(o);
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    @RequestMapping(value = "/del", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object del(@RequestParam Map params, Model model) {
        try {
            Object o = orderService.delOrderById(params);
            try {
                orderService.queryOrdersList(params);
            } catch (Exception e) {

            }
            return ajax(o);
        } catch (BizException e) {
            return ajax(e);
        } catch (Exception e) {
            return ajax(Code.FAIL);
        }
    }

    /**
     */
    @GetMapping("test1")
    @ResponseBody
    public String test1(@RequestParam Map params, Model model) {
        String param = MapUtils.getString(params, "param", "");
        return "test1 param = " + param;
    }

    /**
     */
    @PostMapping("test2")
    @ResponseBody
    public String test2(@RequestParam Map params, Model model) {
        String param = MapUtils.getString(params, "param", "");
        return "test2 param = " + param;
    }

    /**
     * 用户数据
     */
    @GetMapping("run_datas1")
    public String runDatas(@RequestParam Map params, Model model) {
//        List<Run> list = runMapper.selectByExample(null);
//        return list.toString();
        String strJson = "{\"body\"\n" +
                ":\n" +
                "\"儿科门诊2018-07-30张丹医生\",\n" +
                "\"nonce_str\"\n" +
                ":\n" +
                "\"ffee8acfb95e40db928d6ca4dd875551\",\n" +
                "\"notify_url\"\n" +
                ":\n" +
                "\"https://wechat.vipsuns.com/payStatus\",\n" +
                "\"out_trade_no\"\n" +
                ":\n" +
                "\"216468798641520\",\n" +
                "\"spbill_create_ip\"\n" +
                ":\n" +
                "\"176.2.1.131\",\n" +
                "\"totalFee\"\n" +
                ":\n" +
                "1,\n" +
                "\"trade_type\"\n" +
                ":\n" +
                "\"JSAPI\"}";
        String url = "http://172.16.0.173:11080/yaeyy/weixin/pay/unifiedOrderGetRequest";
        url += "?body=" + "儿科门诊2018-07-30张丹医生";
        url += "&nonce_str=" + "ffee8acfb95e40db928d6ca4dd875551";
        url += "&notify_url=" + "https://wechat.vipsuns.com/payStatus";
        url += "&out_trade_no=" + "216468798641520";
        url += "&spbill_create_ip=" + "176.2.1.131";
        url += "&totalFee=" + "1";
        url += "&trade_type=" + "JSAPI";
//        url += "?"+strJson;
        String response = HttpUtil.sendGet(url);
        return response;
    }

    /**
     * 用户数据
     */
    @PostMapping("run_datas2")
    @ResponseBody
    public String runDatas2(@RequestParam Map params, Model model) {
//        List<Run> list = runMapper.selectByExample(null);
//        return list.toString();
        return "Hello world!Mr kong 3";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/food_detail")
    public ModelAndView wechatUser(HttpServletRequest request,
                                   HttpServletResponse response, @RequestParam Map params) throws Exception {
        int id = MapUtils.getInteger(params, "id");
        FoodExample example = new FoodExample();
        if (id > 0)
            example.createCriteria().andIdEqualTo(id);
        Food food = foodMapper.selectByPrimaryKey(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("food", food);
        return new ModelAndView("food_detail", data);
    }

    /**
     * 用户数据
     */
    @PostMapping("run_datas3")
    @ResponseBody
    public String runDatas3(@RequestBody String params, Model model) {
        return "Hello world!Mr kong 3 = " + params;
    }
}
