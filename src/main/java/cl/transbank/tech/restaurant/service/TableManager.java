package cl.transbank.tech.restaurant.service;

import java.util.List;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

public interface TableManager {
	
	public List<Table> listTables();
	public Table getTable(int id) throws ItemNotFoundException;
	public Table setStatus(Table table) throws ItemNotFoundException;
	public List<Order> listOrders(Table table) throws ItemNotFoundException;

}
