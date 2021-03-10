package com.hp.warriors.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEStock {
    //"1300138"
    private String CODE;
    //16.07
    private BigDecimal HIGH;
    //15.3
    private BigDecimal LOW;
    //"晨光生物"
    private String NAME;
    //换手率:0.01007760684732
    private String HS;
    //开盘价
    private BigDecimal OPEN;
    //30.497150717233
    private BigDecimal PE;
    //当前价:15.52
    private BigDecimal PRICE;
    //名称:"晨光生物"
    private String SNAME;
    //代码:"300138"
    private String SYMBOL;
    //市值:8287288740.8
    private BigDecimal TCAP;
    /*//市值:8287288740.8
    private BigDecimal TURNOVER;*/
    //成交量5381188
    private Integer VOLUME;
    //昨收15.75
    private BigDecimal YESTCLOSE;
}
