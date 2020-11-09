package com.hp.warriors.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {

    /**
     * id
     */
    private Integer id;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    /**
     * application
     */
    private String application;

    /**
     * profile
     */
    private String profile;

    /**
     * label
     */
    private String label;
}
