package com.testehan.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@RestController
public class MainSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(MainSpringApp.class, args);
	}

	@GetMapping(path ="/greet")
	public GreetResponse greet(){
		return new GreetResponse("hello :)", List.of("Java","Python"), new Person("Dan",33,new BigDecimal(100)));
	}

	record Person(String name, int age, BigDecimal savings){};
	record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person){}
}
