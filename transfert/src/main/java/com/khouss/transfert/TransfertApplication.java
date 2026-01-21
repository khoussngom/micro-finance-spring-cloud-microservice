package com.khouss.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransfertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfertApplication.class, args);
	}

}
