package com.mitsurishi.repairdbapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Stereotype annotation indicating this is a controller class for handling client requests
@Controller
public class RepairDbApiController {
    // Login endpoint, GET method
    @GetMapping("/login")
    public boolean getLoginStatus() {
        return true;
    }
}