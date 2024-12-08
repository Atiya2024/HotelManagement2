package com.oyo1.HotelManagement2.repo;

import com.oyo1.HotelManagement2.entity.Aadhaar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadhaarRepository extends JpaRepository<Aadhaar, Integer> {
}
