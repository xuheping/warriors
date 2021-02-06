package com.hp.warriors.entity.seattle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_city")
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @TableId(value = "id")
    private Integer id;

    /**
     * 城市码
     */
    private String code;

    /**
     * 城市名
     */
    private String name;

    /**
     * 省份码
     */
    private String provinceCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
