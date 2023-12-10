package com.mitsurishi.repairdbapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mitsurishi.repairdbapi.data.models.Customer;
import com.mitsurishi.repairdbapi.data.models.Employee;
import com.mitsurishi.repairdbapi.data.models.Ticket;
import com.mitsurishi.repairdbapi.data.payloads.response.MessageResponse;
import com.mitsurishi.repairdbapi.service.CustomerService;
import com.mitsurishi.repairdbapi.service.EmployeeService;
import com.mitsurishi.repairdbapi.service.InvoiceService;
import com.mitsurishi.repairdbapi.service.TicketService;
import com.mitsurishi.repairdbapi.web.RepairDbApiController;

@WebMvcTest(RepairDbApiController.class)
public class RepairDbApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private InvoiceService invoiceService;

	@MockBean
	private TicketService ticketService;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void testGetCustomerById() throws Exception {
		when(customerService.getCustomerById(1)).thenReturn(new Customer("John Doe", "john@example.com", "123456789"));

		mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("123456789"));
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(
				new Customer("John Doe", "john@example.com", "123456789"),
				new Customer("Jane Doe", "jane@example.com", "987654321")));

		mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john@example.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("123456789"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane@example.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("987654321"));
	}

	@Test
	public void testCreateCustomer() throws Exception {
		when(customerService.createCustomer(any(), any(), any())).thenReturn(new MessageResponse("SUCCESSFUL"));

		mockMvc.perform(MockMvcRequestBuilders.post("/customers")
				.param("name", "John Doe")
				.param("email", "john@example.com")
				.param("phoneNumber", "123456789")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		when(customerService.updateCustomer(any(), any(), any(), any())).thenReturn(new MessageResponse("SUCCESSFUL"));

		mockMvc.perform(MockMvcRequestBuilders.put("/customers")
				.param("id", "1")
				.param("name", "John Doe")
				.param("email", "john@example.com")
				.param("phoneNumber", "123456789")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
	}

	@Test
	public void testGetAllTickets() throws Exception {
		when(ticketService.getAllTickets()).thenReturn(Arrays.asList(
				new Ticket(new Employee(), new Customer(), "Device 1", "Issue 1", "Open"),
				new Ticket(new Employee(), new Customer(), "Device 2", "Issue 2", "Closed")));

		mockMvc.perform(MockMvcRequestBuilders.get("/tickets"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].deviceDescription").value("Device 1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].issueDescription").value("Issue 1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("Open"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].deviceDescription").value("Device 2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].issueDescription").value("Issue 2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value("Closed"));
	}

	@Test
	public void testCreateTicket() throws Exception {
		when(ticketService.createTicket(any(), any(), any(), any(), any()))
				.thenReturn(new MessageResponse("SUCCESSFUL"));

		mockMvc.perform(MockMvcRequestBuilders.post("/tickets")
				.param("employeeId", "1")
				.param("customerId", "2")
				.param("deviceDescription", "Device 1")
				.param("issueDescription", "Issue 1")
				.param("status", "Open")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
	}

	@Test
	public void testGetTicketById() throws Exception {
		Ticket sampleTicket = new Ticket(new Employee(), new Customer(), "Sample Device", "Sample Issue", "Open");
		sampleTicket.setId(1);
		when(ticketService.getTicketById(1)).thenReturn(sampleTicket);

		mockMvc.perform(MockMvcRequestBuilders.get("/tickets/{ticketId}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.deviceDescription").value("Sample Device"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.issueDescription").value("Sample Issue"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Open"));
	}

	@Test
	public void testDeleteTicket() throws Exception {
		when(ticketService.deleteTicket(any())).thenReturn(new MessageResponse("SUCCESSFUL"));

		mockMvc.perform(MockMvcRequestBuilders.delete("/tickets")
				.param("ticketId", "1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
	}

	@Test
	public void testCreateNote() throws Exception {
		when(ticketService.createNote(1, 2, "Sample note"))
				.thenReturn(new MessageResponse("Note created successfully"));

		mockMvc.perform(MockMvcRequestBuilders.post("/tickets/notes")
				.param("ticketId", "1")
				.param("employeeId", "2")
				.param("note", "Sample note")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Note created successfully"));
	}

	@Test
	public void testUpdateNote() throws Exception {
		when(ticketService.updateNote(1, "Updated message"))
				.thenReturn(new MessageResponse("Note updated successfully"));

		mockMvc.perform(MockMvcRequestBuilders.put("/tickets/notes")
				.param("noteId", "1")
				.param("message", "Updated message")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Note updated successfully"));
	}

	@Test
	public void testDeleteNote() throws Exception {
		when(ticketService.deleteNote(1)).thenReturn(new MessageResponse("Note deleted successfully"));

		mockMvc.perform(MockMvcRequestBuilders.delete("/tickets/notes")
				.param("noteId", "1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Note deleted successfully"));
	}

	@Test
    public void testCreateEmployee() throws Exception {
        when(employeeService.createEmployee("John Doe", "john_doe", "password123"))
                .thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .param("name", "John Doe")
                .param("userName", "john_doe")
                .param("password", "password123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(1, "Updated Name", "updated_user", "updated_password"))
                .thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.put("/employee")
                .param("id", "1")
                .param("name", "Updated Name")
                .param("userName", "updated_user")
                .param("password", "updated_password")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(1)).thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }

    @Test
    public void testAssignEmployeeToTicket() throws Exception {
        when(employeeService.assignEmployeeToTicket(1, 2)).thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.put("/employee/ticket")
                .param("id", "1")
                .param("ticketId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }

    @Test
    public void testLogin() throws Exception {
        when(employeeService.login("john_doe", "password123")).thenReturn(new Employee("John Doe", "john_doe", "password123"));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/login")
                .param("username", "john_doe")
                .param("password", "password123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("john_doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password123"));
    }

	@Test
    public void testCreateInvoice() throws Exception {
        when(invoiceService.createInvoice(1)).thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.post("/invoice")
                .param("ticketId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }

    @Test
    public void testUpdateInvoice() throws Exception {
        when(invoiceService.updateInvoice(1, "Updated Name", 100, 2, "Type"))
                .thenReturn(new MessageResponse("SUCCESSFUL"));

        mockMvc.perform(MockMvcRequestBuilders.put("/invoice")
                .param("invoiceId", "1")
                .param("name", "Updated Name")
                .param("cost", "100")
                .param("quantity", "2")
                .param("type", "Type")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SUCCESSFUL"));
    }
}