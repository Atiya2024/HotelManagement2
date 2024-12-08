package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.dto.responseDto.PriceInventoryResponseDto;
import com.oyo1.HotelManagement2.service.PriceInvetoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/priceInventory")
public class PriceInventoryController {

    @Autowired
    private PriceInvetoryService priceInvetoryService;


    @GetMapping("/getPriceInventoryForHotel")
    public List<PriceInventoryResponseDto> getPriceInventoryForHotel(@RequestParam Integer hotelId, @RequestParam LocalDate checkIn){
        return priceInvetoryService.getPriceInvetoryForHotel(hotelId, checkIn);
    }
}
