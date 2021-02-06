package com.hp.warriors.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.warriors.entity.houston.Student;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.houston.StudentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "failMethod")//指定降级方法，在熔断和异常时会走降级方法
    public ApiResp get(@RequestParam Integer id) {
        if (id == 1) {
            throw new RuntimeException("getUserById command failed");
        }

        Student student = studentService.getById(id);
        return ApiResp.success(student);
    }

    @PostMapping("/save")
    public ApiResp save(@RequestBody Student student) {
        return ApiResp.success(studentService.save(student));
    }

    @GetMapping("/getById")
    @HystrixCommand(
            fallbackMethod = "failMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),//断路器的最小请求数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80"),//断路频率
            })
    public ApiResp getById(@RequestParam Integer id) {
        Student student = studentService.getById(id);
        return ApiResp.success(student);
    }

    public ApiResp failMethod(Integer id) {
        JSONObject result = new JSONObject();
        result.put("method", "failMethod");
        result.put("id", id);
        return ApiResp.success(result);
    }
}
