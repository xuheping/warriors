package com.hp.warriors.entity.seattle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学校
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School extends BaseEntity {

    /**
     * 名字
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 城市编码d
     */
    private String cityCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

}
