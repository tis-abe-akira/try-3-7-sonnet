package com.example.try_gpt4_1.feature.project.service;

import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import com.example.try_gpt4_1.feature.project.dto.ProjectRequest;
import com.example.try_gpt4_1.feature.project.dto.ProjectResponse;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {

    List<ProjectResponse> findAll();

    ProjectResponse findById(Long id);

    List<ProjectResponse> findByCustomerId(Long customerId);

    List<ProjectResponse> findByName(String name);

    List<ProjectResponse> findByRank(ProjectRank rank);

    List<ProjectResponse> findByProjectType(ProjectType projectType);

    List<ProjectResponse> findByPeriod(LocalDate startDate, LocalDate endDate);

    ProjectResponse create(ProjectRequest request);

    ProjectResponse update(Long id, ProjectRequest request);

    void delete(Long id);
}
