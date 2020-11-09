package com.hp.warriors.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${warriors.url}")
    private String url;

    /**
     * 输出url
     */
    @GetMapping("/url")
    public JSONObject list() {
        JSONObject result = new JSONObject();
        result.put("url",url);
        return result;
    }
}
