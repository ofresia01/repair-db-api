/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mitsurishi.repairdbapi.data.models.Invoice;
import com.mitsurishi.repairdbapi.service.InvoiceService;

@RestController // Stereotype annotation indicating data returned by each method will be written straight to response body, instead of rendering templates (views)
public class RepairDbApiController {
    // Instantiate service objects for layered request handling
    @Autowired
    InvoiceService invoiceService;

    /*
     * Invoice-related request handling
     */
    @GetMapping("/invoices/find/{id}")
    // Retrieve single invoice via ID provided by request
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getSingleInvoice(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}