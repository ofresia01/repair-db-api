/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.service.InvoiceService;

@RestController // Stereotype annotation indicating data returned by each method will be written
                // straight to response body, instead of rendering templates (views)
public class RepairDbApiController {
    // Instantiate service objects for layered request handling, autowire for
    // dependency injection
    @Autowired
    InvoiceService invoiceService;

    /*
     * ----------------------INVOICE REQUESTS-----------------------
     */
    // Create single invoice via data provided by request
    @PostMapping("/invoices/{ticketId}/{customerId}/{customerName}/{dateCompleted}")
    public ResponseEntity<MessageResponse> createInvoice(@PathVariable("ticketId") Integer ticketId,
            @PathVariable("customerId") Integer customerId, @PathVariable("customerName") String customerName,
            @PathVariable("dateCompleted") Date dateCompleted) {
        MessageResponse response = invoiceService.createInvoice(ticketId, customerId, customerName, dateCompleted);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Retrieve single invoice via ID provided by request
    @GetMapping("/invoices/find/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getSingleInvoice(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    // Retrieve all invoices
    @GetMapping("/invoices/find/all")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> allInvoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(allInvoices, HttpStatus.OK);
    }

    // Update invoice of given ID
    @PutMapping("/invoices/{invoiceId}/{ticketId}/{customerId}/{customerName}/{dateCompleted}")
    public ResponseEntity<MessageResponse> updateInvoice(@PathVariable("invoiceId") Integer invoiceId,
            @PathVariable("ticketId") Integer ticketId, @PathVariable("customerId") Integer customerId,
            @PathVariable("customerName") String customerName, @PathVariable("dateCompleted") Date dateCompleted) {
        MessageResponse response = invoiceService.updateInvoice(invoiceId, ticketId, customerId, customerName,
                dateCompleted);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete invoice of given ID
    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable("invoiceId") Integer invoiceId) {
        MessageResponse response = invoiceService.deleteInvoice(invoiceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}