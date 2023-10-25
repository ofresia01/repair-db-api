/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitsurishi.repairdbapi.data.repositories.InvoiceRepository;
import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

@Service // Specify class as Service bean for component scanning
public class InvoiceService {
    // Instantiate repository object for JPA data store operations
    @Autowired
    InvoiceRepository invoiceRepository;

    // Method for retrieving single invoice from data store via invoice ID
    public Invoice getSingleInvoice(Integer invoiceId) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice", "ID", invoiceId));
    }
}