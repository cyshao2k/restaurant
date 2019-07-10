package cl.transbank.tech.restaurant.service;

import java.util.Date;
import java.util.List;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.NoOrdersFoundException;
import cl.transbank.tech.restaurant.to.Bill;
import cl.transbank.tech.restaurant.to.Table;

public interface BillManager {
	
	public void payBill(Table table) throws ItemNotFoundException, NoOrdersFoundException;
	public List<Bill> listSalesOfDay(Date day);

}
