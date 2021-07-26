package com.demo.parking.jpaRepositories;

import com.demo.parking.models.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLogRepository extends JpaRepository<Logs, Long> {

    @Query("SELECT l FROM Logs l WHERE l.ticket_id = ?1")
    Logs findByTicket_id(Long ticket_id);
}
