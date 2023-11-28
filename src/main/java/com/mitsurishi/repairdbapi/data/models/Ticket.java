/*
 * Model describing Ticket table in DB.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity // JPA annotation that prepares for storage in data store
@Table(name = "Ticket")
public class Ticket {
    // Private member attributes
    // JPA annotations that denote ID as an auto-populated (via JPA provider) primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer employeeId;

    @Column(name = "customer_id", nullable = false, unique = true)
    private Integer customerId;

    @Column(name = "device_desc", nullable = false, unique = false)
    private String deviceDescription;

    @Column(name = "issue_desc", nullable = false, unique = false)
    private String issueDescription;

    @Column(name = "status", nullable = false, unique = false)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name="created_on", nullable = false, unique = false)
    private Date createdOn;

    // Default, empty constructor
    public Ticket() {
    };

    // Parameterized constructor for creating domain object without an ID
    public Ticket(Integer employeeId, Integer customerId, String deviceDescription, String issueDescription, String status,
            Date createdOn) {
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.deviceDescription = deviceDescription;
        this.issueDescription = issueDescription;
        this.status = status;
        this.createdOn = createdOn;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public String getDeviceDescription() {
        return this.deviceDescription;
    }

    public String getIssueDescription() {
        return this.issueDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public Date getCreatedDate() {
        return this.createdOn;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedDate(Date createdOn) {
        this.createdOn = createdOn;
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
        return Objects.equals(getId(), ticket.getId()) && Objects.equals(getEmployeeId(), ticket.getEmployeeId())
                && Objects.equals(getCustomerId(), ticket.getCustomerId())
                && Objects.equals(getDeviceDescription(), ticket.getDeviceDescription())
                && Objects.equals(getIssueDescription(), ticket.getIssueDescription())
                && Objects.equals(getStatus(), ticket.getStatus())
                && Objects.equals(getCreatedDate(), ticket.getCreatedDate());
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(getId(), getEmployeeId(), getCustomerId(), getDeviceDescription(), getIssueDescription(), getStatus(), getCreatedDate());
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Ticket{" + "id=" + getId() + ", employeeId=" + getEmployeeId() + ", customerId=" + getCustomerId()
                + ", deviceDescription='" + getDeviceDescription() + "', issueDescription=" + getIssueDescription() + "', status=" + getStatus()
                + "', createdOn=" + getCreatedDate() + "}";
    }
}