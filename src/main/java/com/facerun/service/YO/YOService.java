package com.facerun.service.YO;

import com.facerun.bean.YOMeat;
import com.facerun.bean.YOMeatExample;
import com.facerun.dao.YOMeatMapper;
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
public class YOService {
    private static final Logger log = LoggerFactory.getLogger(YOService.class);

    @Autowired
    private YOMeatMapper yoMeatMapper;

    public List<YOMeat> queryAll(Map params) {
        List<YOMeat> list = yoMeatMapper.selectByExample(null);
        return list;
    }

    public List<YOMeat> queryByType(Map params) {
        int type = MapUtils.getInteger(params, "type");
        YOMeatExample example = null;
        if (type > 0) {
            example = new YOMeatExample();
            example.createCriteria().andTypeEqualTo(type);
        }
        List<YOMeat> list = yoMeatMapper.selectByExample(example);
        return list;
    }
}
