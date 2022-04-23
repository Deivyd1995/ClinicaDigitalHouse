package Dh.IntegradorHIbernate;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegradorHIbernateApplication {

	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");

		SpringApplication.run(IntegradorHIbernateApplication.class, args);
	}

}
