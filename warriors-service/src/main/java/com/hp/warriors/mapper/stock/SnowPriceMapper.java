package com.hp.warriors.mapper.stock;

import com.hp.warriors.entity.stock.SnowPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface SnowPriceMapper {

    int insert(SnowPrice snowPrice);
}
