package com.oyo1.HotelManagement2.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceInventoryResponseDto {

    Integer hotelId;
    Integer roomId;
    LocalDate date;
    Boolean isAvailable = false;
    Integer price;
}
