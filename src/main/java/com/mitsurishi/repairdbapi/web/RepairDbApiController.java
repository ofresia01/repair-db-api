/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.models.Customer;
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
     * ----------------------INVOICE REQUESTS-----------------------
     */
    // Create single invoice via data provided by request

    // Retrieve single invoice via ID provided by request
    @GetMapping("/customers/find/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id){
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customer = customerService.getAllCustomers();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<MessageResponse> createcustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber){
        MessageResponse response = customerService.createCustomer(name,email,phoneNumber);
        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/customers")
    public ResponseEntity<MessageResponse> updateCustomer(@RequestParam Integer id, @RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber){
        MessageResponse response = customerService.updateCustomer(id,name,email,phoneNumber);
        return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    }

    // @PostMapping("/login")
    // public ResponseEntity<MessageResponse> login(@RequestParam String userName, @RequestParam String password){
    //     MessageResponse response = employeeService.login(userName,password);
    //     return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
    // }

    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> getTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    
    // @GetMapping("/invoices/find/{id}")
    // public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Integer id) {
    //     Invoice invoice = invoiceService.getSingleInvoice(id);
    //     return new ResponseEntity<>(invoice, HttpStatus.OK);
    // }

    // // Retrieve all invoices
    // @GetMapping("/invoices/find/all")
    // public ResponseEntity<List<Invoice>> getAllInvoices() {
    //     List<Invoice> allInvoices = invoiceService.getAllInvoices();
    //     return new ResponseEntity<>(allInvoices, HttpStatus.OK);
    // }

    // // Delete invoice of given ID
    // @DeleteMapping("/invoices/{invoiceId}")
    // public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable("invoiceId") Integer invoiceId) {
    //     MessageResponse response = invoiceService.deleteInvoice(invoiceId);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }
}