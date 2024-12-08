package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.dto.requestDto.RoomRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.RoomResponseDto;
import com.oyo1.HotelManagement2.entity.Room;
import com.oyo1.HotelManagement2.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomResponseDto createRoom(RoomRequestDto roomRequestDto){
        Room room = convertRoomRequestDtoToRoom(roomRequestDto);
        roomRepository.save(room);
        RoomResponseDto roomResponseDto = convertRoomToRoomResponseDto(room);
        return roomResponseDto;
    }

    private RoomResponseDto convertRoomToRoomResponseDto(Room room) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setRoomType(room.getRoomType());
        roomResponseDto.setAmenities(room.getAmenities());
        roomResponseDto.setStatus(room.getStatus());
        roomResponseDto.setRoomId(room.getRoomId());
        return roomResponseDto;
    }

    private Room convertRoomRequestDtoToRoom(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setRoomId(roomRequestDto.getRoomId());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setStatus(roomRequestDto.getStatus());
        room.setAmenities(roomRequestDto.getAmenities());
        room.setMaxOccupancy(roomRequestDto.getMaxOccupancy());
        return room;
    }

    public RoomResponseDto getRoom(Integer roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        return convertRoomToRoomResponseDto(room);
    }

    public List<RoomResponseDto> getAllRooms() {

        List<Room> rooms = roomRepository.findAll();
        List<RoomResponseDto> allRooms = new ArrayList<>();

        for(Room room : rooms){
            allRooms.add(convertRoomToRoomResponseDto(room));

        }
        return allRooms;
    }

    public Room getRoomByRoomId(Integer roomId) {
        return roomRepository.findByRoomId(roomId);
    }
}
