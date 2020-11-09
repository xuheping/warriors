package com.hp.warriors.controller;

import com.hp.warriors.entity.houston.Student;
import com.hp.warriors.json.ApiResp;
import com.hp.warriors.service.houston.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public ApiResp getById(@RequestParam Integer id) {
        return ApiResp.success(studentService.getById(id));
    }

    @PostMapping("/save")
    public ApiResp save(@RequestBody Student student) {
        return ApiResp.success(studentService.save(student));
    }
}
