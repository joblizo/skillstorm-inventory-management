package com.inventorymanagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.inventorymanagement.config.DbConfig;
import com.inventorymanagement.models.Warehouse;

public class WarehouseDao implements IWarehouseDao {

	@Override
	public List<Warehouse> findAll() {
		String query = "SELECT * FROM warehouses";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			LinkedList<Warehouse> warehouses = new LinkedList<>();
			while(rs.next()) {
				Warehouse warehouse = new Warehouse(
						rs.getInt("id"),
						rs.getInt("capacity"), 
						rs.getString("location"), 
						rs.getInt("status")
						);
				warehouses.add(warehouse);
			}
			return warehouses;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public Warehouse findById(int id) {
		String query = "SELECT * FROM warehouses WHERE id = ?";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return new Warehouse(
						rs.getInt("id"),
						rs.getInt("capacity"), 
						rs.getString("location"), 
						rs.getInt("status")
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
	public Warehouse create(Warehouse warehouse) {
		String query = "INSERT INTO warehouses(capacity, location, status) VALUES(?,?,?);";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, warehouse.getCapacity());
			pStmt.setString(2, warehouse.getLocation());
			pStmt.setInt(3, warehouse.getStatus());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					warehouse.setId(key);
					return warehouse;
				}
			}
			return warehouse;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Warehouse update(Warehouse warehouse) {
		String query = "UPDATE warehouses SET capacity = ?, location = ?, status = ? WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, warehouse.getCapacity());
			pStmt.setString(2, warehouse.getLocation());
			pStmt.setInt(3, warehouse.getStatus());
			pStmt.setInt(4, warehouse.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					warehouse.setId(key);
					return warehouse;
				}
			}
			return warehouse;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Warehouse delete(Warehouse warehouse) {
		String query = "DELETE FROM warehouses WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, warehouse.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				return warehouse;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM warehouses WHERE id = ?;";
		
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
