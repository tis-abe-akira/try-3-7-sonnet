package com.example.try_gpt4_1.feature.project.repository;

import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import com.example.try_gpt4_1.feature.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectRepository {

    // 全てのプロジェクトを取得
    List<Project> findAll();

    // IDでプロジェクトを検索
    Optional<Project> findById(@Param("id") Long id);

    // 顧客IDでプロジェクトを検索
    List<Project> findByCustomerId(@Param("customerId") Long customerId);

    // プロジェクト名で検索
    List<Project> findByName(@Param("name") String name);

    // ランクでプロジェクトを検索
    List<Project> findByRank(@Param("rank") ProjectRank rank);

    // プロジェクトタイプで検索
    List<Project> findByProjectType(@Param("projectType") ProjectType projectType);

    // 期間でプロジェクトを検索
    List<Project> findByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // プロジェクトを新規作成
    void create(Project project);

    // プロジェクト情報を更新
    void update(Project project);

    // プロジェクトを論理削除
    void delete(@Param("id") Long id);

    // 顧客情報も含めて取得
    Optional<Project> findByIdWithCustomer(@Param("id") Long id);
}
