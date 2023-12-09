/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mitsurishi.repairdbapi.data.models.Customer;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.service.CustomerService;
import com.mitsurishi.repairdbapi.service.EmployeeService;
import com.mitsurishi.repairdbapi.service.InvoiceService;
import com.mitsurishi.repairdbapi.service.TicketService;

@RestController // Stereotype annotation indicating data returned by each method will be written
                // straight to response body, instead of rendering templates (views)
public class RepairDbApiController {
    // Instantiate service objects for layered request handling, autowire for
    // dependency injection
    @Autowired
    CustomerService customerService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    TicketService ticketService;

    @Autowired
    EmployeeService employeeService;

    /*
     ******************************************************************* CUSTOMER REQUESTS*********************************************************************************
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customer = customerService.getAllCustomers();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<MessageResponse> createCustomer(@RequestParam String name, @RequestParam String email,
            @RequestParam String phoneNumber) {
        MessageResponse response = customerService.createCustomer(name, email, phoneNumber);
        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/customers")
    public ResponseEntity<MessageResponse> updateCustomer(@RequestParam Integer id, @RequestParam String name,
            @RequestParam String email, @RequestParam String phoneNumber) {
        MessageResponse response = customerService.updateCustomer(id, name, email, phoneNumber);
        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }

    /*
     ******************************************************************* TICKET REQUESTS*********************************************************************************
     */
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public ResponseEntity<MessageResponse> createTicket(@RequestParam Integer employeeId,
            @RequestParam Integer customerId, @RequestParam String deviceDescription,
            @RequestParam String issueDescription, @RequestParam String status) {
        MessageResponse response = ticketService.createTicket(employeeId, customerId, deviceDescription,
                issueDescription, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PutMapping("/tickets")
    public ResponseEntity<MessageResponse> updateTicket(@RequestParam Integer ticketId,
            @RequestParam Integer employeeId, @RequestParam Integer customerId, @RequestParam String deviceDescription,
            @RequestParam String issueDescription, @RequestParam String status) {
        MessageResponse response = ticketService.updateTicket(ticketId, employeeId, customerId, deviceDescription,
                issueDescription, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/tickets")
    public ResponseEntity<MessageResponse> deleteTicket(@RequestParam Integer ticketId) {
        MessageResponse response = ticketService.deleteTicket(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/tickets/notes")
    public ResponseEntity<MessageResponse> createNote(@RequestParam Integer ticketId, @RequestParam Integer employeeId,
            @RequestParam String note) {
        MessageResponse response = ticketService.createNote(ticketId, employeeId, note);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/tickets/notes")
    public ResponseEntity<MessageResponse> updateNote(@RequestParam Integer noteId, @RequestParam String message) {
        MessageResponse response = ticketService.updateNote(noteId, message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/tickets/notes")
    public ResponseEntity<MessageResponse> deleteNote(@RequestParam Integer noteId) {
        MessageResponse response = ticketService.deleteNote(noteId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     ******************************************************************* EMPLOYEE REQUESTS *********************************************************************************
     */

    @PostMapping("/employee")
    public ResponseEntity<MessageResponse> createEmployee(@RequestParam String name, @RequestParam String userName,
            @RequestParam String password) {
        MessageResponse response = employeeService.createEmployee(name, userName, password);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/employee")
    public ResponseEntity<MessageResponse> updateEmployee(@RequestParam Integer id, @RequestParam String name,
            @RequestParam String userName, @RequestParam String password) {
        MessageResponse response = employeeService.updateEmployee(id, name, userName, password);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<MessageResponse> deleteEmployee(@RequestParam Integer id) {
        MessageResponse response = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/employee/ticket")
    public ResponseEntity<MessageResponse> assignEmployeeToTicket(@RequestParam Integer id,
            @RequestParam Integer ticketId) {
        MessageResponse response = employeeService.assignEmployeeToTicket(id, ticketId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/login")
    public ResponseEntity<MessageResponse> login(@RequestParam String username, @RequestParam String password) {
        MessageResponse response = employeeService.login(username, password);
        return ResponseEntity.ok(response);
    }

    /*
     ******************************************************************* INVOICE REQUESTS *********************************************************************************
     */

    @PostMapping("/invoice")
    public ResponseEntity<MessageResponse> createInvoice(@RequestParam Integer ticketId) {
        MessageResponse response = invoiceService.createInvoice(ticketId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/invoice")
    public ResponseEntity<MessageResponse> updateInvoice(@RequestParam Integer invoiceId, @RequestParam String name,
            @RequestParam Integer cost, @RequestParam Integer quantity, @RequestParam String type) {
        MessageResponse response = invoiceService.updateInvoice(invoiceId, name, cost, quantity, type);
        return ResponseEntity.ok(response);
    }
}