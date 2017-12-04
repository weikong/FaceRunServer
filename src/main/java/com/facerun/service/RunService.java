package com.facerun.service;

import com.facerun.bean.Account;
import com.facerun.bean.Run;
import com.facerun.bean.RunExample;
import com.facerun.controller.RunController;
import com.facerun.dao.AccountMapper;
import com.facerun.dao.RunMapper;
import com.facerun.exception.BizException;
import com.facerun.util.Code;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xinzhendi-031 on 2017/12/1.
 */
@Service
@Transactional
public class RunService {
    private static final Logger log = LoggerFactory.getLogger(RunService.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private AccountService accountService;

    public List<Run> runList(Map params){
        int account_id = MapUtils.getInteger(params,"account_id",0);
        RunExample example = new RunExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<Run> list = runMapper.selectByExample(example);
        return list;
    }

    public List<Run> runList(int account_id){
        RunExample example = new RunExample();
        example.createCriteria().andAccountIdEqualTo(account_id);
        List<Run> list = runMapper.selectByExample(example);
        return list;
    }

    public List<Run> runList(String strAccount){
        Account account = accountService.accountSelect(strAccount);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        RunExample example = new RunExample();
        example.createCriteria().andAccountIdEqualTo(account.getId());
        List<Run> list = runMapper.selectByExample(example);
        return list;
    }

    public void runInsert(Map params){
        int account_id = MapUtils.getInteger(params,"account_id",0);
        Account account = accountMapper.selectByPrimaryKey(account_id);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        double longitude = MapUtils.getDouble(params,"longitude",0d);
        double latitude = MapUtils.getDouble(params,"latitude",0d);
        String address = MapUtils.getString(params,"address","");
        String nearby = MapUtils.getString(params,"nearby","");
        int continue_days = MapUtils.getInteger(params,"continue_days",0);
        Long distance = MapUtils.getLong(params,"distance",0L);
        double speed = MapUtils.getDouble(params,"speed",0d);
        String use_time = MapUtils.getString(params,"use_time","");
        String heat = MapUtils.getString(params,"heat","");
        String coordinate_list = MapUtils.getString(params,"coordinate_list","");
        String cover = MapUtils.getString(params,"cover","");
        String city = MapUtils.getString(params,"city","");
        String district = MapUtils.getString(params,"district","");
        Date create_at = (Date) MapUtils.getObject(params,"create_at",new Date());
        Run run = new Run();
        run.setAccountId(account_id);
        run.setLongitude(longitude);
        run.setLatitude(latitude);
        run.setAddress(address);
        run.setNearby(nearby);
        run.setContinueDays(continue_days);
        run.setDistance(distance);
        run.setSpeed(speed);
        run.setUseTime(use_time);
        run.setHeat(heat);
        run.setCoordinateList(coordinate_list);
        run.setCover(cover);
        run.setCity(city);
        run.setDistrict(district);
        run.setCreateAt(create_at);
        int insert = runMapper.insertSelective(run);
        if (insert != 1)
            throw new BizException(Code.FAIL_DATABASE_INSERT);
    }

    public void runInsertBatch(Map params){
        int account_id = MapUtils.getInteger(params,"account_id",0);
        Account account = accountMapper.selectByPrimaryKey(account_id);
        if (account == null)
            throw new BizException(Code.USER_NOT_EXIST);
        List<Run> list = (List<Run>) MapUtils.getObject(params,"list");
        for (Run run : list){
            int insert = runMapper.insertSelective(run);
            if (insert != 1)
                throw new BizException(Code.FAIL_DATABASE_INSERT);
        }
    }

    public void runDelete(Map params){
        int account_id = MapUtils.getInteger(params,"account_id",0);
        int run_id = MapUtils.getInteger(params,"id",0);
        RunExample example = new RunExample();
        example.createCriteria().andAccountIdEqualTo(account_id).andIdEqualTo(run_id);
        int del = runMapper.deleteByExample(example);
        if (del != 1)
            throw new BizException(Code.FAIL_DATABASE_DEL);
    }
}
