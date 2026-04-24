package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Khai báo đây là nơi tiếp nhận Request từ Web
@RequestMapping("/api/customers") // Đường dẫn chung
public class CustomerController {

    @Autowired // Tiếp tục DI: Tiêm Service vào Controller
    private CustomerService customerService;

    // Mở cổng nhận dữ liệu gửi lên (Thường dùng phương thức POST)
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // Mở cổng trả về danh sách khách hàng (Thường dùng phương thức GET)
    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/find")
    public List<Customer> findByName(@RequestParam String name) {
        return customerService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return "Đã xóa khách hàng có ID: " + id;
    }

    // @PutMapping("/{id}")
    // public String putById(@PathVariable Long id) {
    // customerService.putById(id);
    // return "Đã cập nhật khách hàng có ID: " + id;
    // }

}
