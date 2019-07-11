package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cl.transbank.tech.restaurant.dao.OrderDao;
import cl.transbank.tech.restaurant.dao.TableDao;
import cl.transbank.tech.restaurant.entity.DishEntity;
import cl.transbank.tech.restaurant.entity.OrderEntity;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@RunWith(MockitoJUnitRunner.class)
public class TableManagerImplTest {
	
	@Mock
	private TableDao dao;
	
	@Mock
	private OrderDao oDao;
	
	@InjectMocks
	private TableManagerImpl service;
	
	@Test
	public void listTablesTest() {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		List<TableEntity> tableEntities = new ArrayList<>();
		tableEntities.add(tableEntity);
		
		Mockito.when(dao.findAll()).thenReturn(tableEntities);
		
		List<Table> tables = service.listTables();
		
		assert(!tables.isEmpty());
	}
	
	@Test
	public void getTableTest() throws ItemNotFoundException {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		Optional<TableEntity> optional = Optional.of(tableEntity);
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		Table table = service.getTable(1);
		
		assert(table != null);
	}

	@Test (expected = ItemNotFoundException.class)
	public void getTableThrowExceptionTest() throws ItemNotFoundException {
		Optional<TableEntity> optional = Optional.empty();
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		service.getTable(1);
	}
	
	@Test
	public void setStatusTest() throws ItemNotFoundException {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		Optional<TableEntity> optional = Optional.of(tableEntity);
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		Table status = new Table();
		status.setId(1);
		status.setStatus("available");
		
		Mockito.when(dao.save(Mockito.any())).thenReturn(tableEntity);
		
		service.setStatus(status);
	}

	@Test
	public void listOrdersTest()  throws ItemNotFoundException {
		List<OrderEntity> oes = new ArrayList<>();
		
		TableEntity table = new TableEntity();
		table.setId(1);
		table.setStatus("unavailable");
		
		Set<DishEntity> dishes = new HashSet<>();
		DishEntity de = new DishEntity();
		de.setId(1);
		de.setName("Plato");
		de.setCost(1000);
		dishes.add(de);
		
		OrderEntity oe = new OrderEntity();
		oe.setId(1);
		oe.setTable(table);
		oe.setDishes(dishes);
		
		oes.add(oe);
		
		Optional<TableEntity> optional = Optional.of(table);
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		Mockito.when(oDao.findByTable_Id(Mockito.anyInt())).thenReturn(oes);
		
		Table tableParam = new Table();
		tableParam.setId(1);
		tableParam.setStatus("unavailable");
		List<Order> orders = service.listOrders(tableParam);
		
		assert(!orders.isEmpty());
	}
}
