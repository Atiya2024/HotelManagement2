package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.entity.Aadhaar;
import com.oyo1.HotelManagement2.entity.Customer;
import com.oyo1.HotelManagement2.repo.AadhaarRepository;
import com.oyo1.HotelManagement2.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AadhaarRepository aadhaarRepository;

    public Customer createCustomer(Customer customer) {
        Aadhaar aadhaar = customer.getAadhaar();
        aadhaar.setCustomer(customer);
//        aadhaarRepository.save(aadhaar);
        customerRepository.save(customer);
        return customer;
    }

    public Optional<Customer> getCustomer(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    public String getEmail(Integer customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.get().getEmail();
    }

    public String getNumber(Integer customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.get().getPhoneNumber();
    }

//    public String getNumber(Customer customer) {
//        return customer.getPhoneNumber();
//    }
}
