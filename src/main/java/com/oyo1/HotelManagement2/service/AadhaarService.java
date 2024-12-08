package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.entity.Aadhaar;
import com.oyo1.HotelManagement2.repo.AadhaarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AadhaarService {

    @Autowired
    private AadhaarRepository aadhaarRepository;


    public Optional<Aadhaar> getDetails(Integer id) {
        return aadhaarRepository.findById(id);
    }
}
