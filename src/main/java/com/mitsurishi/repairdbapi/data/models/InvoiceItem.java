/*
 * InvoiceItem domain class for Jakarta Persistence Layer (JPL), which allows Java Persistence API (JPA) to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "\"Invoice_Items\"")
public class InvoiceItem {
    // Attributes (all private)
    // JPL annotations indicating id as auto-generated (via JPL provider) primary
    // key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @JsonIgnore
    private Invoice invoice;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "item_type", nullable = false)
    private String type;

    // Default, empty constructor
    public InvoiceItem() {
    }

    // Custom constructor with all attributes except ID
    public InvoiceItem(String name, Integer cost, Integer quantity, String type, Invoice invoice) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
        this.invoice = invoice;
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

    public Invoice getInvoice() {
        return this.invoice;
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

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
                Objects.equals(getInvoice(), other.getInvoice()) &&
                Objects.equals(getName(), other.getName()) &&
                Objects.equals(getCost(), other.getCost()) &&
                Objects.equals(getQuantity(), other.getQuantity()) &&
                Objects.equals(getType(), other.getType());
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(getId(), getInvoice(), getName(), getCost(), getQuantity(), getType());
    }

    @Override
    public String toString() {
        // return string representation of an InvoiceItem object
        return "InvoiceItem{" +
                "id=" + getId() +
                "invoice=" + getInvoice().toString() +
                ", name=" + getName() +
                ", cost=" + getCost() +
                ", quantity=" + getQuantity() +
                ", type=" + getType() +
                "}";
    }
}
