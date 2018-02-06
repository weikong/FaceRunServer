package com.facerun.service.pet;

import com.facerun.bean.PetDating;
import com.facerun.bean.PetDatingExample;
import com.facerun.bean.PetLookfor;
import com.facerun.bean.PetService;
import com.facerun.dao.CustPetDatingMapper;
import com.facerun.dao.PetDatingMapper;
import com.facerun.dao.PetServiceMapper;
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
public class PetDatingService {
    private static final Logger log = LoggerFactory.getLogger(PetDatingService.class);

    @Autowired
    private PetDatingMapper petDatingMapper;
    @Autowired
    private CustPetDatingMapper custPetDatingMapper;

    public List<PetDating> queryAllDating() {
        PetDatingExample example = new PetDatingExample();
        example.createCriteria();
        example.setOrderByClause("time desc");
        List<PetDating> list = petDatingMapper.selectByExample(example);
        return list;
    }


    public List<PetDating> queryDating(Map params) {
        Map paramsWrapper = new HashMap();
        int pageSize = Integer.valueOf(params.get("pageSize") == null ? "20" : params.get("pageSize").toString());
        int pageNum = Integer.valueOf(params.get("pageNum") == null ? "1" : params.get("pageNum").toString());
        int beginNum = pageSize * (pageNum - 1);
        paramsWrapper.put("beginNum", beginNum);
        paramsWrapper.put("limitSize", pageSize);
        List<PetDating> list = custPetDatingMapper.getDatingList(paramsWrapper);
        return list;
    }
}
