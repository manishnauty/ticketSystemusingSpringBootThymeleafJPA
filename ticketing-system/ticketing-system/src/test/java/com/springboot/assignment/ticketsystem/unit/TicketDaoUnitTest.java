package com.springboot.assignment.ticketsystem.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.entity.RoleEntity;
import com.springboot.assignment.ticketsystem.entity.TicketEntity;
import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.repository.EmployeeRepository;
import com.springboot.assignment.ticketsystem.repository.TicketRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketDaoUnitTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	
	@Test
	public void testEmployee() {
		employeeRepository.save(loadEmployeeEntity());
		EmployeeEntity employeeEntity = employeeRepository.findByEmail("m@gmail.com");
		assertEquals("m@gmail", employeeEntity.getEmail());
	}
	
	@Test
	public void testTicket() {
		ticketRepository.save(loadTicketEntitiy());
		TicketEntity ticket = ticketRepository.getOne(1l);
		assertNotNull(ticket);
	}
	
	
	private EmployeeEntity loadEmployeeEntity() {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setId(1l);
		employeeEntity.setEmail("m@gmail.com");
		employeeEntity.setFirstName("manish");
		employeeEntity.setLastName("nautiyal");
		employeeEntity.setPassword("test");
		employeeEntity.setUsername("m@gmail.com");
		employeeEntity.setRoles(Arrays.asList(loadRoleEntity()));
		return employeeEntity;
	}
	private RoleEntity loadRoleEntity() {
		RoleEntity roleEntitiy = new RoleEntity();
		roleEntitiy.setId(1l);
		roleEntitiy.setName("ROLE_USER");
		return roleEntitiy;
	}
	
	private TicketEntity loadTicketEntitiy() {
		TicketEntity ticketEntity = new TicketEntity();
		ticketEntity.setId(1l);
		ticketEntity.setPriority("immediate");
		ticketEntity.setSubject("laptop issue");
		ticketEntity.setDescription("laptop not working");
		ticketEntity.setRaisedOn(LocalDate.now().toString());
		ticketEntity.setRaisedBy(loadEmployeeEntity());
		return ticketEntity;
	}
	
	

}
