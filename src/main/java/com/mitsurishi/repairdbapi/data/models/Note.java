/*
 * Note domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Note")
public class Note {
    // Attrubutes (all private)
    // JPA annotations indicating id as auto-generated (via JPA provider) primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id; 

    @Column(name = "ticket_id", nullable = false, unique = true)
    private Integer ticketId;

    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer employeeId;

    @Column(name = "note", nullable = false, unique = false)
    private String note;

    // Default, empty constructor
    Note() {
    }

    // Custom constructor with all attributes except ID
    Note(Integer ticketId, Integer employeeId, String note) {
        this.ticketId = ticketId;
        this.employeeId = employeeId;
        this.note = note;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public String getNote() {
        return this.note;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
                Objects.equals(getTicketId(), other.getTicketId()) &&
                Objects.equals(getEmployeeId(), other.getEmployeeId()) &&
                Objects.equals(getNote(), other.getNote());
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(getId(), getTicketId(), getEmployeeId(), getNote());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + getId() +
                ", ticketId" + getTicketId() +
                ", employeeId=" + getEmployeeId() +
                ", note=" + getNote() +
                "}";
    }
}