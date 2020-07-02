package com.springboot.assignment.ticketsystem.exception;

public class EmployeeExistException extends TicketSystemException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeExistException() {
		super();
	}

	public EmployeeExistException(String message) {
		super(message);
	}

}
