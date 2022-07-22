package com.inventorymanagement.daos;

import java.util.List;
import com.inventorymanagement.models.Inventory;

public interface IInventoryDao {
	public List<Inventory> findAll();
	public Inventory findById(int id);
	
	public Inventory create(Inventory inventory);
	
	public Inventory update(Inventory inventory);
	
	public Inventory delete(Inventory inventory);
	public int delete(int id);
}
