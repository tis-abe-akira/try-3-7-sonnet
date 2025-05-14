package com.example.try_3_7_sonnet.common.entity;

/**
 * プロジェクト区分を表す列挙型
 */
public enum ProjectType {
    NEW_DEVELOPMENT("新規開発"),
    MAINTENANCE("保守開発"),
    PACKAGE_IMPLEMENTATION("パッケージ導入");

    private final String value;

    ProjectType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProjectType fromValue(String value) {
        // まず値で検索
        for (ProjectType type : ProjectType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        
        // 次に名前で検索
        for (ProjectType type : ProjectType.values()) {
            if (type.name().equals(value)) {
                return type;
            }
        }
        
        throw new IllegalArgumentException("Invalid ProjectType value: " + value);
    }
}
