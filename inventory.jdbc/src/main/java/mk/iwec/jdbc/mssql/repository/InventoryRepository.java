package mk.iwec.jdbc.mssql.repository;

import java.util.List;

import mk.iwec.jdbc.mssql.domain.Inventory;

// TODO use generics
public interface InventoryRepository {
	public Inventory findByProductId(Integer productId);
	public List<Inventory> findAll();
	public int insert(Inventory inventory);
	public int deleteById(Integer productId);
	public int update(Inventory inventory);
}