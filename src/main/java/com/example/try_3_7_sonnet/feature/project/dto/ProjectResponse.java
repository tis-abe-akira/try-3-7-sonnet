package com.example.try_3_7_sonnet.feature.project.dto;

import com.example.try_3_7_sonnet.common.entity.ProjectRank;
import com.example.try_3_7_sonnet.common.entity.ProjectType;
import com.example.try_3_7_sonnet.feature.customer.dto.CustomerResponse;
import com.example.try_3_7_sonnet.feature.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private Long customerId;
    private CustomerResponse customer;
    private String departmentName;
    private String projectManagerName;
    private String projectLeaderName;
    private ProjectRank rank;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectType projectType;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProjectResponse fromEntity(Project project) {
        ProjectResponseBuilder builder = ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .customerId(project.getCustomerId())
                .departmentName(project.getDepartmentName())
                .projectManagerName(project.getProjectManagerName())
                .projectLeaderName(project.getProjectLeaderName())
                .rank(project.getRank())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectType(project.getProjectType())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt());

        if (project.getCustomer() != null) {
            builder.customer(CustomerResponse.fromEntity(project.getCustomer()));
        }

        return builder.build();
    }
}
