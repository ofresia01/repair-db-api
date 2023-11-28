/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.InvoiceRepository;
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

    /*
     * Method for creating an invoice.
     * Creates an Invoice object, saves it to InvoiceRepository.
     */
    public MessageResponse createInvoice(Ticket ticket) {
        Invoice newInvoice = new Invoice(ticket);
        invoiceRepository.save(newInvoice);
        return new MessageResponse("SUCCESSFUL");
    }

    /*
     * Method for retrieving a single invoice by its ID.
     * Queries data store with invoice ID and returns it or otherwise returns
     * ResourceNotFoundException.
     */
    public Invoice getSingleInvoice(Integer invoiceId) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "ID", invoiceId));
    }

    /*
     * Method for retrieving all invoices.
     * Queries data store for all invoices and returns them.
     * If no invoices exist or are retrievable, throw ResourceNotFoundException.
     */
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    /*
     * Method for updating an invoice.
     * Queries for Invoice by given ID.
     * If Invoice exists, update it. Otherwise, throw a ResourceNotFoundException.
     */
    public MessageResponse updateInvoice(Integer invoiceId, Ticket ticket, Integer customerId, String customerName,
            Date dateCompleted) throws ResourceNotFoundException {
        Optional<Invoice> oldInvoice = invoiceRepository.findById(invoiceId);
        if (oldInvoice.isEmpty()) {
            throw new ResourceNotFoundException("Invoice", "ID", invoiceId);
        } else {
            oldInvoice.get().setTicket(ticket);
            invoiceRepository.save(oldInvoice.get());
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /*
     * Method for deleting an invoice.
     * Queries for Invoice by given ID. If it exists, it's deleted. Otherwise, a
     * ResourceNotFoundException is thrown.
     */
    public MessageResponse deleteInvoice(Integer invoiceId) throws ResourceNotFoundException {
        if (invoiceRepository.getReferenceById(invoiceId).getId().equals(invoiceId)) {
            invoiceRepository.deleteById(invoiceId);
            return new MessageResponse("SUCCESS");
        } else {
            throw new ResourceNotFoundException("Invoice", "ID", invoiceId);
        }
    }
}