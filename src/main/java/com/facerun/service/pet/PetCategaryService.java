package com.facerun.service.pet;

import com.alibaba.fastjson.JSONObject;
import com.facerun.bean.Account;
import com.facerun.bean.PetCategary;
import com.facerun.bean.PetCategaryExample;
import com.facerun.bean.PetProduct;
import com.facerun.dao.CustPetProductMapper;
import com.facerun.dao.PetCategaryMapper;
import com.facerun.dao.PetProductMapper;
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
public class PetCategaryService {
    private static final Logger log = LoggerFactory.getLogger(PetCategaryService.class);

    @Autowired
    private PetCategaryMapper petCategaryMapper;

    public List<PetCategary> queryAll() {
        List<PetCategary> list = petCategaryMapper.selectByExample(null);
        return list;
    }
}
