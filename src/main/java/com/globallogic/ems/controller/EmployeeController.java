package com.globallogic.ems.controller;

import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@PostMapping
	public String addEmployee(@RequestBody Map<String,Object> map) {

		//Fetching values from map
		String firstName=map.get("first_name").toString();
		String lastName=map.get("last_name").toString();
		String username=map.get("username").toString();
		String password=map.get("password").toString();
		String address=map.get("address").toString();
		String contactNo=map.get("contact_no").toString();
		
		//Creating object of Employee from map values
		Employee e = new Employee(firstName,lastName,username,password,address,contactNo);
		System.out.println(e.toString());
		
		//Adding employee to Repository
		return employeeRepository.addEmployee(e);
	}
	
	//For DELETE request on employee with {username}
	@RequestMapping(method = RequestMethod.DELETE, value="/{username}")
	public String delete(@PathVariable("username") String username) {
		//Deleteing entry with username
		return employeeRepository.delete(username);
	}
	
	//For POST request on employee with {username}
	@RequestMapping(method = RequestMethod.PUT, value="/{username}")
	public void update(@PathVariable("username") String key, @RequestBody Map<String,Object> map) {
		//Updating the row with key as username with the values of map
		employeeRepository.update(key, map);
	}

}