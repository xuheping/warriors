package com.hp.warriors.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowProfitWrapper {

    //"中船汉光"
    private String quote_name;
    //"人民币"
    private String currency_name;
    //1
    private Integer org_type;
    //"2020三季报"
    private String last_report_name;
    //"CNY"
    private String currency;
    //
    private List<ProfitWrapper> list;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProfitWrapper {
        //1601395200000
        private Long report_date;
        //2020三季报
        private String report_name;
        //1603789247000
        private Long ctime;
        //营业总收入
        private BigDecimal[] total_revenue;
        //营业总收入
        private BigDecimal[] op;
        //净利润
        private BigDecimal[] net_profit;
        //扣非净利润
        private BigDecimal[] net_profit_after_nrgal_atsolc;
        //研发费用
        private BigDecimal[] rad_cost;
    }
}

