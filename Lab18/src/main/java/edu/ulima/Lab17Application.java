package edu.ulima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.ulima")
public class Lab17Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab17Application.class, args);
	}

}
