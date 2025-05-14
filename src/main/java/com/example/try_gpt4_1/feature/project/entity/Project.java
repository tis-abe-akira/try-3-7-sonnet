package com.example.try_gpt4_1.feature.project.entity;

import com.example.try_gpt4_1.common.entity.BaseEntity;
import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import com.example.try_gpt4_1.feature.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * プロジェクト管理エンティティ
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {
    // プロジェクトID
    private Long id;

    // プロジェクト名
    private String name;

    // 顧客ID（外部キー）
    private Long customerId;

    // 顧客情報（参照）
    private Customer customer;

    // 担当部署名称
    private String departmentName;

    // プロジェクトマネージャー名
    private String projectManagerName;

    // プロジェクトリーダー名
    private String projectLeaderName;

    // プロジェクトランク（S, A, B, C, D）
    private ProjectRank rank;

    // プロジェクト開始日
    private LocalDate startDate;

    // プロジェクト終了日
    private LocalDate endDate;

    // プロジェクト区分（新規開発/保守開発/パッケージ導入）
    private ProjectType projectType;

    // 備考
    private String description;
}
