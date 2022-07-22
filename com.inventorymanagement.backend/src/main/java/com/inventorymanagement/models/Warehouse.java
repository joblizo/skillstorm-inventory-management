package com.inventorymanagement.models;

public class Warehouse {
	int id;
	int capacity;
	String location;
	int status;
	
	public Warehouse() {
		
	}
	
	public Warehouse(int capacity, String location, int status) {
		super();
		this.capacity = capacity;
		this.location = location;
		this.status = status;
	}

	public Warehouse(int id, int capacity, String location, int status) {
		this(capacity, location, status);
		this.id = id;
	}

	
	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", capacity=" + capacity + ", location=" + location + ", status=" + status + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
