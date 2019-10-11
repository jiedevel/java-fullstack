package com.example.fullstack41;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Fullstack41Application {

	public static void main(String[] args) {
		SpringApplication.run(Fullstack41Application.class, args);
	}
	
	/**
	 * @RequestMapping("/hello")
	 * @return
	 */
	/**
	 * public String greetings() {
		return "hello world";
	}
	*/
	

}
