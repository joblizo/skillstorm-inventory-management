package com.inventorymanagement.models;

public class Inventory {
	private int id;
	private int stock;
	private float price;
	private int productId;
	private int warehouseId;
	
	public Inventory() {}
	
	public Inventory(int stock,float price,int productId, int warehouseId) {
		super();
		this.stock = stock;
		this.price = price;
		this.productId = productId;
		this.warehouseId = warehouseId;
	}
	
	public Inventory(int id, int stock, float price,int productId, int warehouseId) {
		this(stock, price, productId, warehouseId);
		this.id = id;
		
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", stock=" + stock + ", price=" + price + ", productId=" + productId
				+ ", warehouseId=" + warehouseId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
	
	
	
}
