/*
 * Service-level class for handling Employee-related services.
 */
package com.mitsurishi.repairdbapi.service;

import com.mitsurishi.repairdbapi.data.models.Employee;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.data.repositories.EmployeeRepository;
import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Specify that this is a Service class
public class EmployeeService {

  // Instantiate repository object for JPA data store operations, autowire for
  // dependency injection
  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  TicketRepository ticketRepository;

  /**
   * Method that sends data to JPA for inserting a new employee.
   *
   * @param name     String containing name of employee
   * @param userName String containing employee's username
   * @param password String containing employee's password
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException.
   */
  public MessageResponse createEmployee(String name, String userName, String password) {
    Employee employee = new Employee(name, userName, password);
    employeeRepository.save(employee);
    return new MessageResponse("SUCCESSFUL");
  }

  /**
   * Method that updates an employee in the database via JPA.
   * 
   * @param id       Integer identifier of employee
   * @param name     String containing name of employee
   * @param userName String containing employee's username
   * @param password String containing employee's password
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException
   */
  public MessageResponse updateEmployee(Integer id, String name, String userName, String password) {
    Optional<Employee> employee = employeeRepository.findById(id);
    if (employee.isEmpty()) {
      throw new ResourceNotFoundException("Employee", "employeeID:", id);
    } else {
      employee.get().setName(name);
      employee.get().setUserName(userName);
      employee.get().setPassword(password);
      employeeRepository.save(employee.get());
      return new MessageResponse("SUCCESSFUL");
    }
  }

  /**
   * Method that deletes an employee in the database via JPA.
   * 
   * @param ID Integer identifier of employee
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException
   */
  public MessageResponse deleteEmployee(Integer ID) {
    if (employeeRepository.getReferenceById(ID).getId().equals(ID)) {
      employeeRepository.deleteById(ID);
      return new MessageResponse("SUCCESSFUL");
    } else
      throw new ResourceNotFoundException("Employee", "EmployeeID", ID);
  }

  /**
   * Method that associates a ticket to an employee.
   * 
   * @param id       Integer identifier of employee
   * @param ticketId Integer identifier of ticket
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException
   */
  @Transactional
  public MessageResponse assignEmployeeToTicket(Integer id, Integer ticketId) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", id));

    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new ResourceNotFoundException("Ticket", "ticketId", ticketId));

    if (employee.getTickets().contains(ticket)) {
      return new MessageResponse("Employee already associated with the ticket");
    }

    employee.getTickets().add(ticket);
    employeeRepository.save(employee);

    return new MessageResponse("SUCCESSFUL");
  }

  /**
   * Method that checks for existence of username and password combination in database via JPA.
   * @param username String containing employee's username
   * @param password String containing employee's password
   * @return MessageResponse indicating either success or invalid credentials
   */
  public MessageResponse login(String username, String password) {
    // Find employee by username and password
    Employee employee = employeeRepository.findByUserNameAndPassword(username, password);

    if (employee != null) {
      return new MessageResponse("SUCCESSFUL");
    } else {
      return new MessageResponse("CREDENTIALS INVALID");
    }
  }
}