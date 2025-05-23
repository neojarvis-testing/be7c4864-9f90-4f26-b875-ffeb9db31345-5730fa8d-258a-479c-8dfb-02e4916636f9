package com.examly.springappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringappserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappserverApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteLocator(RouteLocatorBuilder builder){

		return builder.routes()
		.route("user_route",route->route.path("/api/user/**")
		.uri("http://localhost:8085"))
		.build();
	}

}
