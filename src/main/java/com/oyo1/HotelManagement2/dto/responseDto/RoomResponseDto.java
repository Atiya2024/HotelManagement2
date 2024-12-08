package com.oyo1.HotelManagement2.dto.responseDto;

import com.oyo1.HotelManagement2.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto {
    Integer roomId;
    RoomType roomType;
    String amenities;
    Boolean status;
}
