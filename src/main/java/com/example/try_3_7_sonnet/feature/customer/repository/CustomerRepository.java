package com.example.try_3_7_sonnet.feature.customer.repository;

import com.example.try_3_7_sonnet.feature.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerRepository {

    // 全ての顧客を取得
    List<Customer> findAll();

    // ページング付きで全ての顧客を取得
    List<Customer> findAllWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    // 顧客の総数をカウント
    long countAll();

    // IDで顧客を検索
    Optional<Customer> findById(@Param("id") Long id);

    // 名前で顧客を検索
    List<Customer> findByName(@Param("name") String name);

    // 名前でページング付きで顧客を検索
    List<Customer> findByNameWithPaging(@Param("name") String name, @Param("offset") int offset,
            @Param("limit") int limit);

    // 名前で検索した顧客の総数をカウント
    long countByName(@Param("name") String name);

    // 業種で顧客を検索
    List<Customer> findByIndustry(@Param("industry") String industry);

    // 業種でページング付きで顧客を検索
    List<Customer> findByIndustryWithPaging(@Param("industry") String industry, @Param("offset") int offset,
            @Param("limit") int limit);

    // 業種で検索した顧客の総数をカウント
    long countByIndustry(@Param("industry") String industry);

    // 顧客を新規作成
    void create(Customer customer);

    // 顧客情報を更新
    void update(Customer customer);

    // 顧客を論理削除
    void delete(@Param("id") Long id);
}
