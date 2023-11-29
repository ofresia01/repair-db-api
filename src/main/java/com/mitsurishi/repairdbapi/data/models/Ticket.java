/*
 * Ticket domain class for Jakarta Persistence Layer (JPL), which allows Java Persistence API (JPA) to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity // JPL annotation that prepares for storage in data store
@Table(name = "\"Ticket\"")
public class Ticket {
    // Private member attributes
    // JPL annotations that denote ID as an auto-populated (via JPL provider)
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "device_desc", nullable = false)
    private String deviceDescription;

    @Column(name = "issue_desc", nullable = false)
    private String issueDescription;

    @Column(name = "status", nullable = false)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket")
    private Invoice invoice;

    // NotFound annotation to satisfy one to zero-or-many relationship
    @OneToMany(mappedBy = "ticket")
    // @NotFound(action = NotFoundAction.IGNORE)
    private Set<Note> notes;

    // Default, empty constructor
    public Ticket() {
    };

    // Parameterized constructor for creating domain object without an ID
    public Ticket(Employee employee, Customer customer, String deviceDescription, String issueDescription,
            String status,
            Date createdOn) {
        this.employee = employee;
        this.customer = customer;
        this.deviceDescription = deviceDescription;
        this.issueDescription = issueDescription;
        this.status = status;
        this.createdOn = createdOn;
    }

    // Accessors
    public Integer getId() {
        return this.id;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Customer getCustomer() {
        return this.customer;
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

    public Invoice getInvoice() {
        return this.invoice;
    }

    public Set<Note> getNotes() {
        return this.notes;
    }

    // Mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
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
        return Objects.equals(getId(), ticket.getId()) && getEmployee().equals(ticket.getEmployee())
                && getCustomer().equals(ticket.getCustomer())
                && Objects.equals(getDeviceDescription(), ticket.getDeviceDescription())
                && Objects.equals(getIssueDescription(), ticket.getIssueDescription())
                && Objects.equals(getStatus(), ticket.getStatus())
                && Objects.equals(getCreatedDate(), ticket.getCreatedDate());
    }

    @Override
    public int hashCode() {
        // Computes hash value of this instance
        return Objects.hash(getId(), getEmployee().hashCode(), getCustomer().hashCode(), getDeviceDescription(),
                getIssueDescription(), getStatus(), getCreatedDate(), getInvoice().hashCode());
    }

    @Override
    public String toString() {
        // String representation of Invoice object
        return "Ticket{" + "id=" + getId() + ", employee=" + getEmployee().toString() + ", customer="
                + getCustomer().toString()
                + ", deviceDescription='" + getDeviceDescription() + "', issueDescription=" + getIssueDescription()
                + "', status=" + getStatus()
                + "', createdOn=" + getCreatedDate() + "', invoice=" + getInvoice().toString() + "}";
    }
}