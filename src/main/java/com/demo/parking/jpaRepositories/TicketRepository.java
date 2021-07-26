package com.demo.parking.jpaRepositories;

import com.demo.parking.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.transaction_id = ?1")
    Ticket findByTransaction_id(String transaction_id);

}
