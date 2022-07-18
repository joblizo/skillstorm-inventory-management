package com.inventorymanagement;
import java.util.List;

import com.inventorymanagement.daos.*;
import com.inventorymanagement.models.Employee;

public class driver {

	public static void main(String[] args) {
		EmployeeDao data = new EmployeeDao();
		
		List<Employee> employees = data.findAll();
		System.out.println(employees);
		;
		Employee employee = data.findById(1);
		System.out.println(employee);

		Employee newEmployee = new Employee("Kyla", "Lopez", "San Juan", "123213", "kylaflopez@gmail.com");
		newEmployee = data.create(newEmployee);
		System.out.println(newEmployee);
		
		newEmployee.setPhone("092653322");
		newEmployee = data.update(newEmployee);
		System.out.println(newEmployee);
		
		Employee deletedEmployee = data.delete(newEmployee);
		System.out.println(deletedEmployee);
		
		
		int result = data.delete(5);
		System.out.println(result);
		
	}

}
