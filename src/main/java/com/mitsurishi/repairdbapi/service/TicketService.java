/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public MessageResponse createTicket(Integer employee_id, Integer customer_id, String device_desc, String issue_desc, String status,
            Date created_on) {
        Ticket newTicket = new Ticket(employee_id, customer_id, device_desc, issue_desc,status,created_on);
        ticketRepository.save(newTicket);
        return new MessageResponse("SUCCESSFUL");
    }

    /*
     * Method for retrieving a single invoice by its ID.
     * Queries data store with invoice ID and returns it or otherwise returns
     * ResourceNotFoundException.
     */
    public Ticket getSingleInvoice(Integer ticketId) throws ResourceNotFoundException {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "ID", ticketId));
    }

    /*
     * Method for retrieving all invoices.
     * Queries data store for all invoices and returns them.
     * If no invoices exist or are retrievable, throw ResourceNotFoundException.
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /*
     * Method for updating an invoice.
     * Queries for Invoice by given ID.
     * If Invoice exists, update it. Otherwise, throw a ResourceNotFoundException.
     */
    public MessageResponse updateTicket(Integer ticket_id, Integer employee_id, Integer customer_id, String device_desc, String issue_desc, String status,
            Date created_on) throws ResourceNotFoundException {
        Optional<Ticket> oldTicket = ticketRepository.findById(ticket_id);
        if (oldTicket.isEmpty()) {
            throw new ResourceNotFoundException("Invoice", "ID", ticket_id);
        } else {
            oldTicket.get();
            ticketRepository.save(oldTicket.get());
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /*
     * Method for deleting an invoice.
     * Queries for Invoice by given ID. If it exists, it's deleted. Otherwise, a
     * ResourceNotFoundException is thrown.
     */
    public MessageResponse deleteInvoice(Integer ticketID) throws ResourceNotFoundException {
        if (ticketRepository.getReferenceById(ticketID).getId().equals(ticketID)) {
            ticketRepository.deleteById(ticketID);
            return new MessageResponse("SUCCESS");
        } else {
            throw new ResourceNotFoundException("Invoice", "ID", ticketID);
        }
    }
}