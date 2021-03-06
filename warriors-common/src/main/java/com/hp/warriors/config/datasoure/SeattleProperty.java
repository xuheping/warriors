package com.hp.warriors.config.datasoure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.stock")
public class SeattleProperty {

    private String jdbcUrl;

    private String username;

    private String password;

    private String driverClassName;
}
