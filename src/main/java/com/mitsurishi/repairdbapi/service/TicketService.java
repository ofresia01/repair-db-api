/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.data.models.Customer;
import com.mitsurishi.repairdbapi.data.models.Employee;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;

import java.util.Date;

@Service // Specify class as Service bean for component scanning
public class TicketService {
    // Instantiate repository object for JPA data store operations, autowire for
    // dependency injection
    @Autowired
    TicketRepository ticketRepository;

    /*
     * Method for creating an invoice.
     * Creates an Invoice object, saves it to InvoiceRepository.
     */
    public MessageResponse createTicket(Employee employee, Customer customer, String device_desc, String issue_desc, String status,
            Date created_on) {
        Ticket newTicket = new Ticket(employee, customer, device_desc, issue_desc, status, created_on);
        ticketRepository.save(newTicket);
        return new MessageResponse("SUCCESSFUL");
    }
}