package com.example.try_3_7_sonnet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.try_3_7_sonnet.**.repository")
public class Try37SonnetApplication {

    public static void main(String[] args) {
        SpringApplication.run(Try37SonnetApplication.class, args);
    }

}
