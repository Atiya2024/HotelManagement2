package com.oyo1.HotelManagement2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "price_inventory_details")
public class PriceInventoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "hotel_id")
    Integer hotelId;

    @Column(name = "room_id")
    Integer roomId;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "available_rooms")
    Integer availableRooms;

    @Column(name = "price")
    Integer price;



}
