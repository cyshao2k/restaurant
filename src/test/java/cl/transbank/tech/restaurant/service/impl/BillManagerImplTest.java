package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cl.transbank.tech.restaurant.dao.BillDao;
import cl.transbank.tech.restaurant.entity.BillEntity;
import cl.transbank.tech.restaurant.entity.BillItemEntity;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.NoOrdersFoundException;
import cl.transbank.tech.restaurant.service.OrderManager;
import cl.transbank.tech.restaurant.service.TableManager;
import cl.transbank.tech.restaurant.to.Bill;
import cl.transbank.tech.restaurant.to.Dish;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@RunWith(MockitoJUnitRunner.class)
public class BillManagerImplTest {

	@Mock
	private TableManager tableManager;
	
	@Mock
	private OrderManager orderManager;
	
	@Mock
	private BillDao billDao;
	
	@InjectMocks
	private BillManagerImpl service;
	
	@Test
	public void payBillTest() throws ItemNotFoundException, NoOrdersFoundException {
		Table table = new Table();
		table.setId(1);
		table.setStatus("available");
		
		Dish dish = new Dish();
		dish.setId(1);
		dish.setName("Plato");
		dish.setCost(1000);
		Dish[] dishes = {dish};
		
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setId(1);
		order.setTable(table);
		order.setDishes(dishes);
		orders.add(order);
		
		BillEntity billEntity = new BillEntity();
		billEntity.setId(1);
		
		Mockito.when(tableManager.getTable(Mockito.anyInt())).thenReturn(table);
		Mockito.when(tableManager.listOrders(Mockito.any())).thenReturn(orders);
		Mockito.when(billDao.save(Mockito.any())).thenReturn(billEntity);
		Mockito.doNothing().when(orderManager).deleteOrdersByTable(Mockito.any());
		Mockito.when(tableManager.setStatus(Mockito.any())).thenReturn(table);
		
		
		
		service.payBill(table);
	}
	
	@Test
	public void listSalesOfDayTest() {
		List<BillEntity> bes = new ArrayList<>();

		
		BillEntity be = new BillEntity();
		be.setId(1);
		be.setBillDate(new Date());
		be.setTotalCost(1000);
		be.setTaxes(190);
		be.setTipping(100);
		
		TableEntity table = new TableEntity();
		table.setId(1);
		table.setStatus("available");
		be.setTable(table);
		
		Set<BillItemEntity> items = new HashSet<>();
		BillItemEntity bie = new BillItemEntity();
		bie.setId(1);
		bie.setDescription("Plato");
		bie.setBill(be);
		bie.setCost(1000);
		items.add(bie);
		be.setItems(items);
		
		bes.add(be);
		
		Mockito.when(billDao.findAllByBillDate(Mockito.any())).thenReturn(bes);
		
		List<Bill> bills = service.listSalesOfDay(new Date());
		
		assert(!bills.isEmpty());
	}
}
