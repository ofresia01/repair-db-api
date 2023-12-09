/*
 * Service-level class for handling Customer-related services.
 */
package com.mitsurishi.repairdbapi.service;

import com.mitsurishi.repairdbapi.data.models.Customer;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.data.repositories.CustomerRepository;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {
  @Autowired
  CustomerRepository customerRepository;

  /**
   * Method that sends data to JPA for inserting a new ticket.
   *
   * @param name        String containing name of customer
   * @param email       String containing email of customer
   * @param phoneNumber String containing customer's phone number
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException.
   */
  public MessageResponse createCustomer(String name, String email, String phoneNumber) {
    Customer customer = new Customer(name, email, phoneNumber);
    customerRepository.save(customer);
    return new MessageResponse("SUCCESSFUL");
  }

  /**
   * Method that attempts to retrieve customer from database via JPA.
   * 
   * @param customerID Integer identifier of customer
   * @return Customer if successful retrieval, otherwise ResourceNotFoundException
   */
  public Customer getCustomerById(Integer customerID) {
    return customerRepository
        .findById(customerID)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerID));
  }

  /**
   * Method that returns all customers in database via JPA.
   * 
   * @return List of all customers in the database.
   */
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  /**
   * Method that updates customer in database via JPA.
   * 
   * @param customerID  Integer identifier of customer
   * @param name        String containing name of customer
   * @param email       String containing email of customer
   * @param phoneNumber String containing customer's phone number
   * @return MessageResponse indicating success, otherwise a
   *         ResourceNotFoundException
   */
  public MessageResponse updateCustomer(Integer customerID, String name, String email, String phoneNumber) {
    Optional<Customer> customer = customerRepository.findById(customerID);
    if (customer.isEmpty()) {
      throw new ResourceNotFoundException("Customer", "customerID:", customerID);
    } else {
      customer.get().setName(name);
      customer.get().setEmail(email);
      customer.get().setPhoneNumber(phoneNumber);
      customerRepository.save(customer.get());
      return new MessageResponse("SUCCESSFUL");
    }
  }
}