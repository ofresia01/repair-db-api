/*
 * Service-level class for handling Invoice-related services.
 */
package com.mitsurishi.repairdbapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitsurishi.repairdbapi.data.repositories.TicketRepository;
import com.mitsurishi.repairdbapi.data.repositories.EmployeeRepository;
import com.mitsurishi.repairdbapi.data.repositories.CustomerRepository;
import com.mitsurishi.repairdbapi.data.repositories.NoteRepository;
import com.mitsurishi.repairdbapi.exception.ResourceNotFoundException;
import com.mitsurishi.repairdbapi.data.models.Customer;
import com.mitsurishi.repairdbapi.data.models.Employee;
import com.mitsurishi.repairdbapi.data.models.Note;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;

import java.util.List;
import java.util.Optional;

@Service // Specify class as Service bean for component scanning
public class TicketService {
    // Instantiate repository objects for JPA data store operations, autowire for
    // dependency injection
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    NoteRepository noteRepository;

    /**
     * Method that sends data to JPA for inserting a new ticket.
     * 
     * @param employeeId        ID of employee to be associated with this ticket.
     * @param customerId        Id of customer to be associated with this ticket.
     * @param deviceDescription Description of the device for this ticket.
     * @param issueDescription  Description of the issue for this ticket.
     * @param status            Current status of this ticket.
     * @param createdOn         Date object describing the time this ticket was
     *                          created.
     * @return MessageResponse indicating success, otherwise
     *         ResourceNotFoundException.
     */
    public MessageResponse createTicket(Integer employeeId, Integer customerId, String deviceDescription,
            String issueDescription, String status) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (employee.isEmpty())
            throw new ResourceNotFoundException("employee", "employeeId", employeeId);
        else if (customer.isEmpty())
            throw new ResourceNotFoundException("customer", "customerId", customerId);
        else {
            Ticket newTicket = new Ticket(employee.get(), customer.get(), deviceDescription, issueDescription, status);
            ticketRepository.save(newTicket);
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /**
     * Method that retrieves single ticket from JPA via ticket ID.
     * 
     * @param ticketId Id of the ticket.
     * @return The ticket belonging to that ID or else a ResourceNotFoundException.
     */
    public Ticket getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("ticket", "ticketId", ticketId));
    }

    /**
     * Method that retrieves all tickets from JPA.
     * 
     * @return List object containing all tickets retrievable.
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Method that updates a ticket in JPA.
     * 
     * @param ticketId          ID of the ticket to be updated.
     * @param employeeId        ID of employee associated with this ticket.
     * @param customerId        ID of customer associated with this ticket.
     * @param deviceDescription Description of the device for this ticket.
     * @param issueDescription  Description of the issue for this ticket.
     * @param status            Current status of this ticket.
     * @param createdOn         Date object describing the time this ticket was
     *                          created.
     * @return MessageResponse indicating success, otherwise throws
     *         ResourceNotFoundException
     */
    public MessageResponse updateTicket(Integer ticketId, Integer employeeId, Integer customerId,
            String deviceDescription, String issueDescription, String status) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (ticket.isEmpty())
            throw new ResourceNotFoundException("ticket", "ticketId", ticketId);
        else if (employee.isEmpty())
            throw new ResourceNotFoundException("employee", "employeeId", employeeId);
        else if (customer.isEmpty())
            throw new ResourceNotFoundException("customer", "customerId", customerId);
        else {
            ticket.get().setEmployee(employee.get());
            ticket.get().setCustomer(customer.get());
            ticket.get().setDeviceDescription(deviceDescription);
            ticket.get().setIssueDescription(issueDescription);
            ticket.get().setStatus(status);
            ticketRepository.save(ticket.get());
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /**
     * Method that deletes ticket from JPA.
     * 
     * @param ticketId ID of the ticket to be deleted.
     * @return MessageResponse indicating success, otherwise a
     *         ResourceNotFoundException.
     */
    public MessageResponse deleteTicket(Integer ticketId) {
        if (ticketRepository.getReferenceById(ticketId).getId().equals(ticketId)) {
            ticketRepository.deleteById(ticketId);
            return new MessageResponse("SUCCESSFUL");
        } else
            throw new ResourceNotFoundException("ticket", "ticketId", ticketId);
    }

    /**
     * Method that inserts a new note object to JPA.
     * 
     * @param ticketId   ID of the ticket associated with this note.
     * @param employeeId ID of the employee associated with this note.
     * @param note       The message to be saved.
     * @return MessageResponse indicating success, otherwise
     *         ResourceNotFoundException.
     */
    public MessageResponse createNote(Integer ticketId, Integer employeeId, String note) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (ticket.isEmpty())
            throw new ResourceNotFoundException("ticket", "ticketId", ticketId);
        else if (employee.isEmpty())
            throw new ResourceNotFoundException("employee", "employeeId", employeeId);
        else {
            Note noteObject = new Note(ticket.get(), employee.get(), note);
            noteRepository.save(noteObject);
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /**
     * Method that updates a note in JPA.
     * 
     * @param noteId  ID of the note to be updated.
     * @param message New message to be contained within the note.
     * @return MessageResponse indicating success, otherwise
     *         ResourceNotFoundException.
     */
    public MessageResponse updateNote(Integer noteId, String message) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isEmpty())
            throw new ResourceNotFoundException("note", "noteId", noteId);
        else {
            note.get().setNote(message);
            noteRepository.save(note.get());
            return new MessageResponse("SUCCESSFUL");
        }
    }

    /**
     * Method that deletes note from JPA.
     * 
     * @param noteId ID of the note to be deleted.
     * @return MessageResponse indicating success, otherwise
     *         ResourceNotFoundException.
     */
    public MessageResponse deleteNote(Integer noteId) {
        if (noteRepository.getReferenceById(noteId).getId().equals(noteId)) {
            noteRepository.deleteById(noteId);
            return new MessageResponse("SUCCESSFUL");
        } else
            throw new ResourceNotFoundException("note", "noteId", noteId);
    }
}