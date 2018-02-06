package com.facerun.service.pet;

import com.facerun.bean.*;
import com.facerun.dao.CustPetLookForMapper;
import com.facerun.dao.PetDatingMapper;
import com.facerun.dao.PetLookforMapper;
import com.facerun.exception.BizException;
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
public class PetLookForService {
    private static final Logger log = LoggerFactory.getLogger(PetLookForService.class);

    @Autowired
    private PetLookforMapper petLookforMapper;
    @Autowired
    private CustPetLookForMapper custPetLookForMapper;

    public List<PetLookfor> queryAllLookfor(Map params) {
        PetLookforExample example = new PetLookforExample();
        example.createCriteria();
        example.setOrderByClause("time desc");
        List<PetLookfor> list = petLookforMapper.selectByExample(example);
        return list;
    }

    public List<PetLookfor> queryLookfor(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<PetLookfor> list = custPetLookForMapper.getLookForList(paramsWrapper);
        return list;
    }
}
