/*
 * Java file that operates as Controller in Model-Controller-Service (MVC) design pattern.
 */
package com.mitsurishi.repairdbapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.HttpExchange;

// Stereotype annotation indicating this is a controller class for handling client requests
@Controller
public class RepairDbApiController {
    /* TEMPLATE FOR ADDING HANDLING TO AN ENDPOINT
     * @GetMapping("/example") { is a Spring annotation for handling GET requests on the "example" endpoint.
     * public bool isExample() { is a function that will execute upon GET request of "example" endpoint.
     *      return true;
     * }
     */
    @GetMapping("/login")
    public Boolean getLogin(Model model) {
        return true;
    }
}