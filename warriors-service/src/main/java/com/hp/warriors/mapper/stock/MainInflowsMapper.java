package com.hp.warriors.mapper.stock;

import com.hp.warriors.entity.stock.MainInflows;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MainInflowsMapper {

    int insert(MainInflows mainInflows);

    int update(MainInflows mainInflows);

    int delete(Long id);

    MainInflows getByCodeAndDate(@Param("code") String code,@Param("createDate") Date createDate);

}
