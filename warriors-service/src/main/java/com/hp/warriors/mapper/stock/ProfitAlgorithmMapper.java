package com.hp.warriors.mapper.stock;

import com.hp.warriors.entity.stock.ProfitAlgorithm;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitAlgorithmMapper {

    int insert(ProfitAlgorithm profitAlgorithm);

    int deleteByCode(String code);
}
