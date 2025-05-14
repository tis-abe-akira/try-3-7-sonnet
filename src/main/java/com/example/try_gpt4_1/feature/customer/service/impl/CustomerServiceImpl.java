package com.example.try_gpt4_1.feature.customer.service.impl;

import com.example.try_gpt4_1.common.exception.ResourceNotFoundException;
import com.example.try_gpt4_1.common.service.impl.DateTimeService;
import com.example.try_gpt4_1.feature.customer.dto.CustomerRequest;
import com.example.try_gpt4_1.feature.customer.dto.CustomerResponse;
import com.example.try_gpt4_1.feature.customer.entity.Customer;
import com.example.try_gpt4_1.feature.customer.repository.CustomerRepository;
import com.example.try_gpt4_1.feature.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DateTimeService dateTimeService;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findByName(String name) {
        return customerRepository.findByName(name).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findByIndustry(String industry) {
        return customerRepository.findByIndustry(industry).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .industry(request.getIndustry())
                .address(request.getAddress())
                .notes(request.getNotes())
                .createdAt(dateTimeService.getCurrentDateTime())
                .updatedAt(dateTimeService.getCurrentDateTime())
                .isDeleted(false)
                .build();

        customerRepository.create(customer);
        return CustomerResponse.fromEntity(customer);
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        existingCustomer.setName(request.getName());
        existingCustomer.setPhoneNumber(request.getPhoneNumber());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setIndustry(request.getIndustry());
        existingCustomer.setAddress(request.getAddress());
        existingCustomer.setNotes(request.getNotes());
        existingCustomer.setUpdatedAt(dateTimeService.getCurrentDateTime());

        customerRepository.update(existingCustomer);
        return CustomerResponse.fromEntity(existingCustomer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.delete(id);
    }
}
