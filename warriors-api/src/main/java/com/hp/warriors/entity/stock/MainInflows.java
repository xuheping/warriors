package com.hp.warriors.entity.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainInflows {

    /**
     * 主键
     */
    private Long id;

    /**
     * 代码
     */
    private String code;

    /**
     * 收盘价
     */
    private BigDecimal price;

    /**
     * 涨跌幅
     */
    private BigDecimal riseFall;

    /**
     * 净流入
     */
    private BigDecimal netInflow;

    /**
     * 换手
     */
    private BigDecimal changHand;

    /**
     * 日期
     */
    private Date createDate;
}
