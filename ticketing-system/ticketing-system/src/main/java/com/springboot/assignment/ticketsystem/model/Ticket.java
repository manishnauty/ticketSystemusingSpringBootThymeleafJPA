package com.springboot.assignment.ticketsystem.model;

import java.io.Serializable;

public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String subject;
	private String description;
	private String priority;
	private String raisedOn;

	public Ticket() {
	}

	public Ticket(Long id, String subject, String description, String priority, String raisedOn) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.priority = priority;
		this.raisedOn = raisedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(String raisedOn) {
		this.raisedOn = raisedOn;
	}

}
