package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.dto.requestDto.HotelRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.HotelResponseDto;
import com.oyo1.HotelManagement2.dto.responseDto.PriceInventoryResponseDto;
import com.oyo1.HotelManagement2.entity.Hotel;
import com.oyo1.HotelManagement2.exception.HotelNotFoundException;
import com.oyo1.HotelManagement2.repo.HotelRepository;
import com.oyo1.HotelManagement2.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PriceInvetoryService priceInvetoryService;

    public HotelResponseDto createHotel(HotelRequestDto hotelRequestDto){
        Hotel hotel = convertHotelRequestDtoToHotel(hotelRequestDto);
        hotelRepository.save(hotel);
        HotelResponseDto hotelResponseDto = convertHotelToHotelResponseDto(hotel);
        return hotelResponseDto;
    }

    private HotelResponseDto convertHotelToHotelResponseDto(Hotel hotel) {
        return HotelResponseDto.builder().hotelId(hotel.getHotelId()).name(hotel.getName())
                .status(hotel.getStatus()).rooms(hotel.getRoomList()).build();
//        hotelResponseDto.setHotelId(hotel.getHotelId());
//        hotelResponseDto.setName(hotel.getName());
//        hotelResponseDto.setStatus(hotel.getStatus());
//        hotelResponseDto.setRooms(hotel.getRoomList());
//        return hotelResponseDto;
    }

    private Hotel convertHotelRequestDtoToHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelRequestDto.getHotelId());
        hotel.setStatus(hotelRequestDto.getStatus());
        hotel.setName(hotelRequestDto.getName());
        hotel.setAddress(hotelRequestDto.getAddress());
        hotel.setPhoneNumber(hotelRequestDto.getPhoneNumber());
//        hotel.setRoomList(Arrays.asList(roomRepository.f));
        return hotel;
    }

    public HotelResponseDto getHotel(Integer hotelId) throws HotelNotFoundException {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(!hotel.isPresent()){
            throw new HotelNotFoundException("Hotel with hotel Id : " + hotelId + " does not exist");
        }
        return convertHotelToHotelResponseDto(hotel.get());
    }

    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponseDto> allHotels = new ArrayList<>();
        for(Hotel hotel : hotels){
            allHotels.add(convertHotelToHotelResponseDto(hotel));
        }
        return allHotels;
    }

    public List<PriceInventoryResponseDto> getAllHotelsByLocation(String location) {
        List<Hotel> allHotels = hotelRepository.findHotelsByLocation(location);
        List<PriceInventoryResponseDto> priceInventoryResponseDtos = new ArrayList<>();
        for(Hotel hotel : allHotels){
            PriceInventoryResponseDto priceInventoryResponseDto = priceInvetoryService.getPriceInvetoryForHotelWithMinimumPrice(hotel.getHotelId());
            if(priceInventoryResponseDto.getIsAvailable()) priceInventoryResponseDtos.add(priceInventoryResponseDto);
        }
        return priceInventoryResponseDtos;
    }

    public Hotel getHotelByHotelId(Integer hotelId) {
        return hotelRepository.findByHotelId(hotelId);
    }
}
