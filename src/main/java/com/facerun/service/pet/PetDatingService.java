package com.facerun.service.pet;

import com.facerun.bean.PetDating;
import com.facerun.bean.PetDatingExample;
import com.facerun.bean.PetService;
import com.facerun.dao.PetDatingMapper;
import com.facerun.dao.PetServiceMapper;
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
public class PetDatingService {
    private static final Logger log = LoggerFactory.getLogger(PetDatingService.class);

    @Autowired
    private PetDatingMapper petDatingMapper;

    public List<PetDating> queryAllDating() {
        PetDatingExample example = new PetDatingExample();
        example.createCriteria();
        example.setOrderByClause("time desc");
        List<PetDating> list = petDatingMapper.selectByExample(example);
        return list;
    }
}
