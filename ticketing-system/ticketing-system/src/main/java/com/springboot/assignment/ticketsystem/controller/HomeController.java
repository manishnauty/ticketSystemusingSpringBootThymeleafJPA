package com.springboot.assignment.ticketsystem.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.assignment.ticketsystem.entity.TicketEntity;
import com.springboot.assignment.ticketsystem.model.Ticket;
import com.springboot.assignment.ticketsystem.service.TicketService;

@Controller
public class HomeController {
	
	@Autowired
	private TicketService ticketService;
	

    @GetMapping("/")
    public String root(Model model) throws ParseException {
		/*
		 * List <TicketEntity> tickets = new ArrayList<>(); TicketEntity ticket = new
		 * TicketEntity(); ticket.setSubject("issue"); ticket.
		 * setDescription("issue description table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-borderedtable-bordered table-bordered table-bordered table-bordered table-bordered table-bordered table-bordered"
		 * ); DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 * ticket.setRaisedOn(formatter.format(new Date()));
		 * ticket.setPriority("immediate"); ticket.setId(1l); tickets.add(ticket);
		 * tickets.add(ticket); tickets.add(ticket); tickets.add(ticket);
		 */
    	List<Ticket> tickets = ticketService.getAllTicketsForEmployee();
    	model.addAttribute("tickets", tickets);
        return "index";
    }
    
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    @GetMapping("/newticket")
    public String newTicket() {
    	return "newticket";
    }
    
    @GetMapping("/sample")
    public String getSample() {
    	return "sample";
    }
    
    
}
