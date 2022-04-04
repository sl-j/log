package com.lei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lei.mapper")
public class BolgApplication {

    public static void main(String[] args) {
        SpringApplication.run(BolgApplication.class,args);
    }
}
