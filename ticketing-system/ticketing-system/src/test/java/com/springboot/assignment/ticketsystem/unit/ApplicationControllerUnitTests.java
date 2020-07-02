package com.springboot.assignment.ticketsystem.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.assignment.ticketsystem.controller.TicketController;
import com.springboot.assignment.ticketsystem.model.Employee;
import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerUnitTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreateTicket() throws Exception {
		/*
		 * Employee employee = loadEmployee(); employeeService.save(employee);
		 */
		
		
		String uri = "/ticket";
		Ticket ticket = loadTicket();

		String inputJson = mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}
	
	@Test
	public void testGetAllTicket() throws Exception {
		String uri = "/ticket";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testUpdateTicket() throws Exception {
		String uri = "/ticket/update";
		Ticket ticket = loadTicket();
		ticket.setSubject("new issue");
		ticket.setDescription("closing issue");

		String inputJson = mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testDeleteTicket() throws Exception {
		String uri = "/ticket/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
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
	
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	private <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
