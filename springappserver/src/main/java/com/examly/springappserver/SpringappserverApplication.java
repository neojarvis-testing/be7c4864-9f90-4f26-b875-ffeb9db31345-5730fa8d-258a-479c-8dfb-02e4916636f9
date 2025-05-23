package com.examly.springappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SpringappserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappserverApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteLocator(RouteLocatorBuilder builder){

		return builder.routes()
		.route("user_route",route->route.path("")
		.uri("http://localhost:8085"))
		.build();
	}

}
