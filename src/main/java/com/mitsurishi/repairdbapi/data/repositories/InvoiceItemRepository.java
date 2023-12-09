/*
 * Spring Data JPA repository interface class for InvoiceItem. Supports creating, reading, updating, and deleting records against back-end data store.
 * Allows for:
 *   - Creating new InvoiceItems
 *   - Updating existing InvoiceItems
 *   - Deleting InvoiceItems
 *   - Finding InvoiceItems (one, all, or by searching with simple to complex parameters)
 */
package com.mitsurishi.repairdbapi.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mitsurishi.repairdbapi.data.models.InvoiceItem;

@Repository // Specify class as Repository bean for component scanning
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {} // Declare interface extension of JpaRepository, specifying domain type as Employee and ID type as Integer