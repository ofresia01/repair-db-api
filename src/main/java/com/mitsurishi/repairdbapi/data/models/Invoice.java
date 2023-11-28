/*
 * Invoice domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Entity // JPA annotation that prepares object for storage in JPA-based data store
@Table(name = "Invoice")
public class Invoice {
    // Private attributes
    // JPA annotations indicating id as auto-populated (via JPA provider) primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name="ticket_id", nullable = false, unique = true)
    private Integer ticketId;

    // Default, empty constructor
    public Invoice() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    public Invoice(Integer ticketId) {
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
        return Objects.equals(getId(), invoice.getId()) && Objects.equals(getTicketId(), invoice.getTicketId());
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(getId(), getTicketId());
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Invoice{" + "id=" + getId() + ", ticketId=" + getTicketId() + "}";
    }
}