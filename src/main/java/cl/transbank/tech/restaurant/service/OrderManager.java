package cl.transbank.tech.restaurant.service;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.TableNotUsedException;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

public interface OrderManager {
	
	public void createOrder(Order order) throws ItemNotFoundException, TableNotUsedException;
	public void deleteOrdersByTable(Table table) throws ItemNotFoundException;

}
