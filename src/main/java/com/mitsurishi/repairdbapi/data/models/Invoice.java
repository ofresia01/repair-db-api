/*
 * Model describing Invoice table in DB.
 */
package com.mitsurishi.repairdbapi.data.models;

import javax.persistence.*;
import java.util.Date;

@Entity // Using Java Persistence API for management of object/relational mapping
public class Invoice {
    // Define ID as primary key, automatically generate ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // Private attributes
    private Integer id;
    private Integer ticketId;
    private Integer CustomerId;
    private String customerName;
    private Date dateCompleted;

    // Empty constructor
    public Invoice() {
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

    public Integer getCustomerId() {
        return this.CustomerId;
    }

    public void setCustomerId(Integer CustomerId) {
        this.CustomerId = CustomerId;
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