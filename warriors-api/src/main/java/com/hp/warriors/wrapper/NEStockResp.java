package com.hp.warriors.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEStockResp {

    private Integer page;

    private Integer count;

    private String order;

    private Integer total;

    private Integer pagecount;

    private Date time;

    private List<NEStock> list;
}
