package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.EmployeeRepository;

@Service // Specify that this is a Service class
public class EmployeeService {
    
    // Instantiate repository object for JPA data store operations, autowire for dependency injection
    @Autowired
    EmployeeRepository employeeRepository;
} 