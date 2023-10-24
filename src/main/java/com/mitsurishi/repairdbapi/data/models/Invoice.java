/*
 * Model describing Invoice table in DB.
 */
package com.mitsurishi.repairdbapi.data.models;

import javax.persistence.*;
import java.util.Date;

@Entity // Using Java Persistence API for management of object-relational mapping
public class Invoice {
    // Define ID as primary key, automatically generate ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Private attributes
    private Integer id;
    private Integer ticketId;
    private Integer customerId;
    private String customerName;
    private Date dateCompleted;

    // Empty constructor
    public Invoice() {
    }

    public Invoice(Integer id, Integer ticketId, Integer customerId, String customerName, Date dateCompleted) {
        this.id = id;
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.dateCompleted = dateCompleted;
    }

    // Accessors and mutators
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getcustomerId() {
        return this.customerId;
    }

    public void setcustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDateCompleted() {
        return this.dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}