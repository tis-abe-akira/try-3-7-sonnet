package com.example.try_gpt4_1.feature.project.controller;

import com.example.try_gpt4_1.common.dto.ApiResponse;
import com.example.try_gpt4_1.common.entity.ProjectRank;
import com.example.try_gpt4_1.common.entity.ProjectType;
import com.example.try_gpt4_1.feature.project.dto.ProjectRequest;
import com.example.try_gpt4_1.feature.project.dto.ProjectResponse;
import com.example.try_gpt4_1.feature.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProjectResponse>> getAllProjects(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProjectRank rank,
            @RequestParam(required = false) ProjectType projectType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<ProjectResponse> projects;

        if (name != null && !name.isEmpty()) {
            projects = projectService.findByName(name);
        } else if (rank != null) {
            projects = projectService.findByRank(rank);
        } else if (projectType != null) {
            projects = projectService.findByProjectType(projectType);
        } else if (startDate != null && endDate != null) {
            projects = projectService.findByPeriod(startDate, endDate);
        } else {
            projects = projectService.findAll();
        }

        return ApiResponse.success("プロジェクト情報を取得しました", projects);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.findById(id);
        return ApiResponse.success("プロジェクト情報を取得しました", project);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProjectResponse>> getProjectsByCustomerId(@PathVariable Long customerId) {
        List<ProjectResponse> projects = projectService.findByCustomerId(customerId);
        return ApiResponse.success("顧客のプロジェクト情報を取得しました", projects);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        ProjectResponse createdProject = projectService.create(request);
        return ApiResponse.success("プロジェクトを登録しました", createdProject);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {
        ProjectResponse updatedProject = projectService.update(id, request);
        return ApiResponse.success("プロジェクト情報を更新しました", updatedProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ApiResponse.success("プロジェクトを削除しました", null);
    }
}
