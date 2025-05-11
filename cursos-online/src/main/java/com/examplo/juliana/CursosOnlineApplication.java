package com.examplo.juliana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.examplo.juliana")
public class CursosOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosOnlineApplication.class, args);
	}

}

