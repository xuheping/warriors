//package com.hp.warriors.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hp.warriors.feign.HoustonFeign;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/feign")
//public class FeignController {
//
//    @Autowired
//    private HoustonFeign houstonFeign;
//
//    @GetMapping("/getStudent")
//    public JSONObject getStudent(Integer id) {
//        return houstonFeign.get(id);
//    }
//
//    @GetMapping("/getById")
//    public JSONObject getById(Integer id) {
//        return houstonFeign.getById(id);
//    }
//}
