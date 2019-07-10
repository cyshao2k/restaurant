package cl.transbank.tech.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.NoOrdersFoundException;
import cl.transbank.tech.restaurant.service.BillManager;
import cl.transbank.tech.restaurant.service.TableManager;
import cl.transbank.tech.restaurant.to.Order;
import cl.transbank.tech.restaurant.to.Table;

@RestController
@RequestMapping("tables")
public class TablesController {
	
	@Autowired
	private TableManager service;
	
	@Autowired
	private BillManager billService;
	
	@GetMapping
	public List<Table> list() {
		List<Table> result = null;
		
		result = service.listTables();
		
		return result;
	}
	
	@GetMapping("/{id}")
	public Table getTable(@PathVariable("id") int id) throws ItemNotFoundException {
		Table result = null;
		
		result = service.getTable(id);
		
		return result;
	}
	
	@PutMapping("/{id}")
	public Object setStatus(
			@PathVariable("id") int id, 
			@RequestBody Table table)
					 throws ItemNotFoundException {
		
		table.setId(id);
		
		service.setStatus(table);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}/orders")
	public List<Order> getOrdersByTable(@PathVariable("id") int id) throws ItemNotFoundException  {
		List<Order> result = null;
		
		Table table = new Table();
		table.setId(id);
		
		result = service.listOrders(table);
		
		return result;
	}
	
	@PostMapping("/{id}/bill")
	public Object payBill(@PathVariable("id") int id) throws ItemNotFoundException, NoOrdersFoundException {
		Table table = new Table();
		table.setId(id);
		
		billService.payBill(table);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
