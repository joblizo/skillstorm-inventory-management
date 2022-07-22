package com.inventorymanagement.models;

public class Product {
	private int id;
	private String name;
	private String description;
	private int category_id;
	private int supplier_id;
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", category_id=" + category_id
				+ ", supplier_id=" + supplier_id + "]";
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Product() {
	
	}
	
	public Product(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Product(int id, String name, String description, int category_id, int supplier_id) {
		this(name, description);
		this.id = id;
		this.category_id = category_id;
		this.supplier_id = supplier_id;
	}
	
	public Product(String name, String description, int category_id, int supplier_id) {
		this(name, description);
		this.category_id = category_id;
		this.supplier_id = supplier_id;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
