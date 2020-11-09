package com.hp.warriors.mapper.warriors;

import com.hp.warriors.entity.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper {

    int insert(School school);

    int update(School school);

    School get(Integer id);

    int delete(Integer id);
}
