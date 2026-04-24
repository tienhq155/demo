package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Phép màu 3: Chỉ cần kế thừa JpaRepository, cậu có sẵn các hàm save(),
// findAll(), delete() mà không cần viết SQL.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

}
