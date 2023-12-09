/*
 * Spring Data JPA repository interface class for Ticket. Supports creating, reading, updating, and deleting records against back-end data store.
 * Allows for:
 *   - Creating new Tickets
 *   - Updating existing Tickets
 *   - Deleting Tickets
 *   - Finding Tickets (one, all, or by searching with simple to complex parameters)
 */
package com.mitsurishi.repairdbapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mitsurishi.repairdbapi.data.models.Ticket;

@Repository // Specify class as Repository bean for component scanning
public interface TicketRepository extends JpaRepository<Ticket, Integer> {}