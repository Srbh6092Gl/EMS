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
import org.springframework.web.bind.annotation.RequestParam;
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

	//For POST request on employee, returning SUCCESS if added employee or no error in request
	@PostMapping
	public String addEmployee(@RequestParam("first_name") String firstName,
							  @RequestParam("last_name") String lastName,
							  @RequestParam("username") String username,
							  @RequestParam("password") String password,
							  @RequestParam("address") String address,
							  @RequestParam("contact_no") String contactNo) {

		//If all params are null, returning response for it
		if(firstName==null&&lastName==null&&username==null&&password==null&&address==null&&contactNo==null)
			return "No params given to change";
		
		//Otherwise
		//Creating object of Employee from params received from request
		Employee e = new Employee(firstName,lastName,username,password,address,contactNo);
		
		//Adding employee to Repository
		return employeeRepository.addEmployee(e);
	}
	
	//For DELETE request on employee with {username}, returning SUCCESS if deleted employee or no error in request
	@RequestMapping(method = RequestMethod.DELETE, value="/{username}")
	public String delete(@PathVariable("username") String username) {
		
		//Deleteing entry with username
		return employeeRepository.delete(username);
		
	}
	
	//For PUT request on employee with {username}, returning SUCCESS if updated employee or no error in request
	@RequestMapping(method = RequestMethod.PUT, value="/{username}")
	public String update(@PathVariable("username") String key,
					   @RequestParam(required=false,name="first_name") String firstName,
					   @RequestParam(required=false,name="last_name") String lastName,
					   @RequestParam(required=false,name="username") String username,
					   @RequestParam(required=false,name="password") String password,
					   @RequestParam(required=false,name="address") String address,
					   @RequestParam(required=false,name="contact_no") String contactNo) {
		
		//If all params are null, returning response for it
		if(firstName==null&&lastName==null&&username==null&&password==null&&address==null&&contactNo==null)
			return "No params given to change";
		
		//Otherwise
		//Creating object of Employee from params received from request
		Employee e = new Employee(firstName,lastName,username,password,address,contactNo);
		
		//Updating the row with key as username with the values of map
		return employeeRepository.update(key, e);
	}

}