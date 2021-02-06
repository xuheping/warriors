package com.hp.warriors.mapper.seattle;

import com.hp.warriors.entity.seattle.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityMapper {
    /**
     * return id
     */
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    @Insert("insert into tb_city (code, name, province_code, create_time, update_time) " +
            "values (#{code}, #{name}, #{provinceCode}, now(), now())")
    int insert(City city);

    /**
     * baseResultMap
     */
    @Results(id = "baseResultMap", value = {
            @Result(property = "id", column = "id"), @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"), @Result(property = "provinceCode", column = "province_code"),
            @Result(property = "createTime", column = "create_time"), @Result(property = "updateTime", column = "update_time")
    })
    @Select("select id, code, name, province_code, create_time, update_time from tb_city where id = #{id}")
    City get(Integer id);

    /**
     * reuse baseResultMap
     */
    @ResultMap("baseResultMap")
    @Select("select id, code, name, province_code, create_time, update_time from tb_city where province_code = #{provinceCode}")
    List<City> listByProvinceCode(String provinceCode);

    @Update({
            "<script>" +
                    "update tb_city " +
                    "<if test = 'null != code'>" +
                    " code = #{code}," +
                    "</if>" +
                    "<if test = 'null != name'>" +
                    " name = #{name}," +
                    "</if>" +
                    "<if test = 'null != provinceCode'>" +
                    " province_code = #{provinceCode}," +
                    "</if>" +
                    " update_time = now()" +
                    "</script>"})
    int update(City city);
}
