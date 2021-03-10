package com.hp.warriors.entity.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitAlgorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代码
     */
    private String code;

    /**
     * 本金
     */
    private BigDecimal cash;

    /**
     * 买入价格
     */
    private BigDecimal buyPrice;

    /**
     * 当前价格
     */
    private BigDecimal currentPrice;

    /**
     * 涨跌幅
     */
    private BigDecimal rate;

    /**
     * 个人涨跌
     */
    private BigDecimal earnRate;

    /**
     * 盈利
     */
    private BigDecimal earnMoney;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

}
