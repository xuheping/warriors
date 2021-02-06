package com.hp.warriors.service;

import com.hp.warriors.entity.seattle.School;
import com.hp.warriors.mapper.seattle.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    public School get(Integer id) {
        return schoolMapper.get(id);
    }

    public int save(School school) {
        if(Objects.isNull(school.getId())){
            return schoolMapper.insert(school);
        }
        return schoolMapper.update(school);
    }
}
