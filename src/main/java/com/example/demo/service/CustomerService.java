package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.util.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordencoder;

    public CustomerService(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordencoder = passwordEncoder;
    }

    public CustomerResponse create(CustomerRequest request) {
        if (repository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Số điện thoại đã tồn tại");
        }
        String hashedPassword = passwordencoder.encode(request.getPassword());

        Customer customer = new Customer(request.getName(), request.getPhone(), hashedPassword);
        customer.setName(request.getName());
        customer.setNameSearch(
                TextUtils.removeAccent(request.getName()));
        Customer saved = repository.save(customer);
        return mapToResponse(saved);
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        // List <Customer> customer = repositrory.findAll();
        // List <CustomerResponse> result = new ArrayList<>();
        // for (Customer c : customer){
        // result.add(mapToResponse(c));
        // }
        // return result; chỉ là 2 cách viết, chức năng giống nhau
    }

    public List<CustomerResponse> search(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new BadRequestException("Keyword không được để trống");
        }
        String normalizedKeyword = TextUtils.removeAccent(keyword);
        // return repository
        // .findByNameSearchContainingOrPhoneContaining(normalizedKeyword,keyword)
        // .stream()
        // .map(this::mapToResponse)
        // .collect(Collectors.toList());
        // }
        List<Customer> customer = repository.findByNameSearchContainingOrPhoneContaining(normalizedKeyword, keyword);
        List<CustomerResponse> result = new ArrayList<>();
        for (Customer c : customer) {
            result.add(mapToResponse(c));
        }
        return result;
    }

    // List <Customer> customer =
    // repository.findByNameIgnoreCaseorPhone(keyword,keyword);
    // List <CustomerResponse> result = new ArrayList<>();
    // for (Customer c : customer) {
    // ressult.add(mapToResponse(c));
    // }
    // return result; chỉ là 2 cách viết, chức năng giống nhau

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
        customer.setNameSearch(TextUtils.removeAccent(request.getName()));

        return mapToResponse(repository.save(customer));
    }

    public CustomerResponse login(LoginRequest request) {
        Customer customer = repository.findByPhone(request.getPhone())
                .stream()
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Sai tên tài khoản hoặc mật khẩu"));
        boolean isMatch = passwordencoder.matches(request.getPassword(), customer.getPassword());
        if (!isMatch) {
            throw new BadRequestException("Sai tên tài khoản hoặc mật khẩu");
        }
        return mapToResponse(customer);
    }

    private CustomerResponse mapToResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getPhone());
    }
}