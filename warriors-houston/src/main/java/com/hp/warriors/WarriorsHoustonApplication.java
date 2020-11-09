package com.hp.warriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
/** 熔断注解*/
@EnableHystrix
@SpringBootApplication
public class WarriorsHoustonApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsHoustonApplication.class, args);
    }

}
