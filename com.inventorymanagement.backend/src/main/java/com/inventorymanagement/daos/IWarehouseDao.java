package com.inventorymanagement.daos;
import java.util.List;
import com.inventorymanagement.models.Warehouse;

public interface IWarehouseDao {
	public List<Warehouse> findAll();
	public Warehouse findById(int id);
	public Warehouse create(Warehouse employee);
	public Warehouse update(Warehouse employee);
	public Warehouse delete(Warehouse employee);
	public int delete(int id);
}
