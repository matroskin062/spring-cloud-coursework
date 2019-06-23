package com.client.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@ComponentScan({"com.client.client.Controller", "com.client.client.Configuration"})
public class ClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
