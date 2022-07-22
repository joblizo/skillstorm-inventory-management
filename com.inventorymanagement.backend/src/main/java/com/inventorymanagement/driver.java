package com.inventorymanagement;
import java.util.List;

import com.inventorymanagement.daos.*;
import com.inventorymanagement.models.Employee;
import com.inventorymanagement.models.Product;

public class driver {

	public static void main(String[] args) {
		IProductDao data = new ProductDao();
		
		List<Product> products = data.findAll();
		System.out.println(products);
		;
		Product product = data.findById(1);
		System.out.println(product);

		Product newProduct = new Product("Adidas Shoe", "Running Shoes", 1, 2);
		newProduct = data.create(newProduct);
		System.out.println(newProduct);
		
		newProduct.setDescription("asdasd");
		newProduct = data.update(newProduct);
		System.out.println(newProduct);
		
		Product deletedProduct = data.delete(newProduct);
		System.out.println(deletedProduct);
		
		int result = data.delete(2);
		System.out.println(result);
		
	}

}
