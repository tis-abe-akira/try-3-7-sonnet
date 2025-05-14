package com.example.try_gpt4_1.feature.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "顧客名は必須です")
    @Size(max = 100, message = "顧客名は100文字以下で入力してください")
    private String name;

    @Size(max = 20, message = "電話番号は20文字以下で入力してください")
    private String phoneNumber;

    @Email(message = "メールアドレスの形式が正しくありません")
    @Size(max = 100, message = "メールアドレスは100文字以下で入力してください")
    private String email;

    @Size(max = 50, message = "業種は50文字以下で入力してください")
    private String industry;

    @Size(max = 200, message = "住所は200文字以下で入力してください")
    private String address;

    @Size(max = 500, message = "備考は500文字以下で入力してください")
    private String notes;
}
