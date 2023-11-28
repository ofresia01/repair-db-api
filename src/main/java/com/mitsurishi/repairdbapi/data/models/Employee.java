/*
 * Employee domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Entity // JPA annotation that prepares object for storage in JPA based data store
@Table(name = "Employee")
public class Employee {
    // Attributes (all private)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id; // JPA annotations indicating id as auto-generated (via JPA provider) primary key

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    // Default empty constructor
    Employee() {
    }

    // Custom constructor for creating this domain object without yet having an ID
    Employee(String name) {
        this.name = name;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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