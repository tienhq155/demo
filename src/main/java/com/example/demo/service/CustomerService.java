package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponse create(CustomerRequest request) {
        if (repository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Số điện thoại đã tồn tại");
        }

        Customer customer = new Customer(request.getName(), request.getPhone());
        Customer saved = repository.save(customer);

        return mapToResponse(saved);
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<CustomerResponse> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new BadRequestException("Keyword không được để trống");
        }

        return repository
                .findByNameOrPhone(keyword, keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy customer");
        }
        repository.deleteById(id);
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy customer"));

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());

        return mapToResponse(repository.save(customer));
    }

    private CustomerResponse mapToResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getPhone());
    }
}