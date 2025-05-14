package com.example.try_gpt4_1.common.entity;

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
        for (ProjectRank rank : ProjectRank.values()) {
            if (rank.getValue().equals(value)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid ProjectRank value: " + value);
    }
}
