/*
 * Note domain class for Jakarta Persistence Layer (JPL), which allows Java Persistence API (JPA) to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Note\"")
public class Note {
    // Attributes (all private)
    // JPL annotations indicating id as auto-generated (via JPL provider) primary
    // key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @JsonIgnore
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "note", nullable = false)
    private String note;

    // Default, empty constructor
    public Note() {
    }

    // Custom constructor with all attributes except ID
    public Note(Ticket ticket, Employee employee, String note) {
        this.ticket = ticket;
        this.employee = employee;
        this.note = note;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public String getNote() {
        return this.note;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Override .equals(), .hashCode(), and .toString()
    @Override
    public boolean equals(Object object) {
        // Is object the same instance?
        if (this == object)
            return true;

        // Is object of Note type?
        if (!(object instanceof Note))
            return false;

        // Cast object to Note type and check all attributes
        Note other = (Note) object;
        return Objects.equals(getId(), other.getId()) &&
                getTicket().equals(other.getTicket()) &&
                getEmployee().equals(other.getEmployee()) &&
                Objects.equals(getNote(), other.getNote());
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(getId(), getTicket().hashCode(), getEmployee(), getNote());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + getId() +
                ", ticketId" + getTicket().toString() +
                ", employeeId=" + getEmployee().toString() +
                ", note=" + getNote() +
                "}";
    }
}