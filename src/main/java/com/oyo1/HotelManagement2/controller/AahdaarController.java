package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.entity.Aadhaar;
import com.oyo1.HotelManagement2.service.AadhaarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aadhaarDetails")
public class AahdaarController {

    @Autowired
    private AadhaarService aadhaarService;

    @GetMapping("/getDetails")
    public Aadhaar getDetails(@RequestParam Integer id){
         Optional<Aadhaar> aadhaar = aadhaarService.getDetails(id);
         return aadhaar.get();
    }
}
