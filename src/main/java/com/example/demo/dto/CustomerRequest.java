package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerRequest { // DTO
    @NotBlank(message = " Tên không được để trống")
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
