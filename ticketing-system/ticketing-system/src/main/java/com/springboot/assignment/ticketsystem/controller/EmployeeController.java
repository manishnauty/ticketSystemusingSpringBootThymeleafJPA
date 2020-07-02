package com.springboot.assignment.ticketsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.assignment.ticketsystem.entity.EmployeeEntity;
import com.springboot.assignment.ticketsystem.exception.EmployeeExistException;
import com.springboot.assignment.ticketsystem.model.Employee;
import com.springboot.assignment.ticketsystem.service.EmployeeService;

@Controller
@RequestMapping("/registration")
public class EmployeeController {

    @Autowired
    private EmployeeService userService;

    @ModelAttribute("employee")
    public Employee userRegistrationDto() {
        return new Employee();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("employee") @Valid Employee employee,
                                      BindingResult result){

        EmployeeEntity existing = userService.findByEmail(employee.getEmail());
        if (existing != null){
            throw new EmployeeExistException("There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(employee);
        return "index";
    }

}
