package com.springboot.assignment.ticketsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping()
	public String getAllTicket(Model model) {
		List<Ticket> tickets = ticketService.getAllTicketsForEmployee();
		model.addAttribute("tickets", tickets);
		return "index";
	}

	
	@GetMapping("/get/{id}")
	@ResponseBody
	public Ticket getTicket(@PathVariable long id, Model model) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return ticketService.getTicketById(id);
	}
	 
	
	
	@PostMapping()
	public String createTicket(@Valid Ticket ticket, Model model) {
		ticketService.createTicket(ticket);
		List<Ticket> tickets = ticketService.getAllTicketsForEmployee();
		model.addAttribute("tickets", tickets);
		return "index";
	}

	@PostMapping("/update")
	public String updateTicket(@Valid Ticket ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			ticket.setId(ticket.getId());
			return "index";
		}
		ticketService.updateTicket(ticket);
		List<Ticket> tickets = ticketService.getAllTicketsForEmployee();
		model.addAttribute("tickets", tickets);
		return "index";
	}

	@GetMapping("/{id}")
	public String deleteTicket(@PathVariable("id") long id, Model model) {
		ticketService.deleteTicket(id);
		List<Ticket> tickets = ticketService.getAllTicketsForEmployee();
		model.addAttribute("tickets", tickets);
		return "index";
	}

}
