package com.examly.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
     
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

		.route("user_loans",route->route.path("/api/loans/**","/api/colleges/**")
		.filters(token->token.filter(jwtRequestFilter))
		.uri("lb://LOAN-SERVICE"))

		.route("feedback_service",route->route.path("/api/feedback/**")
		.filters(token->token.filter(jwtRequestFilter))
		.uri("lb://FEEDBACK-SERVICE"))

		
		.build();
	}

}
        