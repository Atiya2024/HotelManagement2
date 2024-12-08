package com.oyo1.HotelManagement2.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.oyo1.HotelManagement2.entity.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class HotelResponseDto {
    String error;
    Integer hotelId;
    String name;
    Boolean status;
    List<Room> rooms;
}
