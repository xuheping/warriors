package com.hp.warriors.entity.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowProfit {

    /**
     * id
     */
    private Long id;

    /**
     * stock_code
     */
    private String code;

    /**
     * 报告期
     */
    private String reportName;

    /**
     * 研发费用
     */
    private BigDecimal researchCost;

    /**
     * 营业总收入
     */
    private BigDecimal grossRevenue;

    /**
     * 净利润
     */
    private BigDecimal netProfit;

    /**
     * 扣非净利润
     */
    private BigDecimal nonNetProfit;

    /**
     * 报告日期
     */
    private String seasonDate;

    /**
     * 报告日期
     */
    private Date reportDate;
}
