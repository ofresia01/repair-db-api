/*
 * Note domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Note {
    // Attrubutes (all private)
    private @Id @GeneratedValue Integer id; // JPA annotations indicating id as auto-generated (via JPA provider) primary key
    private String text;
    private Integer ticketId;
    private @JoinColumn(name = "employee", referencedColumnName = "id") Employee employee;

    // Default, empty constructor
    Note() {}

    // Custom constructor with all attributes except ID
    Note(String text, Integer ticketId, Employee employee) {
        this.text = text;
        this.ticketId = ticketId;
        this.employee = employee;
    }

    // Getters
    public String getNote() {
        return this.text;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    // Setters
    public void setNote(String text) {
        this.text = text;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        return Objects.equals(this.id, other.id) &&
            Objects.equals(this.text, other.text) &&
            Objects.equals(this.ticketId, other.ticketId) &&
            Objects.equals(this.employee, other.employee);
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(this.id, this.text, this.ticketId, this.employee);
    }

    @Override
    public String toString() {
        return "Note{" + 
            "id=" + this.id +
            ", text=" + this.text +
            ", ticketId=" + this.ticketId +
            ", employee=" + this.employee +
            "}";
    }
}
