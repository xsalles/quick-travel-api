package br.com.quick_travel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.quick_travel.main")
public class QuickTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickTravelApplication.class, args);
	}

}
