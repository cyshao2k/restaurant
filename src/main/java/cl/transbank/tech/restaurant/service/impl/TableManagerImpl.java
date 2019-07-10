package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.transbank.tech.restaurant.dao.OrderDao;
import cl.transbank.tech.restaurant.dao.TableDao;
import cl.transbank.tech.restaurant.entity.DishEntity;
import cl.transbank.tech.restaurant.entity.OrderEntity;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.service.TableManager;
import cl.transbank.tech.restaurant.to.Dish;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@Service
public class TableManagerImpl implements TableManager {
	
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Table> listTables() {
		ArrayList<Table> tables = new ArrayList<>();
		
		Iterable<TableEntity> tableEntities = tableDao.findAll();
		
		for (Iterator<TableEntity> it = tableEntities.iterator(); it.hasNext(); ) {
			TableEntity entity = it.next();
			
			Table table = new Table();
			table.setId(entity.getId());
			table.setStatus(entity.getStatus());
			
			tables.add(table);
		}
			
		return tables;
	}

	@Override
	public Table getTable(int id) throws ItemNotFoundException {
		Table table = new Table();
		
		Optional<TableEntity> entity = tableDao.findById(id);
		
		if (entity.isPresent()) {
			TableEntity tableEntity = entity.get();
			
			table.setId(tableEntity.getId());
			table.setStatus(tableEntity.getStatus());
		} else {
			throw new ItemNotFoundException();
		}
		
		return table;
	}

	@Override
	public Table setStatus(Table table) throws ItemNotFoundException {
		
		this.getTable(table.getId());
		
		TableEntity entity = new TableEntity();
		entity.setId(table.getId());
		entity.setStatus(table.getStatus());
		
		tableDao.save(entity);
		
		return null;
	}

	@Override
	public List<Order> listOrders(Table table) throws ItemNotFoundException {
		List<Order> orders = new ArrayList<>();
		
		this.getTable(table.getId());
		
		List<OrderEntity> entities = null;
		
		entities = orderDao.findByTable_Id(table.getId());
		
		if (!entities.isEmpty()) {
			for (Iterator<OrderEntity> it = entities.iterator(); it.hasNext(); ) {
				OrderEntity entity = it.next();
				
				Order order = new Order();
				order.setId(entity.getId());
				
				List<Dish> dishes = new ArrayList<>();
				Set<DishEntity> dishEntities = entity.getDishes();
				if (!dishEntities.isEmpty()) {
					for (Iterator<DishEntity> dIt = dishEntities.iterator(); dIt.hasNext(); ) {
						DishEntity de = dIt.next();
						
						Dish dish = new Dish();
						dish.setId(de.getId());
						dish.setName(de.getName());
						dish.setCost(de.getCost());
						
						dishes.add(dish);
					}
				}
				
				order.setDishes(dishes.toArray(new Dish[0]));
				
				TableEntity te = entity.getTable();
				Table t = new Table();
				t.setId(te.getId());
				t.setStatus(te.getStatus());
				
				order.setTable(t);
				
				orders.add(order);
			}
		}
		
		return orders;
	}

}
