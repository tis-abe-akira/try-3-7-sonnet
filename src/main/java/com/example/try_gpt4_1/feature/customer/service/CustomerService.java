package com.example.try_gpt4_1.feature.customer.service;

import com.example.try_gpt4_1.common.dto.PageResponse;
import com.example.try_gpt4_1.feature.customer.dto.CustomerRequest;
import com.example.try_gpt4_1.feature.customer.dto.CustomerResponse;
import com.example.try_gpt4_1.feature.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> findAll();

    PageResponse<CustomerResponse> findAllWithPaging(int page, int size);

    CustomerResponse findById(Long id);

    List<CustomerResponse> findByName(String name);

    PageResponse<CustomerResponse> findByNameWithPaging(String name, int page, int size);

    List<CustomerResponse> findByIndustry(String industry);

    PageResponse<CustomerResponse> findByIndustryWithPaging(String industry, int page, int size);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
