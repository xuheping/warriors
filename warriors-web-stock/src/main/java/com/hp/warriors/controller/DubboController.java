package com.hp.warriors.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import com.hp.warriors.service.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference
    private DubboService dubboService;

    @GetMapping("/test")
    public Map list() {
        Map map = Maps.newHashMap();
        map.put("connect",dubboService.testDubbo());
        return map;
    }
}
