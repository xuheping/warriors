package com.hp.warriors.mapper.stock;

import com.hp.warriors.entity.stock.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMapper {

    int insert(Stock stock);

    int update(Stock stock);

    int delete(Long id);

    Stock get(String code);

    List<Stock> listAll();
}
