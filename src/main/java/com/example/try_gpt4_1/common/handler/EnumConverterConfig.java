package com.example.try_gpt4_1.common.handler;

import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EnumConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // ProjectRankのString → Enum変換用コンバーター登録
        registry.addConverter(new Converter<String, ProjectRank>() {
            @Override
            public ProjectRank convert(String source) {
                if (source.isEmpty()) {
                    return null;
                }
                try {
                    return ProjectRank.valueOf(source);
                } catch (IllegalArgumentException e) {
                    return ProjectRank.fromValue(source);
                }
            }
        });

        // ProjectTypeのString → Enum変換用コンバーター登録
        registry.addConverter(new Converter<String, ProjectType>() {
            @Override
            public ProjectType convert(String source) {
                if (source.isEmpty()) {
                    return null;
                }
                try {
                    return ProjectType.valueOf(source);
                } catch (IllegalArgumentException e) {
                    return ProjectType.fromValue(source);
                }
            }
        });
    }
}
