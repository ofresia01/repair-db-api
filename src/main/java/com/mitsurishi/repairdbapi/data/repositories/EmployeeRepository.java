/*
 * Spring Data JPA repository interface class for Employee. Supports creating, reading, updating, and deleting records against back-end data store.
 * Allows for:
 *   - Creating new Employees
 *   - Updating existing Employees
 *   - Deleting Employees
 *   - Finding Employees (one, all, or by searching with simple to complex parameters)
 */
package com.mitsurishi.repairdbapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mitsurishi.repairdbapi.data.models.Employee;


// Declare interface extension of JpaRepository, specifying domain type as Employee and ID type as Integer
@Repository // Specify class as Repository bean for component scanning
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUserNameAndPassword(String username, String password);
} 