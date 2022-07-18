package com.inventorymanagement.daos;
import java.util.List;
import com.inventorymanagement.models.Employee;

public interface IEmployeeDAO {
	
	public List<Employee> findAll();
	public Employee findById(int id);
	
	public Employee create(Employee employee);
	
	public Employee update(Employee employee);
	
	public Employee delete(Employee employee);
	public int delete(int id);
}
