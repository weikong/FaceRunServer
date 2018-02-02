package com.facerun.service.pet;

import com.facerun.bean.PetCategary;
import com.facerun.bean.PetService;
import com.facerun.bean.PetServiceExample;
import com.facerun.dao.PetCategaryMapper;
import com.facerun.dao.PetServiceMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class PetServiceService {
    private static final Logger log = LoggerFactory.getLogger(PetServiceService.class);

    @Autowired
    private PetServiceMapper petServiceMapper;

    public List<PetService> queryAllService() {
        List<PetService> list = petServiceMapper.selectByExample(null);
        return list;
    }

    public List<PetService> queryServiceType(Map params) {
        String type = MapUtils.getString(params,"type");
        PetServiceExample example = new PetServiceExample();
        example.createCriteria().andTypeEqualTo(type);
        List<PetService> list = petServiceMapper.selectByExample(example);
        return list;
    }
}
