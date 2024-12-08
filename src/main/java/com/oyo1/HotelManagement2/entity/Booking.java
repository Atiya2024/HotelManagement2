package com.oyo1.HotelManagement2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oyo1.HotelManagement2.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "hotel_id")
    Integer hotelId;

    @Column(name = "room_id")
    Integer RoomId;

    @Column(name = "booking_amount")
    Integer bookingAmount;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;

    @Column(name = "is_prepaid")
    Boolean isPrepaid;

    @Column(name = "check_in")
    LocalDate checkIn;

    @Column(name = "check_out")
    LocalDate checkOut;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;

}
