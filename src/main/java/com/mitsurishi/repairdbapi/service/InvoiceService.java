/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.InvoiceRepository;
import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service // Specify class as Service bean for component scanning
public class InvoiceService {
    // Instantiate repository object for JPA data store operations, autowire for dependency injection
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TicketRepository ticketRepository;

    /**
     * Method that creates an invoice given a ticket.
     * @param ticketId ID of the ticket this invoice belongs to.
     * @return MessageResponse indicating success, otherwise a ResourceNotFoundException.
     */
    public MessageResponse createInvoice(Integer ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isEmpty())
            throw new ResourceNotFoundException("Ticket", "ticketId", ticketId);
        else {
            Invoice invoice = new Invoice(ticket.get());
            invoiceRepository.save(invoice);
            return new MessageResponse("SUCCESSFUL");
        }
    }

    

    
}