package com.hp.warriors.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hp.warriors.service.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference
    private DubboService dubboService;

    @GetMapping("/test")
    public void list() {
        dubboService.testDubbo();
    }
}
