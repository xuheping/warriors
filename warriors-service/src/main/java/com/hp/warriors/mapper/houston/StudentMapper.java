package com.hp.warriors.mapper.houston;

import com.hp.warriors.entity.houston.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    int insert(Student student);

    int update(Student student);

    Student getById(@Param("id") Integer id);
}
