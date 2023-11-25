package com.mitsurishi.repairdbapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mitsurishi.repairdbapi.data.models.Ticket;
@Repository // Specify class as Repository bean for component scanning
public interface TicketRepository extends JpaRepository<Ticket, Integer> {} 
