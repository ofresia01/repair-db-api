/*
 * Controller class that wraps our data store with a web layer via Spring MVC, with Spring Boot handling most infrastructure code.
 */
package com.mitsurishi.repairdbapi.web;

import org.springframework.web.bind.annotation.RestController;
import com.mitsurishi.repairdbapi.data.repositories.InvoiceRepository;

@RestController // Stereotype annotation indicating data returned by each method will be written straight to response body, instead of rendering templates (views)
public class RepairDbApiController {
    private final InvoiceRepository invoiceRepository;
}