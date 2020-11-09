package com.hp.warriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/** feign调用注解*/
@EnableFeignClients
/** 注册eureka注解*/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.hp.warriors"})
public class WarriorsSeattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsSeattleApplication.class, args);
    }

}
