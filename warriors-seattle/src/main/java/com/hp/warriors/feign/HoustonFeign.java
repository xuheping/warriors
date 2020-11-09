package com.hp.warriors.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("WARRIROS-HOUSTON")
public interface HoustonFeign {

    @GetMapping("/student/get")
    JSONObject get(@RequestParam Integer id);

    @GetMapping("/student/getById")
    JSONObject getById(@RequestParam Integer id);
}
