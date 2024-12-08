package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.dto.responseDto.PriceInventoryResponseDto;
import com.oyo1.HotelManagement2.entity.Hotel;
import com.oyo1.HotelManagement2.entity.PriceInventoryDetails;
import com.oyo1.HotelManagement2.repo.PriceInvetoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceInvetoryService {

    @Autowired
    private PriceInvetoryRepository priceInvetoryRepository;


    public List<PriceInventoryResponseDto> getAvailableHotelsByMinPrice(List<Hotel> hotelList, LocalDate checkIn){
        return null;
    }


    public List<PriceInventoryResponseDto> getPriceInvetoryForHotel(Integer hotelId, LocalDate checkIn) {
        List<PriceInventoryDetails> priceInventoryDetails = priceInvetoryRepository.findByHotelIdAndDate(hotelId, checkIn);
        List<PriceInventoryResponseDto> priceInventoryResponseDtos = new ArrayList<>();
        for(PriceInventoryDetails priceInventoryDetails1 : priceInventoryDetails){
            priceInventoryResponseDtos.add(convertPriceInventoryDetailsToPriceInventoryResponseDto(priceInventoryDetails1));
        }

        return priceInventoryResponseDtos;
    }

    private PriceInventoryResponseDto convertPriceInventoryDetailsToPriceInventoryResponseDto(PriceInventoryDetails priceInventoryDetails) {
        Boolean isSold = false;
        PriceInventoryResponseDto priceInventoryResponseDto1 = new PriceInventoryResponseDto();
        if(priceInventoryDetails != null) {
            isSold = isSold(priceInventoryDetails.getAvailableRooms());
            PriceInventoryResponseDto priceInventoryResponseDto = PriceInventoryResponseDto.builder().hotelId(priceInventoryDetails.getHotelId()).price(priceInventoryDetails.getPrice())
                    .date(priceInventoryDetails.getDate()).roomId(priceInventoryDetails.getRoomId()).isAvailable(isSold).build();
            return priceInventoryResponseDto;
        }
        return priceInventoryResponseDto1;
    }

    private Boolean isSold(Integer availableRooms){
        return availableRooms > 0;
    }

    public PriceInventoryResponseDto getPriceInvetoryForHotelWithMinimumPrice(Integer hotelId) {
        PriceInventoryDetails priceInventoryDetails = priceInvetoryRepository.findByHotelIdWithMinPrice(hotelId);
        return convertPriceInventoryDetailsToPriceInventoryResponseDto(priceInventoryDetails);
    }

    @Transactional
    public void updatePriceInventory(Integer roomId, Integer hotelId, LocalDate checkIn) {
        priceInvetoryRepository.decreaseRoomAvailability(roomId, hotelId, checkIn);
    }

    @Transactional
    public void increasePriceInventory(LocalDate checkIn, Integer roomId, Integer hotelId) {
        priceInvetoryRepository.increaseRoomAvailability(roomId, hotelId, checkIn);
    }

    public boolean getPriceInvetoryDetailsByRoomIdAndHotelId(Integer roomId, Integer hotelId) {
        PriceInventoryDetails priceInventoryDetails = priceInvetoryRepository.findByRoomIdAndHotelId(roomId, hotelId);
        if(priceInventoryDetails == null){
            return true;
        }
        return priceInventoryDetails.getAvailableRooms() <= 0;
    }
}
