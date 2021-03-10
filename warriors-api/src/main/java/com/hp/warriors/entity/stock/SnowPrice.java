package com.hp.warriors.entity.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowPrice {

    /**
     * id
     */
    private Long id;

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

    /**
     * price
     */
    private BigDecimal price;

    /**
     * current
     */
    private Date current;

    /**
     * create_time
     */
    private Date createTime;
}
