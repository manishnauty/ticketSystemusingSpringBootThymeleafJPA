package com.springboot.assignment.ticketsystem.service;

import java.util.List;

import com.springboot.assignment.ticketsystem.model.Ticket;

public interface TicketService {

	void createTicket(Ticket ticket);

	void updateTicket(Ticket ticket);

	void deleteTicket(long id);

	List<Ticket> getAllTicketsForEmployee();

	Ticket getTicketById(long id);

}
