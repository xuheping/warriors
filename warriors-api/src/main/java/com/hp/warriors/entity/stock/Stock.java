package com.hp.warriors.entity.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    /**
     * id
     */
    private Long id;

    /**
     * 代码
     */
    private String code;

    /**
     * 文字代码
     */
    private String strCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 股价
     */
    private BigDecimal price;

    /**
     * 市值
     */
    private BigDecimal marketValue;

    /**
     * 本金收益比
     */
    private BigDecimal pe;

    /**
     * 1、上证 2、深证 3、创业 4、科创
     */
    private Integer source;

    /**
     * 版块
     */
    private String section;

    /**
     * 压力位
     */
    private BigDecimal pressure;

    /**
     * 支撑位
     */
    private BigDecimal support;

    /**
     * 止损
     */
    private BigDecimal stopLoss;

    /**
     * 止盈
     */
    private BigDecimal stopProfit;

    /**
     * 支撑点位
     */
    private BigDecimal supportDiffPer;

    /**
     * 止盈点位
     */
    private BigDecimal stopProfitDiffPer;

    /**
     * 压力点位
     */
    private BigDecimal pressureDiffPer;

    /**
     * 止损点位
     */
    private BigDecimal stopLossDiffPer;

    /**
     * 处理标识
     */
    private Integer dealFlag;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    public Integer getSource() {
        Integer source = null;
        if (StringUtils.isBlank(this.code)) {
            return source;
        }
        switch (this.code.substring(0, 2)) {
            case "00":
                source = 1;
                break;
            case "60":
                source = 2;
                break;
            case "30":
                source = 3;
                break;
            case "68":
                source = 4;
                break;
        }
        return source;
    }

    public String getStrCode() {
        String code = null;
        if (StringUtils.isBlank(this.code)) {
            return code;
        }
        switch (this.code.substring(0, 2)) {
            case "00":
                code = "SZ" + this.code;
                break;
            case "60":
                code = "SH" + this.code;
                break;
            case "30":
                code = "SZ" + this.code;
                break;
            case "68":
                code = "SH" + this.code;
                break;
        }
        return code;
    }

}
