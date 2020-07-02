package com.springboot.assignment.ticketsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "ticket_tbl")
public class TicketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private String description;
	private String priority;
	private String raisedOn;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private EmployeeEntity raisedBy;

	public TicketEntity() {
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

	@JsonManagedReference
	public EmployeeEntity getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(EmployeeEntity raisedBy) {
		this.raisedBy = raisedBy;
	}

}
