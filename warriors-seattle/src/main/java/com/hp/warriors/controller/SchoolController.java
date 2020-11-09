package com.hp.warriors.controller;

import com.hp.warriors.entity.School;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 通过id获取学校信息
     */
    @GetMapping("/get")
    public ApiResp getById(@RequestParam Integer id) {
        return ApiResp.success("school", schoolService.get(id));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public ApiResp save(@RequestBody School school) {
        return ApiResp.success(schoolService.save(school));
    }
}
