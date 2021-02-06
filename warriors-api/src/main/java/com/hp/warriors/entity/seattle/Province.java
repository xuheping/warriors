package com.hp.warriors.entity.seattle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 省份
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province extends BaseEntity {

    /**
     * 省份编码
     */
    private String code;

    /**
     * 省份
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
