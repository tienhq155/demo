package com.example.demo.dto;

public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;

    public CustomerResponse(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}