package org.integrain.sm.gateway;

import org.integrain.sm.gateway.filter.PreZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class GatewayBootstrap {
	public static void main(String[] args) {
		SpringApplication.run(GatewayBootstrap.class, args);
	}
	@Bean
	public PreZuulFilter preZuulFilter() {
		return new PreZuulFilter();
	}
}
