package com.examly.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.examly.apigateway.filter.JwtRequestFilter;

     
@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {

	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public ApigatewayApplication(JwtRequestFilter jwtRequestFilter){
		this.jwtRequestFilter = jwtRequestFilter;
	}
   
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
		.route("user_route",route->route.path("/api/users/**")
		.uri("lb://USER-SERVICE"))

		.route("user_loans",route->route.path("/api/loans/**")
		.filters(token->token.filter(jwtRequestFilter))
		.uri("lb://LOAN-SERVICE"))

		.route("feedback_service",route->route.path("/api/feedback/**")
		.filters(token->token.filter(jwtRequestFilter))
		.uri("lb://FEEDBACK-SERVICE"))
		.build();
	}

}
        