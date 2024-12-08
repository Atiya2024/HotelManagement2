package com.oyo1.HotelManagement2.repo;

import com.oyo1.HotelManagement2.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomId(Integer roomId);
}
