package com.bitcoinwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bitcoinwallet.repository")
@EntityScan("com.bitcoinwallet.entity")
public class BitcoinWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinWalletApplication.class, args);
		System.out.println("----------------Receiving Bitcoins now-------------------");
	}

}
