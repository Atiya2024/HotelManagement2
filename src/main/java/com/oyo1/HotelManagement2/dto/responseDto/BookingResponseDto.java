package com.oyo1.HotelManagement2.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oyo1.HotelManagement2.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    String error;

    Integer hotelId;

    Integer RoomId;

    BookingStatus bookingStatus;

    LocalDate checkIn;

    LocalDate checkOut;
}
