package com.examly.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
     
@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
		.route("user_route",route->route.path("/api/users/**")
		.uri("lb://USER-SERVICE"))
		.route("user_loans",route->route.path("/api/loans/**")
		.uri("lb://LOAN-SERVICE")
		)
		.build();
	}

}
        