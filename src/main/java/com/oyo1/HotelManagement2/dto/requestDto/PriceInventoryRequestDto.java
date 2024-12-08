package com.oyo1.HotelManagement2.dto.requestDto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceInventoryRequestDto {

    Integer hotelId;
    Integer roomId;
    LocalDate date;
    Boolean availableRooms;
    Integer price;
}
