package com.example.try_gpt4_1.feature.customer.service;

import com.example.try_gpt4_1.feature.customer.dto.CustomerRequest;
import com.example.try_gpt4_1.feature.customer.dto.CustomerResponse;
import com.example.try_gpt4_1.feature.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    List<CustomerResponse> findByName(String name);

    List<CustomerResponse> findByIndustry(String industry);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
