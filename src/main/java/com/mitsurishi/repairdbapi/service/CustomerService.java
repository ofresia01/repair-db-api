package com.mitsurishi.repairdbapi.service;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.data.repositories.CustomerRepository;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.models.Customer;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    /**
     * Method that sends data to JPA for inserting a new ticket.
     * 
     * @param name        Customer name
     * @param email        Customer Email
     * @param phoneNumber Customer phone number 
     * @return MessageResponse indicating success, otherwise
     *         ResourceNotFoundException.
     */

     public MessageResponse createCustomer(String name, String email, String phoneNumber){
        Customer cust = new Customer(name, email,phoneNumber);
        customerRepository.save(cust);
        return new MessageResponse("SUCCESSFUL");
    }

    public Customer getCustomerById(Integer ticketId){
        return customerRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("ticket", "ticketId", ticketId));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public MessageResponse updateCustomer(Integer customerID, String name, String email, String phoneNumber){
        Optional<Customer> customer = customerRepository.findById(customerID);
        customer.get().setName(name);
        customer.get().setEmail(email);
        customer.get().setPhoneNumber(phoneNumber);
        customerRepository.save(customer.get());
        return new MessageResponse("SUCCESSFUL");
    }
}
