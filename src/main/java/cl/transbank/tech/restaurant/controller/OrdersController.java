package cl.transbank.tech.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.exeption.TableNotUsedException;
import cl.transbank.tech.restaurant.service.OrderManager;
import cl.transbank.tech.restaurant.to.Order;

@RestController
@RequestMapping("orders")
public class OrdersController {
	
	@Autowired
	private OrderManager service;
	
	@PostMapping
	public Object save(@RequestBody Order order) throws ItemNotFoundException, TableNotUsedException {
		
		service.createOrder(order);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
