package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<CustomerResponse> search(@RequestParam String q) {
        return service.search(q);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}