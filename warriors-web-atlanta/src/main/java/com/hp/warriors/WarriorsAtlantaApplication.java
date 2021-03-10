package com.hp.warriors;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RetrofitScan("com.hp.warriors")
@SpringBootApplication(scanBasePackages = {"com.hp.warriors"})
public class WarriorsAtlantaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarriorsAtlantaApplication.class, args);
	}

}
