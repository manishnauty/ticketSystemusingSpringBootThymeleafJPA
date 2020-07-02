package com.springboot.assignment.ticketsystem.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;

import com.springboot.assignment.ticketsystem.Application;
import com.springboot.assignment.ticketsystem.model.Employee;
import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.service.EmployeeService;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TicketSystemApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private EmployeeService employeeService;

	@LocalServerPort
	private int port;


	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}
	
	@Test
	@Order(1)
	public void testRegisterUserAccount() {
		Employee employee = loadEmployee();
		employeeService.save(employee);
	}

	@Test
	@Order(2)
	public void testCreateTicket() {

		Ticket ticket = loadTicket();

		ResponseEntity<Ticket> postResponse = restTemplate.postForEntity(getRootUrl() + "/ticket", ticket,
				Ticket.class);

		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	@Order(3)
	public void testGetAllTicket() {
		Ticket[] ticket = restTemplate.getForObject(getRootUrl() + "/ticket", Ticket[].class);
		Assert.assertNotNull(ticket);
	}

	@Test
	@Order(4)
	public void testUpdateTicket() {
		Ticket ticket = loadTicket();
		ticket.setSubject("new issue");
		ticket.setDescription("issue has been fixed");

		ResponseEntity<Ticket> postResponse = restTemplate.postForEntity(getRootUrl() + "/ticket/update", ticket,
				Ticket.class);

		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
	

	@Test
	@Order(5)
	public void testGetTicketByIdAndDelete() {
		restTemplate.delete(getRootUrl() + "/ticket/"+1);
		
		
		int id = 1;
        Ticket ticket = restTemplate.getForObject(getRootUrl() + "/ticket/" + id, Ticket.class);
        assertNotNull(ticket);
        restTemplate.delete(getRootUrl() + "/employees/" + id);
        try {
        	ticket = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Ticket.class);
        } catch (final HttpClientErrorException e) {
             assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}
	
	private Ticket loadTicket() {
		Ticket ticket = new Ticket();
		ticket.setSubject("issue");
		ticket.setDescription("laptop issue");
		ticket.setPriority("immediate");
		ticket.setRaisedOn(LocalDate.now().toString());
		return ticket;
	}
	private Employee loadEmployee() {
		Employee employee = new Employee();
		employee.setEmail("test@gmail.com");
		employee.setFirstName("firstname");
		employee.setLastName("lastname");
		employee.setConfirmEmail("test@gmail.com");
		employee.setPassword("test");
		employee.setConfirmEmail("test");
		return employee;
	}

}
