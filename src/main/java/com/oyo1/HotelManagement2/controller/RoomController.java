package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.dto.requestDto.RoomRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.RoomResponseDto;
import com.oyo1.HotelManagement2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public RoomResponseDto createRoom(@RequestBody RoomRequestDto roomRequestDto){
        return roomService.createRoom(roomRequestDto);
    }

    @GetMapping("/getRoom")
    public RoomResponseDto getRoom(@RequestParam Integer roomId){
        return roomService.getRoom(roomId);
    }

    @GetMapping("/getAllRooms")
    public List<RoomResponseDto> getAllRoom(){
        return roomService.getAllRooms();
    }
}
