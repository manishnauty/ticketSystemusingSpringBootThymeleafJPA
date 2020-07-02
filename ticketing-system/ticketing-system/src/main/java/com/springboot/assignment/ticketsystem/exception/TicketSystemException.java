package com.springboot.assignment.ticketsystem.exception;

public class TicketSystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketSystemException() {
		super();
	}

	public TicketSystemException(String message) {
		super(message);
	}

}
