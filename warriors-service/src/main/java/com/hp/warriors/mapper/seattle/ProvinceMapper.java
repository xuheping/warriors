package com.hp.warriors.mapper.seattle;

import com.hp.warriors.entity.seattle.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper {

    int insert(Province province);

    int update(Province province);

    Province get(Integer id);

    int delete(Integer id);
}
