package com.example.demo.repository;

import java.util.List;
import java.text.Normalizer;
import java.util.regex.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
// Phép màu 3: Chỉ cần kế thừa JpaRepository, cậu có sẵn các hàm save(),
// findAll(), delete() mà không cần viết SQL.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameOrPhone(String name, String Phone);

    boolean existsByPhone(String phone);
}

// public interface CustomerRepository extends JpaRepository<Customer, Long> {
// // Tìm kiếm trên cột không dấu, không phân biệt hoa thường
// List<Customer> findByNameUnsignedContainingIgnoreCase(String nameUnsigned);
// }
