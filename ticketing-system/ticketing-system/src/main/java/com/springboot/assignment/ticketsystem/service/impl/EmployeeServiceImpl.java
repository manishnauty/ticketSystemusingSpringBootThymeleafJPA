package com.springboot.assignment.ticketsystem.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.entity.RoleEntity;
import com.springboot.assignment.ticketsystem.model.Employee;
import com.springboot.assignment.ticketsystem.repository.EmployeeRepository;
import com.springboot.assignment.ticketsystem.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public EmployeeEntity findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

   
    public EmployeeEntity save(Employee registration){
        EmployeeEntity user = new EmployeeEntity();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new RoleEntity("ROLE_USER")));
        return employeeRepository.save(user);
    }

    @Override
    @Cacheable("employee")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeEntity employee = employeeRepository.findByEmail(email);
        if (employee == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employee.getEmail(),
        		employee.getPassword(),
                mapRolesToAuthorities(employee.getRoles()));
    }
    
    @Cacheable("employeeDb")
	public EmployeeEntity getLoggedInEmployee() {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {

			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			EmployeeEntity employee = employeeRepository.findByEmail(userDetails.getUsername());
			return employee;

		}
		return null;
	}
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
