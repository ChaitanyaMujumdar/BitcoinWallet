package com.bitcointransactions.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bitcointransactions.record.repo")
@EntityScan("com.bitcointransactions.record.model")
public class BitcoinTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinTransactionsApplication.class, args);
		System.out.println("----------------Receiving Bitcoins now-------------------");
	}

}
