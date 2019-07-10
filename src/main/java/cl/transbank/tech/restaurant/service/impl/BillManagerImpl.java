package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.transbank.tech.restaurant.dao.BillDao;
import cl.transbank.tech.restaurant.entity.BillEntity;
import cl.transbank.tech.restaurant.entity.BillItemEntity;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.NoOrdersFoundException;
import cl.transbank.tech.restaurant.service.BillManager;
import cl.transbank.tech.restaurant.service.OrderManager;
import cl.transbank.tech.restaurant.service.TableManager;
import cl.transbank.tech.restaurant.to.Bill;
import cl.transbank.tech.restaurant.to.BillItem;
import cl.transbank.tech.restaurant.to.Dish;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@Service
@Transactional
public class BillManagerImpl implements BillManager {
	
	@Autowired
	private TableManager tableManager;
	
	@Autowired
	private OrderManager orderManager;
	
	@Autowired
	private BillDao billDao;

	@Override
	public void payBill(Table table) throws ItemNotFoundException, NoOrdersFoundException {
		table = tableManager.getTable(table.getId());
		List<Order> orders = tableManager.listOrders(table);
		
		int totalCost = 0;
		int taxes = 0;
		int tipping = 0;
		
		if (!orders.isEmpty()) {
			BillEntity billEntity = new BillEntity();
			billEntity.setBillDate(new Date());
			
			Set<BillItemEntity> billItems = new HashSet<>();
			billEntity.setItems(billItems);
			
			TableEntity tableEntity = new TableEntity();
			tableEntity.setId(table.getId());
			tableEntity.setStatus(table.getStatus());
			billEntity.setTable(tableEntity);
			
			for (Iterator<Order> it = orders.iterator(); it.hasNext(); ) {
				Order order = it.next();
				
				Dish[] dishes = order.getDishes();
				if (dishes.length > 0) {
					for (int i=0; i < dishes.length; i++) {
						BillItemEntity billItemEntity = new BillItemEntity();
						billItemEntity.setBill(billEntity);
						billItemEntity.setCost(dishes[i].getCost());
						billItemEntity.setDescription(dishes[i].getName());
						
						billItems.add(billItemEntity);
						
						totalCost += dishes[i].getCost();
					}
				}
			}
			
			taxes = Integer.divideUnsigned(totalCost*19, 100);
			tipping = Integer.divideUnsigned(totalCost*10, 100);
			
			billEntity.setTotalCost(totalCost);
			billEntity.setTaxes(taxes);
			billEntity.setTipping(tipping);
			
			billDao.save(billEntity);
			orderManager.deleteOrdersByTable(table);
			table.setStatus("available");
			tableManager.setStatus(table);
		} else {
			throw new NoOrdersFoundException();
		}
		
	}

	@Override
	public List<Bill> listSalesOfDay(Date day) {
		List<BillEntity> billEntities = billDao.findAllByBillDate(day);
		
		List<Bill> bills = new ArrayList<>();

		if (!billEntities.isEmpty()) {
			for (Iterator<BillEntity> it = billEntities.iterator(); it.hasNext(); ) {
				BillEntity billEntity = it.next();
				
				Bill bill = new Bill();
				bill.setId(billEntity.getId());
				bill.setBillDate(billEntity.getBillDate());
				bill.setTotalCost(billEntity.getTotalCost());
				bill.setTaxes(billEntity.getTaxes());
				bill.setTipping(billEntity.getTipping());
				
				TableEntity te = billEntity.getTable();
				Table table = new Table();
				table.setId(te.getId());
				table.setStatus(te.getStatus());
				bill.setTable(table);
				
				Set<BillItemEntity> bies = billEntity.getItems();
				if (!bies.isEmpty()) {
					List<BillItem> billItems = new ArrayList<>();
					for (Iterator<BillItemEntity> it2 = bies.iterator(); it2.hasNext(); ) {
						BillItemEntity bie = it2.next();
						
						BillItem item = new BillItem();
						item.setId(bie.getId());
						item.setCost(bie.getCost());
						item.setDescription(bie.getDescription());
						
						billItems.add(item);
					}
					bill.setItems(billItems);
				}
				
				bills.add(bill);
			}
		}
		
		return bills;
	}
}
