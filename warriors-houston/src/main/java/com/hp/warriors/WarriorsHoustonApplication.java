package com.hp.warriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

<<<<<<< HEAD
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
=======
@EnableFeignClients
@EnableEurekaClient
/** 熔断注解*/
@EnableHystrix
>>>>>>> 818c0f0fd073bd61598b92b8b6a28b4fd6a29fde
@SpringBootApplication
public class WarriorsHoustonApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsHoustonApplication.class, args);
    }

}
