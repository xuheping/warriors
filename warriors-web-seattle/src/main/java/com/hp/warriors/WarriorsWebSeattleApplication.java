package com.hp.warriors;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/** feign调用注解*/
//@EnableFeignClients
/** 注册eureka注解*/
//@EnableEurekaClient
@RetrofitScan("com.hp.warriors")
@SpringBootApplication(scanBasePackages = {"com.hp.warriors"})
public class WarriorsWebSeattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsWebSeattleApplication.class, args);
    }

}
