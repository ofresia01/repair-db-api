/*
 * Spring Data JPA repository interface class for Invoices. Supports creating, reading, updating, and deleting records against back-end data store.
 * Allows for:
 *   - Creating new Invoices
 *   - Updating existing Invoices
 *   - Deleting Invoices
 *   - Finding Invoices (one, all, or by searching with simple to complex parameters)
 */
package com.mitsurishi.repairdbapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mitsurishi.repairdbapi.data.models.Invoice;

interface InvoiceRepository extends JpaRepository<Invoice, Integer> {} // Declare interface extension of JpaRepository, specifying domain type as Invoice and ID type as Integer