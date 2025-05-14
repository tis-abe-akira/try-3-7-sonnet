package com.example.try_gpt4_1.common.config;

import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import com.example.try_gpt4_1.common.typehandler.ProjectRankTypeHandler;
import com.example.try_gpt4_1.common.typehandler.ProjectTypeTypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.getTypeHandlerRegistry().register(ProjectRank.class, ProjectRankTypeHandler.class);
            configuration.getTypeHandlerRegistry().register(ProjectType.class, ProjectTypeTypeHandler.class);
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }
}
