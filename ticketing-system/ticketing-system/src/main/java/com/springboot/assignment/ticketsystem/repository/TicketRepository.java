package com.springboot.assignment.ticketsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.entity.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	List<TicketEntity> findAllByRaisedBy(EmployeeEntity entity);

}
