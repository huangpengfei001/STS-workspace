package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.example.demo" })
//@MapperScan(value = { "com.example.demo.mapper" })
//@ComponentScan("com.example.demo")
@MapperScan(value = { "com.example.demo.dao" })
@EnableEurekaClient
public class HpfApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpfApplication.class, args);
	}

}
