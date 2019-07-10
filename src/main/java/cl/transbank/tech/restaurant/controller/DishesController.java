package cl.transbank.tech.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.service.DishManager;
import cl.transbank.tech.restaurant.to.Dish;

@RestController
@RequestMapping("dishes")
public class DishesController {

	@Autowired
	private DishManager service;
	
	@GetMapping
	public List<Dish> list() {
		List<Dish> result = null;
		
		result = service.listDishes();
		
		return result;
	}

	@GetMapping("/{id}")
	public Dish getDish(@PathVariable("id") int id) throws ItemNotFoundException {
		Dish result = null;
		
		result = service.getDish(id);
		
		return result;
	}
}
