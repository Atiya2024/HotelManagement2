package com.oyo1.HotelManagement2.controller;

import com.oyo1.HotelManagement2.dto.requestDto.BookingRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.BookingResponseDto;
import com.oyo1.HotelManagement2.exception.*;
import com.oyo1.HotelManagement2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto){
        try {
            return new ResponseEntity<>(bookingService.createBooking(bookingRequestDto), HttpStatus.CREATED);
        }

        catch (BookingDateInvalidException | RoomDoesNotExistException | OccupencyNotAvailableException |
               HotelNotFoundException | RoomInventoryNotAvailable e){
            BookingResponseDto bookingResponseDto = new BookingResponseDto();
            bookingResponseDto.setError(e.getMessage());
            return new ResponseEntity<>(bookingResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public BookingResponseDto getBooking(@RequestParam Integer bookingId){
        return bookingService.getBooking(bookingId);
    }

    @PatchMapping("/updateBooking")
    public ResponseEntity<BookingResponseDto> updateBooking(@RequestParam Integer bookingId, @RequestBody BookingRequestDto bookingRequestDto){
        try{
            return new ResponseEntity<>(bookingService.updateBooking(bookingId, bookingRequestDto), HttpStatus.OK);
        }
        catch (BookingNotFound e){
            BookingResponseDto bookingResponseDto = new BookingResponseDto();
            bookingResponseDto.setError(e.getMessage());
            return new ResponseEntity<>(bookingResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/cancelBooking")
    public ResponseEntity<BookingResponseDto> cancelBooking(@RequestParam Integer bookingId){
        try{
            return new ResponseEntity<>(bookingService.cancelBooking(bookingId), HttpStatus.OK);
        }
        catch (BookingNotFound | BookingAlreadyCancelledException e){
            BookingResponseDto bookingResponseDto = new BookingResponseDto();
            bookingResponseDto.setError(e.getMessage());
            return new ResponseEntity<>(bookingResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
