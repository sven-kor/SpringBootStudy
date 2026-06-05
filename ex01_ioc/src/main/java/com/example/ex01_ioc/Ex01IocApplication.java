package com.example.ex01_ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@ComponentSacn내장 -> 하위에엇 @Component를 서치
public class Ex01IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex01IocApplication.class, args);
	}

}
