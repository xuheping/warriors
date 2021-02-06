package com.hp.warriors.mapper.seattle;

import com.hp.warriors.entity.seattle.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper {

    int insert(School school);

    int update(School school);

    School get(Integer id);

    int delete(Integer id);
}
