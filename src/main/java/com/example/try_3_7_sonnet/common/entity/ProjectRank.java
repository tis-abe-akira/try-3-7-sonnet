package com.example.try_3_7_sonnet.common.entity;

/**
 * プロジェクトランクを表す列挙型
 */
public enum ProjectRank {
    S("S"),
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private final String value;

    ProjectRank(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProjectRank fromValue(String value) {
        // まず値で検索
        for (ProjectRank rank : ProjectRank.values()) {
            if (rank.getValue().equals(value)) {
                return rank;
            }
        }
        
        // 次に名前で検索
        for (ProjectRank rank : ProjectRank.values()) {
            if (rank.name().equals(value)) {
                return rank;
            }
        }
        
        throw new IllegalArgumentException("Invalid ProjectRank value: " + value);
    }
}
