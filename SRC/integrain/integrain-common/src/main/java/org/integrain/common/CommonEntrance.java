package org.integrain.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommonEntrance {

	public static void main(String[] args) {
		SpringApplication.run(CommonEntrance.class, args);
	}
}
