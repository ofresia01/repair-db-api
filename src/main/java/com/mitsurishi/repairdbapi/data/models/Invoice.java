/*
 * Invoice domain class for Jakarta Persistence Layer (JPL), which allows Java Persistence API (JPA) to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // JPL annotation that prepares object for storage in JPL-based data store
@Table(name = "\"Invoice\"")
public class Invoice {
    // Private attributes
    // JPL annotations indicating id as auto-populated (via JPL provider) primary
    // key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceItem> invoiceItems;

    // Default, empty constructor
    public Invoice() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    public Invoice(Ticket ticket) {
        this.ticket = ticket;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }


    public Ticket getTicket() {
        return this.ticket;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
        return Objects.equals(getId(), invoice.getId()) && getTicket().equals(invoice.getTicket());
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(getId(), getTicket().hashCode());
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Invoice{" + "id=" + getId() + ", ticketId=" + getTicket().getId() + "}";
    }
}