package com.hp.warriors.entity.seattle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

}
