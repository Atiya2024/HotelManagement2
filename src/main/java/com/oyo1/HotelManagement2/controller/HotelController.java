package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.dto.requestDto.HotelRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.HotelResponseDto;
import com.oyo1.HotelManagement2.dto.responseDto.PriceInventoryResponseDto;
import com.oyo1.HotelManagement2.exception.HotelNotFoundException;
import com.oyo1.HotelManagement2.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<HotelResponseDto> createHotel(@RequestBody HotelRequestDto hotelRequestDto){
        return new ResponseEntity<>(hotelService.createHotel(hotelRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<HotelResponseDto> getHotel(@RequestParam Integer hotelId){

        try{
//            HotelResponseDto hotelResponseDto = hotelService.getHotel(hotelId);
            return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
        }
        catch (HotelNotFoundException e){
            HotelResponseDto hotelResponseDto = new HotelResponseDto();
            hotelResponseDto.setError(e.getMessage());
            return new ResponseEntity<>(hotelResponseDto,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllHotels")
    public List<HotelResponseDto> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/getAllHotelsByLocation")
    public List<PriceInventoryResponseDto> getAllHotelsByLocation(@RequestParam("location") String location){
        return hotelService.getAllHotelsByLocation(location);
    }
}
