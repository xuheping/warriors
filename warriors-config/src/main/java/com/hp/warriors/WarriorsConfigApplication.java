package com.hp.warriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.hp.warriors")
public class WarriorsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarriorsConfigApplication.class, args);
    }

}
