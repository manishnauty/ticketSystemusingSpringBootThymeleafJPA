package com.springboot.assignment.ticketsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.model.Employee;

public interface EmployeeService extends UserDetailsService {

    EmployeeEntity findByEmail(String email);

    EmployeeEntity save(Employee registration);
    
    public EmployeeEntity getLoggedInEmployee();
}
