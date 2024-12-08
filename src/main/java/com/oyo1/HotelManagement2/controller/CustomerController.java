package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.entity.Customer;
import com.oyo1.HotelManagement2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam Integer customerId){
        Optional<Customer> customer =  customerService.getCustomer(customerId);
        return customer.get();
    }
}
