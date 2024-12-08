package com.oyo1.HotelManagement2.repo;

import com.oyo1.HotelManagement2.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE LOWER(h.address) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Hotel> findHotelsByLocation(String location);

    Hotel findByHotelId(Integer hotelId);
//    Hotel findByHotelId(Integer hotelId);


}
