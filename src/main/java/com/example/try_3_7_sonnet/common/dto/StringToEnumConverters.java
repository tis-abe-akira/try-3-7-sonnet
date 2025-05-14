package com.example.try_3_7_sonnet.common.dto;

import com.example.try_3_7_sonnet.common.entity.ProjectRank;
import com.example.try_3_7_sonnet.common.entity.ProjectType;
import com.example.try_3_7_sonnet.common.exception.InvalidEnumValueException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 非推奨：代わりに
 * {@link com.example.try_3_7_sonnet.common.handler.EnumConverterConfig}
 * を使用してください。
 * 互換性のためにのみ残されています。
 */
@Component
@Deprecated
public class StringToEnumConverters {

    @Component
    public static class StringToProjectRankConverter implements Converter<String, ProjectRank> {
        @Override
        public ProjectRank convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }

            try {
                return ProjectRank.valueOf(source);
            } catch (IllegalArgumentException e) {
                try {
                    // 値が一文字の場合は、値から変換を試みる
                    if (source.length() == 1) {
                        return ProjectRank.fromValue(source);
                    }
                    throw new InvalidEnumValueException("ProjectRank", source);
                } catch (Exception ex) {
                    throw new InvalidEnumValueException("ProjectRank", source);
                }
            }
        }
    }

    @Component
    public static class StringToProjectTypeConverter implements Converter<String, ProjectType> {
        @Override
        public ProjectType convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }

            try {
                return ProjectType.valueOf(source);
            } catch (IllegalArgumentException e) {
                try {
                    // 名前で検索できない場合は、値から変換を試みる
                    return ProjectType.fromValue(source);
                } catch (Exception ex) {
                    throw new InvalidEnumValueException("ProjectType", source);
                }
            }
        }
    }
}
