package cl.transbank.tech.restaurant.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.transbank.tech.restaurant.dao.OrderDao;
import cl.transbank.tech.restaurant.entity.DishEntity;
import cl.transbank.tech.restaurant.entity.OrderEntity;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.TableNotUsedException;
import cl.transbank.tech.restaurant.service.OrderManager;
import cl.transbank.tech.restaurant.service.TableManager;
import cl.transbank.tech.restaurant.to.Dish;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@Service
public class OrderManagerImpl implements OrderManager {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private TableManager tableManager;

	@Override
	public void createOrder(Order order) throws ItemNotFoundException,TableNotUsedException {
		OrderEntity orderEntity = new OrderEntity();
		
		Table table = order.getTable();
		tableManager.getTable(table.getId());
		
		if (!table.getStatus().equalsIgnoreCase("unavailable")) throw new TableNotUsedException();
		
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(table.getId());
		tableEntity.setStatus(table.getStatus());
		
		orderEntity.setTable(tableEntity);
		
		Dish[] dish = order.getDishes();
		Set<DishEntity> dishEntities = new HashSet<>();
		if (dish!=null && dish.length > 0) {
			for (int i=0; i < dish.length; i++) {
				DishEntity de = new DishEntity();
				de.setId(dish[i].getId());
				de.setName(dish[i].getName());
				de.setCost(dish[i].getCost());
				
				dishEntities.add(de);
			}
		}
		
		orderEntity.setDishes(dishEntities);
		
		orderDao.save(orderEntity);
	}

	@Override
	public void deleteOrdersByTable(Table table) throws ItemNotFoundException {
		orderDao.deleteOrdersByTable(table.getId());
	}

}
