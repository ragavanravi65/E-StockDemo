package com.stock.service.eStockService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories //(basePackageClasses = StockMongoDBRepository.class)
public class EStockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStockServiceApplication.class, args);
	}

}
