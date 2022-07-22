package com.inventorymanagement.daos;

import java.util.List;

import com.inventorymanagement.models.Product;

public interface IProductDao {
	public List<Product> findAll();
	public Product findById(int id);
	public Product create(Product product);
	public Product update(Product product);
	public Product delete(Product product);
	public int delete(int id);
}
