package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.example.demo" })
//@MapperScan(value = { "com.example.demo.mapper" })
//@ComponentScan("com.example.demo")
@MapperScan(value = { "com.example.demo.dao" })
public class Hpf2Application {

	public static void main(String[] args) {
		SpringApplication.run(Hpf2Application.class, args);
	}

}
