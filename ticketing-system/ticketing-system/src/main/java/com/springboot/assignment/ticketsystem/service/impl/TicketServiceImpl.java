package com.springboot.assignment.ticketsystem.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.entity.RoleEntity;
import com.springboot.assignment.ticketsystem.entity.TicketEntity;
import com.springboot.assignment.ticketsystem.exception.TicketNotFoundException;
import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.repository.TicketRepository;
import com.springboot.assignment.ticketsystem.service.EmployeeService;
import com.springboot.assignment.ticketsystem.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EmployeeService userService;
	

	@Override
	public List<Ticket> getAllTicketsForEmployee() {

		List<Ticket> tickets = new ArrayList<Ticket>();

		EmployeeEntity employee = userService.getLoggedInEmployee();
		List<TicketEntity> ticketEntities = ticketRepository.findAllByRaisedBy(employee);
		
		if(ticketEntities == null) {
			throw new TicketNotFoundException("No Ticket Exist For User");
		}

		ticketEntities.stream().forEach(ticketEntity -> {
			Ticket ticket = new Ticket();
			BeanUtils.copyProperties(ticketEntity, ticket);
			tickets.add(ticket);
		});

		return tickets;
	}
	
	@Override
	public void createTicket(Ticket ticket) {
		EmployeeEntity employee = userService.getLoggedInEmployee();
		
		TicketEntity ticketEntity = new TicketEntity();
		BeanUtils.copyProperties(ticket, ticketEntity);
		
		ticketEntity.setRaisedBy(employee);
		ticketEntity.setRaisedOn(LocalDate.now().toString());
    	ticketRepository.save(ticketEntity);
	}



	@Override
	public void updateTicket(Ticket ticket) {
		TicketEntity ticketEntity = null;
		Optional<TicketEntity> ticketEntityOptional =  ticketRepository.findById(ticket.getId());
		if(ticketEntityOptional.isPresent()) {
			ticketEntity = ticketEntityOptional.get();
		}
		BeanUtils.copyProperties(ticket, ticketEntity);
		ticketRepository.save(ticketEntity);
	}


	@Override
	public void deleteTicket(long id) {
		Optional<TicketEntity> ticketEntity =  ticketRepository.findById(id);
		if(ticketEntity.isPresent()) {
			ticketRepository.delete(ticketEntity.get());
		}
	}

	@Override
	public Ticket getTicketById(long id) {
		Ticket ticket = new Ticket();
		if(ticketRepository.findById(id).isPresent()) {
			BeanUtils.copyProperties(ticketRepository.findById(id).get(), ticket);
		}
		return ticket;
	}



	


	
}
