package com.Antartyca.ejercicio5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.Antartyca"})
public class Ejercicio5Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio5Application.class, args);
	}

}
