package com.oyo1.HotelManagement2.dto.requestDto;

import com.oyo1.HotelManagement2.entity.Customer;
import com.oyo1.HotelManagement2.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    Integer hotelId;

    Integer roomId;

    Integer bookingAmount;

    LocalDate checkIn;

    LocalDate checkOut;

    Boolean isPrepaid;

    Integer customerId;

    Integer noOfGuests;
}
