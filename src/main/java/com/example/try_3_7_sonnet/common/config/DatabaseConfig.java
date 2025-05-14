package com.example.try_3_7_sonnet.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.try_gpt4_1.**.repository")
public class DatabaseConfig {
}
