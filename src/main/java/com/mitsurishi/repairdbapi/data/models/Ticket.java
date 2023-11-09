/*
 * Model describing Ticket table in DB.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // JPA annotation that prepares for storage in data store
public class Ticket {
    // Private member attributes
    private @Id @GeneratedValue Integer id; // JPA annotations that denote ID as an auto-populated (via JPA provider) primary key
    private Integer employee_id;
    private Integer customer_id;
    private String device_desc;
    private String issue_desc;
    private String status;
    private Date created_on;

    // Default, empty constructor
    public Ticket() {
    };

    // Parameterized constructor for creating domain object without an ID
    public Ticket(Integer employee_id, Integer customer_id, String device_desc, String issue_desc, String status,
            Date created_on) {
        this.employee_id = employee_id;
        this.customer_id = customer_id;
        this.device_desc = device_desc;
        this.issue_desc = issue_desc;
        this.status = status;
        this.created_on = created_on;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }

    public Integer getEmployeeId() {
        return this.employee_id;
    }

    public Integer getCustomerId() {
        return this.customer_id;
    }

    public String getDeviceDescription() {
        return this.device_desc;
    }

    public String getIssueDescription() {
        return this.issue_desc;
    }

    public String getStatus() {
        return this.status;
    }

    public Date getCreatedDate() {
        return this.created_on;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployeeId(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public void setCustomerId(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public void setDeviceDescription(String device_desc) {
        this.device_desc = device_desc;
    }

    public void setIssueDescription(String issue_desc) {
        this.issue_desc = issue_desc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedDate(Date created_on) {
        this.created_on = created_on;
    }

    // Overriden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object object) {
        // Initial check of instance being an object
        if (this == object)
            return true;

        // Check if instance is of type described by Ticket class
        if (!(object instanceof Ticket))
            return false;

        // Cast object to Ticket, check if all attributes are equal
        Ticket ticket = (Ticket) object;
        return Objects.equals(this.id, ticket.id) && Objects.equals(this.employee_id, ticket.employee_id)
                && Objects.equals(this.customer_id, ticket.customer_id)
                && Objects.equals(this.device_desc, ticket.device_desc)
                && Objects.equals(this.issue_desc, ticket.issue_desc)
                        && Objects.equals(this.status, ticket.status)
                        && Objects.equals(this.created_on, ticket.created_on);
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(this.id, this.employee_id, this.customer_id, this.device_desc, this.issue_desc, this.status,
                this.created_on);
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Ticket{" + "id=" + this.id + ", employee_id=" + this.employee_id + ", customer_id=" + this.customer_id
                + ", device_desc='" + this.device_desc + "', issue_desc=" + this.issue_desc + "', status=" + this.status
                + "', created_on=" + this.created_on + "}";
    }
}