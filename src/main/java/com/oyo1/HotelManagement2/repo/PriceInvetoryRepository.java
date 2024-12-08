package com.oyo1.HotelManagement2.repo;

import com.oyo1.HotelManagement2.entity.PriceInventoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceInvetoryRepository extends JpaRepository<PriceInventoryDetails, Integer  > {

    ///// finding inventory for a particular hotel on particular date //////

    List<PriceInventoryDetails> findByHotelIdAndDate(Integer hotelId, LocalDate checkIn);


    ////// finding hotel rooms with minimum price //////

    @Query(value = "SELECT pid.* " +
                "FROM price_inventory_details pid " +
                "WHERE pid.hotel_id = :hotelId " +
                "AND pid.available_rooms > 0 " +
                "ORDER BY pid.price ASC " +
                "LIMIT 1", nativeQuery = true)
    PriceInventoryDetails findByHotelIdWithMinPrice(@Param("hotelId") Integer hotelId);


    ////////decreasing inventory/////////

    @Modifying
    @Query("UPDATE PriceInventoryDetails p " +
            "SET p.availableRooms = p.availableRooms - 1 " +
            "WHERE p.roomId = :roomId " +
            "AND p.hotelId = :hotelId " +
            "AND p.date = :checkIn " +
            "AND p.availableRooms > 0")
    void decreaseRoomAvailability(@Param("roomId") Integer roomId,
                                  @Param("hotelId") Integer hotelId,
                                  @Param("checkIn") LocalDate checkIn);



    ///// increase inventory///////////////

    @Modifying
    @Query("UPDATE PriceInventoryDetails p " +
            "SET p.availableRooms = p.availableRooms +1 " +
            "WHERE p.roomId = :roomId " +
            "AND p.hotelId = :hotelId " +
            "AND p.date = :checkIn " +
            "AND p.availableRooms > 0")
    void increaseRoomAvailability(@Param("roomId") Integer roomId,
                                  @Param("hotelId") Integer hotelId,
                                  @Param("checkIn") LocalDate checkIn);

    PriceInventoryDetails findByRoomIdAndHotelId(Integer roomId, Integer hotelId);
}


