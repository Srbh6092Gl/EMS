package com.globallogic.ems.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.globallogic.ems.model.Employee;

public class EmployeeRepository {

	//URL for the DB
	static final String url="jdbc:mysql://localhost:3306/GL";
	
	//DB login credentials
	static final String userName="root";
	static final String password="root";
	
	//Declaring connection and statement
	static Connection connection;
	static Statement statement;
	
	public EmployeeRepository(){
		
		//creating connections with DB
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,userName,password);
			statement = connection.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//All from employee table into a list
	public static List<Employee> getAll() {
		
		//Initialing a list of Employee type to store all employee we will get from employee table
		List<Employee> list = new ArrayList<Employee>();
		
		try {
			//Query to get all from employee table
			String selectAll = "SELECT * FROM employee";
			
			//Getting set of rows from table as a set
			ResultSet rs = statement.executeQuery(selectAll);
			
			//For every set
			while(rs.next()) {
				
				//Fetching the data of every column
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String address = rs.getString("address");
				String contactNo = rs.getString("contact_no");
				
				//Creating an object of employee of one row data from employee table
				Employee e = new Employee(firstName,lastName,username,password,address,contactNo);
				
				//Adding object to list
				list.add(e);
			}
		} catch (SQLException e) { //If error occurs in execution
			System.out.println(e.getMessage());
		}
		
		//Returning the list of employee objects fetched from employee table
		return list;
	}
	
	//Add employee to table employee
	public static String addEmployee(Employee employee) {
		
		//Getting values of employee
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String username = employee.getUsername();
		String password = employee.getPassword();
		String address = employee.getAddress();
		String contactNo = employee.getContactNo();
		
		//Query to insert values of employee in employee table
		String addQuery = "INSERT INTO employee VALUES ('"
							+firstName+"','"
							+lastName+"','"
							+username+"','"
							+password+"','"
							+address+"','"
							+contactNo+"')";
		
		try {
			//Executing query
			statement.execute(addQuery);
			System.out.println("SUCCESS");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("FAILURE");
			//Returning Failure as response on encountering an exception
			return "FAILURE";
			
		}
		
		//Returning Success as Response if we reached till here
		return "SUCCESS";
	}
	
	//Delete row with username from employee table
	public static String delete(String username) {
		
		//Query to delete row with username
		String deleteQuery = "DELETE FROM employee WHERE username='"+username+"'";
		try {
			
			//Executing query
			statement.executeUpdate(deleteQuery);
			//Returning Success as Response if we reached till here
			return "DELETE SUCCESS";
			
		} catch (SQLException e) {
			
			System.out.println("DELETE FAIL");
			e.printStackTrace();
			//Returning Failure as response on encountering an exception
			return "DELETE FAIL";
			
		}
	}
	
	//Updating row with username key with values of map
	public static String update(String key, Employee employee) {
		
		//Getting values of employee
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String username = employee.getUsername();
		String password = employee.getPassword();
		String address = employee.getAddress();
		String contactNo = employee.getContactNo();
		
		//Initializing a string to store the changes according to MySQL query format
		String changes ="";
		
		//If firstName is not null, add to changes
		if(firstName != null)
			changes = changes + "first_name='" + firstName +"',";
		
		//If lastName is not null, add to changes
		if(lastName != null)
			changes = changes + "last_name='" + lastName +"',";
		
		//If username is not null, add to changes
		if(username != null)
			changes = changes + "username='" + username +"',";
		
		//If password is not null, add to changes
		if(password != null)
			changes = changes + "password='" + password +"',";
		
		//If address is not null, add to changes
		if(address != null)
			changes = changes + "address='" + address +"',";
		
		//If contactNo is not null, add to changes
		if(contactNo != null)
			changes = changes + "contact_no='" + contactNo +"'";
		
		//Removing a "," from the String if it is present at the last index
		if(changes.charAt(changes.length()-1) == ',')
			changes=changes.substring(0,changes.length()-1);

		//query to update the v at column k
		String updateQuery = "UPDATE employee SET "+changes+" WHERE username='"+key+"'";
		
		try {
			//Executing query
			statement.executeUpdate(updateQuery);
			//Returning Success as Response if we reached till here
			return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
			//Returning Failure as response on encountering an exception at any column
			return "FAILURE";
		}
		
	}
	
}
