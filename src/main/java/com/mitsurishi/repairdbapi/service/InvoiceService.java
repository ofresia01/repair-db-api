/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.InvoiceItemRepository;
import com.mitsurishi.repairdbapi.data.repositories.InvoiceRepository;
import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.data.models.InvoiceItem;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

import java.util.Optional;

@Service // Specify class as Service bean for component scanning
public class InvoiceService {
    // Instantiate repository object for JPA data store operations, autowire for
    // dependency injection
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    /**
     * Method that creates an invoice given a ticket.
     * 
     * @param ticketId ID of the ticket this invoice belongs to.
     * @return MessageResponse indicating success, otherwise a
     *         ResourceNotFoundException.
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

    /**
     * Method that inserts an InvoiceItem (associated with an invoice) in database
     * via JPA.
     * 
     * @param invoiceId Integer identifier of invoice in database
     * @param name      String containing name of subject of InvoiceItem
     * @param cost      Integer containing cost of InvoiceItem subject
     * @param quantity  Integer containing quanitity of InvoiceItem subject
     * @param type      String indicating type of InvoiceItem
     * @return MessageResponse indicating success
     */
    public MessageResponse updateInvoice(Integer invoiceId, String name, Integer cost, Integer quantity, String type) {
        // Find the invoice by ID
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalInvoice.isPresent()) {
            // Create a new InvoiceItem
            InvoiceItem invoiceItem = new InvoiceItem(name, cost, quantity, type, optionalInvoice.get());

            // Save the InvoiceItem
            invoiceItemRepository.save(invoiceItem);

            return new MessageResponse("SUCCESSFUL");
        } else {
            throw new ResourceNotFoundException("Invoice", "invoiceId", invoiceId);
        }
    }
}