/*
 * Invoice domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.util.Date;
import java.util.Objects;

@Entity // JPA annotation that prepares object for storage in JPA-based data store
public class Invoice {
    // Private attributes
    private @Id @GeneratedValue Integer id; // JPA annotations indicating id as auto-populated (via JPA provider) primary key
    private Integer ticketId;
    
    // Default, empty constructor
    public Invoice() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    public Invoice(Integer ticketId, Integer customerId, String customerName, Date dateCompleted) {
        this.ticketId = ticketId;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    // Overriden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object object) {
        // Initial check of instance being an object
        if (this == object)
            return true;

        // Check if instance is of type described by Invoice class
        if (!(object instanceof Invoice))
            return false;

        // Cast object to Invoice, check if all attributes are equal
        Invoice invoice = (Invoice) object;
        return Objects.equals(this.id, invoice.id) && Objects.equals(this.ticketId, invoice.ticketId);
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(this.id, this.ticketId);
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Invoice{" + "id=" + this.id + ", ticketId=" + this.ticketId + "}";
    }
}