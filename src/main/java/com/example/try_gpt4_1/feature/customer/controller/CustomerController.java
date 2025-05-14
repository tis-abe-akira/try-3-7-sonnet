package com.example.try_gpt4_1.feature.customer.controller;

import com.example.try_gpt4_1.common.dto.ApiResponse;
import com.example.try_gpt4_1.feature.customer.dto.CustomerRequest;
import com.example.try_gpt4_1.feature.customer.dto.CustomerResponse;
import com.example.try_gpt4_1.feature.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<CustomerResponse>> getAllCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String industry) {

        List<CustomerResponse> customers;

        if (name != null && !name.isEmpty()) {
            customers = customerService.findByName(name);
        } else if (industry != null && !industry.isEmpty()) {
            customers = customerService.findByIndustry(industry);
        } else {
            customers = customerService.findAll();
        }

        return ApiResponse.success("顧客情報を取得しました", customers);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse customer = customerService.findById(id);
        return ApiResponse.success("顧客情報を取得しました", customer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse createdCustomer = customerService.create(request);
        return ApiResponse.success("顧客を登録しました", createdCustomer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {
        CustomerResponse updatedCustomer = customerService.update(id, request);
        return ApiResponse.success("顧客情報を更新しました", updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ApiResponse.success("顧客を削除しました", null);
    }
}
