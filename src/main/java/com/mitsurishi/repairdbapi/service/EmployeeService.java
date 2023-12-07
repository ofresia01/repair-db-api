package com.mitsurishi.repairdbapi.service;

import com.mitsurishi.repairdbapi.data.models.Employee;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.data.repositories.EmployeeRepository;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Specify that this is a Service class
public class EmployeeService {

  // Instantiate repository object for JPA data store operations, autowire for dependency injection
  @Autowired
  EmployeeRepository employeeRepository;

  /**
   * Method that sends data to JPA for inserting a new ticket.
   *
   * @param name          Employee name
   * @param userName      Employee Username
   * @param password      Customer phone number
   * @param notes         Assigned Notes
   * @param tickets       Assigned tickets
   * @return MessageResponse indicating success, otherwise
   *         ResourceNotFoundException.
   */

  public MessageResponse createEmployee(
    String name,
    String userName,
    String password
  ) {
    Employee employee = new Employee(name, userName, password);
    employeeRepository.save(employee);
    return new MessageResponse("SUCCESSFUL");
  }

  public MessageResponse updateEmployee(
    Integer id,
    String name,
    String userName,
    String password
  ) {
    Optional<Employee> employee = employeeRepository.findById(id);
    if (employee.isEmpty()) {
      throw new ResourceNotFoundException(
        "Employee",
        "employeeID:",
        id
      );
    } else {
      employee.get().setName(name);
      employee.get().setUserName(userName);
      employee.get().setPassword(password);
      employeeRepository.save(employee.get());
      return new MessageResponse("SUCCESSFUL");
    }
  }

  public MessageResponse deleteEmployee(Integer ID){
    if (employeeRepository.getReferenceById(ID).getId().equals(ID)) {
        employeeRepository.deleteById(ID);
        return new MessageResponse("SUCCESSFUL");
    } else
        throw new ResourceNotFoundException("Employee", "EmployeeID", ID);
  }

//   public MessageResponse assignEmployeeToTicket(Integer employeeID, Integer ticketID){

//   }

//   public MessageResponse login(String userName, String Password){

//   }
}
