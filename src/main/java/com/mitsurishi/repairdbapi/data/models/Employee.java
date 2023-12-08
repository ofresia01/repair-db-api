/*
 * Employee domain class for Jakarta Persistence Layer (JPL), which allows Java Persistence API (JPA) to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // JPL annotation that prepares object for storage in JPA based data store
@Table(name = "\"Employee\"")
public class Employee {
    // Attributes (all private)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id; // JPL annotations indicating id as auto-generated (via JPL provider) primary
                        // key

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    // NotFound annotation to satisfy one to zero-or-many relationship
    @OneToMany(mappedBy = "employee")
    // @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Set<Note> notes;

    // Notfound annotation to satisfy one to zero-or-many relationship
    @OneToMany(mappedBy = "employee")
    // @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Set<Ticket> tickets;

    // Default empty constructor
    public Employee() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    public Employee(String name,String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Note> getNotes() {
        return this.notes;
    }

    public Set<Ticket> getTickets() {
        return this.tickets;
    }


    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // Overwrite .equals(), .hashCode(), and .toString()
    @Override
    public boolean equals(Object object) {
        // Is object the same instance?
        if (this == object)
            return true;

        // Is object of the Employee type?
        if (!(object instanceof Employee))
            return false;

        // Cast object to Employee, check if all attributes are equal
        Employee emp = (Employee) object;
        return Objects.equals(getId(), emp.getId()) && Objects.equals(getName(), emp.getName());
    }

    @Override
    public int hashCode() {
        // Computes hash value for this object
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        // return string rep of Employee object
        return "Employee{" +
                "id=" + getId() +
                ", name=" + getName() +
                "}";
    }
}