/*
 * InvoiceItem domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Entity
public class InvoiceItem {
    // Attributes (all private)
    // JPA annotations indicating id as auto-generated (via JPA provider) primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id; 

    @Column(name = "invoice_id", nullable = false, unique = true)
    private Integer invoiceId;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "cost", nullable = false, unique = false)
    private Integer cost;

    @Column(name = "quantity", nullable = false, unique = false)
    private Integer quantity;

    @Column(name = "type", nullable = false, unique = false)
    private String type;

    // Default, empty constructor
    InvoiceItem() {
    }

    // Custom constructor with all attributes except ID
    InvoiceItem(String name, Integer cost, Integer quantity, String type, Integer invoiceId) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
        this.invoiceId = invoiceId;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCost() {
        return this.cost;

    }

    public Integer getQuantity() {
        return this.quantity;

    }

    public String getType() {
        return this.type;

    }

    public Integer getInvoiceId() {
        return this.invoiceId;

    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    // Overwrite .equals(), .hashCode(), and .toString()

    @Override
    public boolean equals(Object object) {
        // Is object the same instance?
        if (this == object)
            return true;

        // Is object of InvoiceItem type?
        if (!(object instanceof InvoiceItem))
            return false;

        // Cast object to InvoiceItem type then compare all attributes
        InvoiceItem other = (InvoiceItem) object;
        return Objects.equals(getId(), other.getId()) &&
                Objects.equals(getInvoiceId(), other.getInvoiceId()) &&
                Objects.equals(getName(), other.getName()) &&
                Objects.equals(getCost(), other.getCost()) &&
                Objects.equals(getQuantity(), other.getQuantity()) &&
                Objects.equals(getType(), other.getType());
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(getId(), getInvoiceId(), getName(), getCost(), getQuantity(), getType());
    }

    @Override
    public String toString() {
        // return string representation of an InvoiceItem object
        return "InvoiceItem{" +
                "id=" + getId() +
                "invoiceId=" + getInvoiceId() +
                ", name=" + getName() +
                ", cost=" + getCost() +
                ", quantity=" + getQuantity() +
                ", type=" + getType() +
                "}";
    }
}
