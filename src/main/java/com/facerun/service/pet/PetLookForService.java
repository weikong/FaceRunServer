package com.facerun.service.pet;

import com.facerun.bean.PetDating;
import com.facerun.bean.PetDatingExample;
import com.facerun.bean.PetLookfor;
import com.facerun.bean.PetLookforExample;
import com.facerun.dao.PetDatingMapper;
import com.facerun.dao.PetLookforMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class PetLookForService {
    private static final Logger log = LoggerFactory.getLogger(PetLookForService.class);

    @Autowired
    private PetLookforMapper petLookforMapper;

    public List<PetLookfor> queryAllLookfor() {
        PetLookforExample example = new PetLookforExample();
        example.createCriteria();
        example.setOrderByClause("time desc");
        List<PetLookfor> list = petLookforMapper.selectByExample(example);
        return list;
    }
}
