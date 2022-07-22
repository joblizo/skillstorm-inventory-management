package com.inventorymanagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.inventorymanagement.config.DbConfig;
import com.inventorymanagement.models.Product;
import com.inventorymanagement.models.Product;

public class ProductDao implements IProductDao{

	@Override
	public List<Product> findAll() {
		String query = "SELECT * FROM products";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			LinkedList<Product> products = new LinkedList<>();
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("description"), 
						rs.getInt("category_id"), 
						rs.getInt("supplier_id")
						);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public Product findById(int id) {
		String query = "SELECT * FROM products WHERE id = ?";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return new Product(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("description"), 
						rs.getInt("category_id"), 
						rs.getInt("supplier_id")
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
	public Product create(Product product) {
		String query = "INSERT INTO products(name, description, category_id, supplier_id) VALUES(?,?,?,?);";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, product.getName());
			pStmt.setString(2, product.getDescription());
			pStmt.setFloat(3, product.getCategory_id());
			pStmt.setInt(4, product.getSupplier_id());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					product.setId(key);
					return product;
				}
			}
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Product update(Product product) {
		String query = "UPDATE products SET name = ?, description = ? WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, product.getName());
			pStmt.setString(2, product.getDescription());
			pStmt.setInt(3, product.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = pStmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					product.setId(key);
					return product;
				}
			}
			return product;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	@Override
	public Product delete(Product product) {
		String query = "DELETE FROM products WHERE id = ?;";
		
		try(Connection conn = DbConfig.getInstance().getConnection()){
			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, product.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			if(rowsAffected != 0) {
				return product;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM products WHERE id = ?;";
		
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
