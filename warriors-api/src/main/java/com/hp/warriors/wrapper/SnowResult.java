package com.hp.warriors.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowResult {

    private SnowProfitWrapper data;
    //0
    private Integer error_code;
    //""
    private String error_description;
}
