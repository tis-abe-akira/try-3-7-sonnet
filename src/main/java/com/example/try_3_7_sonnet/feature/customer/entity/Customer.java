package com.example.try_3_7_sonnet.feature.customer.entity;

import com.example.try_3_7_sonnet.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 顧客管理エンティティ
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
    // 顧客ID
    private Long id;

    // 顧客名
    private String name;

    // 連絡先（電話番号）
    private String phoneNumber;

    // 連絡先（メールアドレス）
    private String email;

    // 業種
    private String industry;

    // 住所
    private String address;

    // 備考
    private String notes;
}
