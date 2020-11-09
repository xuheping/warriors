package com.hp.warriors.service.houston;

import com.hp.warriors.entity.houston.Student;
import com.hp.warriors.mapper.houston.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    public int save(Student student) {
        if (Objects.nonNull(student.getId())) {
            return studentMapper.update(student);
        }
        return studentMapper.insert(student);
    }
}
