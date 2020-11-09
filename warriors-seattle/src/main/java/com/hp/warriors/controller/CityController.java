package com.hp.warriors.controller;

import com.hp.warriors.entity.City;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 初始化城市数据
     */
    @GetMapping("/init")
    public void init() {
        try {
            List<String> cityList = FileUtils.readLines(ResourceUtils.getFile("classpath:city.txt"));
            Executors.newFixedThreadPool(8).submit(() -> {
                cityList.forEach(cityStr -> {
                    City city = new City();
                    city.setCode(cityStr.split(",")[0]);
                    city.setName(cityStr.split(",")[1]);
                    city.setProvinceCode(cityStr.split(",")[2]);
                    int affectNum = cityService.save(city);
                    log.info("{}:{} insert success:{}", Thread.currentThread().getName(), city, affectNum);
                });
            });
        } catch (IOException e) {
            log.error("[城市初始化]初始化城市数据异常.", e);
        }
    }

    /**
     * mybatis-plus实现
     */
    @GetMapping("/get")
    public ApiResp get(Integer id) {
        return ApiResp.success(cityService.get(id));
    }
}
