package com.globallogic.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.ems.model.Employee;
import com.globallogic.ems.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	//Creating instance of EmployeeRepository
	EmployeeRepository employeeRepository = new EmployeeRepository();
	
	//For GET request on employee, returning the list of employee
	@GetMapping
	public String getAll() {
		
		//Getting the list of employee from the repository: EmployeeRepository
		List<Employee> list = employeeRepository.getAll();
		
		//Returning the string value of the list on GET request
		return list.toString();
	}
	
}