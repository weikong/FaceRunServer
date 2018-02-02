package com.facerun.service.pet;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.Account;
import com.facerun.bean.PetProduct;
import com.facerun.dao.PetProductMapper;
import com.facerun.dao.CustPetProductMapper;
import com.facerun.exception.BizException;
import com.facerun.service.AccountService;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class PetProductService {
    private static final Logger log = LoggerFactory.getLogger(PetProductService.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private PetProductMapper petProductMapper;
    @Autowired
    private CustPetProductMapper custPetProductMapper;

    public List<PetProduct> query(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<PetProduct> list = custPetProductMapper.getProductList(paramsWrapper);
        return list;
    }

    public void insert(Map params) {
        int account_id = MapUtils.getInteger(params, "account_id", 0);
        Account account = accountService.accountSelect(account_id);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        String data = MapUtils.getString(params, "data", "");
        PetProduct petProduct = JSONObject.parseObject(data, PetProduct.class);
        int insert = petProductMapper.insertSelective(petProduct);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }
}
