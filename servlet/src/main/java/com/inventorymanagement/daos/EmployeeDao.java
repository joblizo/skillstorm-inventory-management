package com.inventorymanagement.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.inventorymanagement.config.DbConfig;
import com.inventorymanagement.models.Employee;

public class EmployeeDao implements IEmployeeDAO {
	
	@Override
	public List<Employee> findAll() {
		String query = "SELECT * FROM EMPLOYEES";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			LinkedList<Employee> employees = new LinkedList<>();
			while(rs.next()) {
				Employee employee = new Employee(
						rs.getInt("id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"),
						rs.getString("address"), 
						rs.getString("phone"), 
						rs.getString("email"),
						rs.getInt("warehouse_id")
						);
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public Employee findById(int id) {
		String query = "SELECT * FROM Employees WHERE id = ?";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return new Employee(
						rs.getInt("id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"),
						rs.getString("address"), 
						rs.getString("phone"), 
						rs.getString("email"),
						rs.getInt("warehouse_id")
						);
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Employee create(Employee employee) {
		String query = "INSERT INTO employees(first_name, last_name, address, phone, email) VALUES(?,?,?,?,?);";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, employee.getFirstName());
			pStmt.setString(2, employee.getLastName());
			pStmt.setString(3, employee.getAddress());
			pStmt.setString(4, employee.getPhone());
			pStmt.setString(5, employee.getEmail());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					employee.setId(key);
					return employee;

				}
			}
			return employee;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Employee update(Employee employee) {
		String query = "UPDATE employees SET first_name = ?, last_name = ?, address = ?, phone = ?, email = ? WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, employee.getFirstName());
			pStmt.setString(2, employee.getLastName());
			pStmt.setString(3, employee.getAddress());
			pStmt.setString(4, employee.getPhone());
			pStmt.setString(5, employee.getEmail());
			pStmt.setInt(6, employee.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					employee.setId(key);
					return employee;
				}
			}
			return employee;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Employee delete(Employee employee) {
		String query = "DELETE FROM employees WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, employee.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				return employee;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return null;

	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM employees WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
					return rowsAffected;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return 0;
	}

}
