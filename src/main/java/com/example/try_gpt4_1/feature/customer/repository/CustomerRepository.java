package com.example.try_gpt4_1.feature.customer.repository;

import com.example.try_gpt4_1.feature.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerRepository {

    // 全ての顧客を取得
    List<Customer> findAll();

    // IDで顧客を検索
    Optional<Customer> findById(@Param("id") Long id);

    // 名前で顧客を検索
    List<Customer> findByName(@Param("name") String name);

    // 業種で顧客を検索
    List<Customer> findByIndustry(@Param("industry") String industry);

    // 顧客を新規作成
    void create(Customer customer);

    // 顧客情報を更新
    void update(Customer customer);

    // 顧客を論理削除
    void delete(@Param("id") Long id);
}
