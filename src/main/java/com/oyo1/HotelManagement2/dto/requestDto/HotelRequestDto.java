package com.oyo1.HotelManagement2.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDto {

    Integer hotelId;
    String name;
    String address;
    String phoneNumber;
    Boolean status;
}
