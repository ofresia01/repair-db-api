/*
 * Invoice domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.util.Objects;

@Entity // JPA annotation that prepares object for storage in JPA based data store
public class Employee {
    // Attributes (all private)
    private @Id @GeneratedValue Integer id; // JPA annotations indicating id as auto-generated (via JPA provider) primary key
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
        return Objects.equals(this.id, emp.id) && Objects.equals(this.name, emp.name);
    }

    @Override
    public int hashCode() {
        // Computes hash value for this object
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        // return string rep of Employee object
        return "Employee{" + "id=" + this.id + "name=" + this.name + "}";
    }
}