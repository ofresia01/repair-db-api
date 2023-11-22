/*
 * InvoiceItem domain class for JPA-based data store, which allows JPA to handle database interactions.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.util.Objects;

@Entity
public class InvoiceItem {
    // Attrubutes (all private)
    private @Id @GeneratedValue Integer id; // JPA annotations indicating id as auto-generated (via JPA provider) primary key
    private String name;
    private Integer cost;
    private Integer quantity;
    private String type;
    private Integer invoiceId;


    // Default, empty constructor
    InvoiceItem() {}
    
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
        InvoiceItem other = (InvoiceItem)object;
        return Objects.equals(this.id, other.id) && 
            Objects.equals(this.name, other.name) && 
            Objects.equals(this.cost, other.cost) &&
            Objects.equals(this.quantity, other.quantity) &&
            Objects.equals(this.type, other.type) &&
            Objects.equals(this.invoiceId, other.invoiceId);
    }

    @Override
    public int hashCode() {
        // Calculate hash value for this instance
        return Objects.hash(this.id, this.name, this.cost, this.quantity, this.type, this.invoiceId);
    }

    @Override
    public String toString() {
        // return string representation of an InvoiceItem object
        return "InvoiceItem{" +
            "id=" + this.id +
            ", name=" + this.name +
            ", cost=" + this.cost +
            ", quantity=" + this.quantity +
            ", type=" + this.type +
            ", invoiceId=" + this.invoiceId +
            "}";
    }
}
