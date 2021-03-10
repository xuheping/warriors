package com.hp.warriors.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("WARRIORS-WEB-SEATTLE")
public interface WarriorsFeign {

    @GetMapping("/student/get")
    JSONObject get();
}
