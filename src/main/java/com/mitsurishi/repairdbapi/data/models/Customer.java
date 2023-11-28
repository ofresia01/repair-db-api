/*
 * Customer domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {
    // Private member variables
    // Annotations denoting primary key of entity with automatic generation strategy
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "email", nullable = false, unique = false)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = false)
    private String phone_number;

    // Default, empty constructor
    Customer() {
    }

    // Custom constructor with all attributes except ID
    Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phone_number = phoneNumber;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    // Override .equals(), .hashCode(), and .toString()
    @Override
    public boolean equals(Object object) {
        // Is object the same instance?
        if (this == object)
            return true;

        // Is object of the Customer type?
        if (!(object instanceof Customer))
            return false;

        // Cast object to Customer, check if all attributes are equal
        Customer customer = (Customer) object;
        return Objects.equals(this.id, customer.getId()) && Objects.equals(this.name, customer.getName())
                && Objects.equals(this.email, customer.getEmail())
                && Objects.equals(this.phone_number, customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        // Computes hash value for this object
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", phone_number='" + getPhoneNumber() + "'" +
                "}";
    }
}