package com.klu.pro_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//http://localhost:8761
//localhost:8761
@SpringBootApplication 
@EnableEurekaServer
public class ProEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProEurekaServerApplication.class, args);
	}

}
