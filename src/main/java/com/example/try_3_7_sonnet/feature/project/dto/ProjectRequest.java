package com.example.try_3_7_sonnet.feature.project.dto;

import com.example.try_3_7_sonnet.common.entity.ProjectRank;
import com.example.try_3_7_sonnet.common.entity.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    @NotBlank(message = "プロジェクト名は必須です")
    @Size(max = 100, message = "プロジェクト名は100文字以下で入力してください")
    private String name;

    @NotNull(message = "顧客IDは必須です")
    private Long customerId;

    @Size(max = 50, message = "担当部署名称は50文字以下で入力してください")
    private String departmentName;

    @Size(max = 50, message = "プロジェクトマネージャー名は50文字以下で入力してください")
    private String projectManagerName;

    @Size(max = 50, message = "プロジェクトリーダー名は50文字以下で入力してください")
    private String projectLeaderName;

    @NotNull(message = "プロジェクトランクは必須です")
    private ProjectRank rank;

    @NotNull(message = "プロジェクト開始日は必須です")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "プロジェクト区分は必須です")
    private ProjectType projectType;

    @Size(max = 500, message = "プロジェクト説明は500文字以下で入力してください")
    private String description;
}
