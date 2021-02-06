package com.hp.warriors.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hp.warriors.service.DubboService;
import org.springframework.stereotype.Component;

@Component
@Service
public class DubboServiceImpl implements DubboService {

    public void testDubbo(){
        System.out.println("访问成功");
    }
}
