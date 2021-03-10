package com.hp.warriors.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hp.warriors.service.DubboService;
import org.springframework.stereotype.Component;

@Component
@Service
public class DubboServiceImpl implements DubboService {

    public String testDubbo(){
        return "访问成功";
    }
}
