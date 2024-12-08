package com.oyo1.HotelManagement2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oyo1.HotelManagement2.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "room_id")
    Integer roomId;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    RoomType roomType;

    @Column(name = "amenities")
    String amenities;

    @Column
    Integer maxOccupancy;

    @Column(name = "status")
    Boolean status;

    @ManyToMany(mappedBy = "roomList")
    @JsonIgnore
    List<Hotel> hotelList = new ArrayList<>();

}
