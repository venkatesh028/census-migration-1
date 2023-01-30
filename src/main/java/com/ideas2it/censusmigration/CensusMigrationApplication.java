package com.ideas2it.censusmigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CensusMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CensusMigrationApplication.class, args);
	}

}
