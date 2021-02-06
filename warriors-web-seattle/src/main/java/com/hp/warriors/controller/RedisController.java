package com.hp.warriors.controller;

import com.hp.warriors.entity.seattle.School;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.SchoolService;
import com.hp.warriors.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/setValue")
    public ApiResp setValue(@RequestParam String key,
                            @RequestParam Integer id) {
        School school = schoolService.get(id);
        return ApiResp.success(redisUtil.set(key, school));
    }

    @GetMapping("/getValue")
    public ApiResp getValue(@RequestParam String key) {
        return ApiResp.success(redisUtil.get(key));
    }
}
