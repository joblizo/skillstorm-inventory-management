package com.inventorymanagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.inventorymanagement.config.DbConfig;
import com.inventorymanagement.models.Inventory;

public class InventoryDao implements IInventoryDao {

	public List<Inventory> findAll() {
		String query = "SELECT * FROM Inventory";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			LinkedList<Inventory> inventorys = new LinkedList<>();
			while(rs.next()) {
				Inventory inventory = new Inventory(
						rs.getInt("id"), 
						rs.getInt("stock"), 
						rs.getInt("price"),
						rs.getInt("product_id"), 
						rs.getInt("warehouse_id") 
						);
				inventorys.add(inventory);
			}
			return inventorys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public Inventory findById(int id) {
		String query = "SELECT * FROM Inventory WHERE id = ?";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return new Inventory(
						rs.getInt("id"), 
						rs.getInt("stock"), 
						rs.getInt("price"),
						rs.getInt("product_id"), 
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
	public Inventory create(Inventory inventory) {
		String query = "INSERT INTO inventory(stock, price, product_id, warehouse_id) VALUES(?,?,?,?);";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, inventory.getStock());
			pStmt.setFloat(2, inventory.getPrice());
			pStmt.setInt(3, inventory.getProductId());
			pStmt.setInt(4, inventory.getWarehouseId());
			
			System.out.println(pStmt);
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					inventory.setId(key);
					return inventory;

				}
			}
			return inventory;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Inventory update(Inventory inventory) {
		String query = "UPDATE inventory SET stock = ?, price = ?, product_id = ?, warehouse_id = ? WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, inventory.getStock());
			pStmt.setFloat(2, inventory.getPrice());
			pStmt.setInt(3, inventory.getProductId());
			pStmt.setInt(4, inventory.getWarehouseId());
			pStmt.setInt(5, inventory.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					inventory.setId(key);
					return inventory;
				}
			}
			return inventory;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Inventory delete(Inventory inventory) {
		String query = "DELETE FROM inventory WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, inventory.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				return inventory;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return null;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM inventory WHERE id = ?;";
		System.out.println("Delete Request!");
		
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			System.out.println(pStmt);
			
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
