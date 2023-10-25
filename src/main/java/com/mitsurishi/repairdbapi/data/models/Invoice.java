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
    private Integer customerId;
    private String customerName;
    private Date dateCompleted;

    // Default, empty constructor
    Invoice() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    Invoice(Integer ticketId, Integer customerId, String customerName, Date dateCompleted) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.dateCompleted = dateCompleted;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Date getDateCompleted() {
        return this.dateCompleted;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
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
        return Objects.equals(this.id, invoice.id) && Objects.equals(this.ticketId, invoice.ticketId)
                && Objects.equals(this.customerId, invoice.customerId)
                && Objects.equals(this.customerName, invoice.customerName)
                && Objects.equals(this.dateCompleted, invoice.dateCompleted);
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(this.id, this.ticketId, this.customerId, this.customerName, this.dateCompleted);
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Invoice{" + "id=" + this.id + ", ticketId=" + this.ticketId + ", customerId=" + this.customerId
                + ", customerName='" + this.customerName + "', dateCompleted=" + this.dateCompleted + "}";
    }
}