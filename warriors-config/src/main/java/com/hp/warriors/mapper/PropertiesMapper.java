package com.hp.warriors.mapper;

import com.hp.warriors.entity.Properties;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PropertiesMapper {

    @Select("select p.id,p.key,p.value,p.application,p.profile,p.label from properties p where id = #{id}")
    Properties get(@Param("id") Integer id);
}
