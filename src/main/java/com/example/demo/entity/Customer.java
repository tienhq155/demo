package com.example.demo.entity;

import jakarta.persistence.*;

@Entity // Phép màu 1: Khai báo đây là một bảng trong Database
@Table(name = "customers") // Đặt tên bảng trong Database
public class Customer {

    @Id // Đánh dấu đây là Khóa chính (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Phép màu 2: ID tự động tăng (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // Không được để trống
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    // Cậu cần tạo Constructor rỗng (bắt buộc cho JPA)
    public Customer() {
    }

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getter và Setter (để lấy và gán giá trị)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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