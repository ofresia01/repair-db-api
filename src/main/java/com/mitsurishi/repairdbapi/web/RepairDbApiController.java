/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.service.InvoiceService;
import com.mitsurishi.repairdbapi.service.TicketService;

@RestController // Stereotype annotation indicating data returned by each method will be written
                // straight to response body, instead of rendering templates (views)
public class RepairDbApiController {
    // Instantiate service objects for layered request handling, autowire for
    // dependency injection
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    TicketService ticketService;

    @PostMapping("/tickets/{employeeId}/{customerId}/{deviceDescription}/{issueDescription}/{status}/{createdOn}")
    public ResponseEntity<MessageResponse> createTicket(@PathVariable Integer employeeId, @PathVariable Integer customerId, @PathVariable String deviceDescription, @PathVariable String issueDescription, @PathVariable String status) {
        MessageResponse response = ticketService.createTicket(employeeId, customerId, deviceDescription, issueDescription, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/tickets/{ticketId}/{employeeId}/{customerId}/{deviceDescription}/{issueDescription}/{status}/{createdOn}")
    public ResponseEntity<MessageResponse> updateTicket(@PathVariable Integer ticketId, @PathVariable Integer employeeId, @PathVariable Integer customerId, @PathVariable String deviceDescription, @PathVariable String issueDescription, @PathVariable String status) {
        MessageResponse response = ticketService.updateTicket(ticketId, employeeId, customerId, deviceDescription, issueDescription, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}