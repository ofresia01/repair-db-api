/*
 * Model describing Ticket table in DB.
 */
package com.mitsurishi.repairdbapi.data.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Ticket {
    // Attrubutes (all private)
    private @Id @GeneratedValue Integer id; // JPA annotations indicating id as auto-generated (via JPA provider) primary key

    // Default, empty constructor

    // Custom constructor with all attributes except ID

    // Getters

    // Setters

    // Overwrite .equals(), .hashCode(), and .toString()
    
}