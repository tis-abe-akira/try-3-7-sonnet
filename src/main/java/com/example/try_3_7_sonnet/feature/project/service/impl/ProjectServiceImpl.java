package com.example.try_3_7_sonnet.feature.project.service.impl;

import com.example.try_3_7_sonnet.common.entity.ProjectRank;
import com.example.try_3_7_sonnet.common.entity.ProjectType;
import com.example.try_3_7_sonnet.common.exception.ResourceNotFoundException;
import com.example.try_3_7_sonnet.common.service.impl.DateTimeService;
import com.example.try_3_7_sonnet.feature.customer.entity.Customer;
import com.example.try_3_7_sonnet.feature.customer.repository.CustomerRepository;
import com.example.try_3_7_sonnet.feature.project.dto.ProjectRequest;
import com.example.try_3_7_sonnet.feature.project.dto.ProjectResponse;
import com.example.try_3_7_sonnet.feature.project.entity.Project;
import com.example.try_3_7_sonnet.feature.project.repository.ProjectRepository;
import com.example.try_3_7_sonnet.feature.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;
    private final DateTimeService dateTimeService;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponse findById(Long id) {
        return projectRepository.findByIdWithCustomer(id)
                .map(ProjectResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findByCustomerId(Long customerId) {
        return projectRepository.findByCustomerId(customerId).stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findByName(String name) {
        return projectRepository.findByName(name).stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findByRank(ProjectRank rank) {
        return projectRepository.findByRank(rank).stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findByProjectType(ProjectType projectType) {
        return projectRepository.findByProjectType(projectType).stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findByPeriod(LocalDate startDate, LocalDate endDate) {
        return projectRepository.findByPeriod(startDate, endDate).stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectResponse create(ProjectRequest request) {
        // 顧客の存在確認
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", request.getCustomerId()));

        Project project = Project.builder()
                .name(request.getName())
                .customerId(request.getCustomerId())
                .departmentName(request.getDepartmentName())
                .projectManagerName(request.getProjectManagerName())
                .projectLeaderName(request.getProjectLeaderName())
                .rank(request.getRank())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .projectType(request.getProjectType())
                .description(request.getDescription())
                .createdAt(dateTimeService.getCurrentDateTime())
                .updatedAt(dateTimeService.getCurrentDateTime())
                .isDeleted(false)
                .build();

        projectRepository.create(project);

        // 顧客情報をセット
        project.setCustomer(customer);

        return ProjectResponse.fromEntity(project);
    }

    @Override
    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        // 顧客の存在確認（顧客IDが変更された場合）
        if (!existingProject.getCustomerId().equals(request.getCustomerId())) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", request.getCustomerId()));
            existingProject.setCustomer(customer);
        }

        existingProject.setName(request.getName());
        existingProject.setCustomerId(request.getCustomerId());
        existingProject.setDepartmentName(request.getDepartmentName());
        existingProject.setProjectManagerName(request.getProjectManagerName());
        existingProject.setProjectLeaderName(request.getProjectLeaderName());
        existingProject.setRank(request.getRank());
        existingProject.setStartDate(request.getStartDate());
        existingProject.setEndDate(request.getEndDate());
        existingProject.setProjectType(request.getProjectType());
        existingProject.setDescription(request.getDescription());
        existingProject.setUpdatedAt(dateTimeService.getCurrentDateTime());

        projectRepository.update(existingProject);
        return ProjectResponse.fromEntity(existingProject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        projectRepository.delete(id);
    }
}
