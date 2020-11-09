package com.hp.warriors.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.warriors.mapper.PropertiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    @Autowired
    private PropertiesMapper propertiesMapper;

    @GetMapping("/get")
    public JSONObject get(Integer id) {
        JSONObject result = new JSONObject();
        result.put("property", propertiesMapper.get(id));
        return result;
    }
}
