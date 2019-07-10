package cl.transbank.tech.restaurant.service;

import java.util.List;

import cl.transbank.tech.restaurant.to.Dish;

public interface DishManager {
	
	public List<Dish> listDishes();
	public Dish getDish(int id);

}
