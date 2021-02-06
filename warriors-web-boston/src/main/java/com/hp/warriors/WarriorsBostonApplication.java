package com.hp.warriors;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableEurekaClient
//@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WarriorsBostonApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsBostonApplication.class, args);
    }

}
