package com.demo.parking.jpaRepositories;

import com.demo.parking.models.EntryPoint;
import com.demo.parking.models.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    @Query("SELECT p FROM ParkingSlot p WHERE p.parked = false")
    List<ParkingSlot> findByParkedFalse();

}
