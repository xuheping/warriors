package com.hp.warriors.config.datasoure;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties(prefix = "spring.datasource.houston")
public class HoustonProperty {

    private String jdbcUrl;

    private String username;

    private String password;

    private String driverClassName;
}
