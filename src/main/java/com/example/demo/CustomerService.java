package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Đánh dấu đây là Tầng Logic
public class CustomerService {

    @Autowired // Phép màu 4 (Dependency Injection): Spring tự lấy Repository đắp vào đây cho
               // cậu dùng.
    private CustomerRepository customerRepository;

    // Hàm lưu khách hàng mới
    public Customer saveCustomer(Customer customer) {
        // Cậu có thể viết thêm logic ở đây: VD: Kiểm tra độ dài số điện thoại...
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            System.out.println("Tên không được để trống");
            return null;
        }
        if (customer.getPhone() != null && customer.getPhone().length() == 10) {
            return customerRepository.save(customer);
        } else {
            System.out.println("Số điện thoại không hợp lệ");
            return null;
        }
        // Gọi Data lưu vào DB
    }

    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    // Hàm lấy danh sách tất cả khách hàng
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteById(long id) {
        customerRepository.deleteById(id);
    }

    public Customer UpdateCustomer(long id, Customer details) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(details.getName());
            customer.setPhone(details.getPhone());
            return customerRepository.save(customer);
        }).orElse(null);
    }
}
