package com.hp.warriors.mapper.stock;

import com.hp.warriors.entity.stock.SnowProfit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SnowProfitMapper {

    int insert(SnowProfit snowProfit);

    int update(SnowProfit snowProfit);

    SnowProfit getByCodeAndReportDate(@Param("code") String code, @Param("reportDate") Date reportDate);
}
