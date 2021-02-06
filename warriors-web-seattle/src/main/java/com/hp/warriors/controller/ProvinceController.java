package com.hp.warriors.controller;

import com.hp.warriors.entity.seattle.Province;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.ProvinceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 通过id获取省份数据
     * @param id
     * @return
     */
    @GetMapping("/get")
    public ApiResp getById(@RequestParam Integer id) {
        return ApiResp.success("province", provinceService.get(id));
    }

    /**
     * 保存省份数据
     * @param province
     * @return
     */
    @PostMapping("/save")
    public ApiResp save(@RequestBody Province province) {
        return ApiResp.success(provinceService.save(province));
    }

    /**
     * 初始化省份数据
     */
    @GetMapping("/init")
    public void init() {
        try {
//            List<String> provinceList = FileUtils.readLines(new File("/Users/heping/work/seattle/seattle-web/src/main/resources/province.txt"));
            List<String> provinceList = FileUtils.readLines(ResourceUtils.getFile("classpath:province.txt"));
            Executors.newFixedThreadPool(8).submit(() -> {
                provinceList.forEach(provinceStr -> {
                    Province province = new Province();
                    province.setCode(provinceStr.split(",")[0]);
                    province.setName(provinceStr.split(",")[1]);
                    provinceService.save(province);
                });
            });
        } catch (IOException e) {
            log.error("[省份初始化]初始化省份数据异常.", e);
        }
    }


}
