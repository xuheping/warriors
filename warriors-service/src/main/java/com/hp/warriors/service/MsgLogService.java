package com.hp.warriors.service;

import com.hp.warriors.entity.seattle.MsgLog;
import com.hp.warriors.mapper.seattle.MsgLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    public int updateTryCount(String msgId, Date nextTryTime) {
        return msgLogMapper.updateTryCount(msgId, nextTryTime);
    }

    public int updateStatus(String msgId,Integer status){
        return msgLogMapper.updateStatus(msgId,status);
    }
}
