package com.example.try_gpt4_1.feature.customer.service.impl;

import com.example.try_gpt4_1.common.dto.PageResponse;
import com.example.try_gpt4_1.common.exception.ResourceNotFoundException;
import com.example.try_gpt4_1.common.service.impl.DateTimeService;
import com.example.try_gpt4_1.common.util.PaginationUtil;
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
    public PageResponse<CustomerResponse> findAllWithPaging(int page, int size) {
        // ページは0から始まるが、UIでは1から始まるので調整
        int offset = page * size;

        List<CustomerResponse> content = customerRepository.findAllWithPaging(offset, size).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());

        long totalElements = customerRepository.countAll();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PaginationUtil.createPageResponse(content, page, size, totalElements, totalPages);
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
    public PageResponse<CustomerResponse> findByNameWithPaging(String name, int page, int size) {
        int offset = page * size;

        List<CustomerResponse> content = customerRepository.findByNameWithPaging(name, offset, size).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());

        long totalElements = customerRepository.countByName(name);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PaginationUtil.createPageResponse(content, page, size, totalElements, totalPages);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findByIndustry(String industry) {
        return customerRepository.findByIndustry(industry).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CustomerResponse> findByIndustryWithPaging(String industry, int page, int size) {
        int offset = page * size;

        List<CustomerResponse> content = customerRepository.findByIndustryWithPaging(industry, offset, size).stream()
                .map(CustomerResponse::fromEntity)
                .collect(Collectors.toList());

        long totalElements = customerRepository.countByIndustry(industry);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PaginationUtil.createPageResponse(content, page, size, totalElements, totalPages);
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
