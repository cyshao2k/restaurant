package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.transbank.tech.restaurant.dao.DishDao;
import cl.transbank.tech.restaurant.entity.DishEntity;
import cl.transbank.tech.restaurant.service.DishManager;
import cl.transbank.tech.restaurant.to.Dish;

@Service
public class DishManagerImpl implements DishManager {
	
	@Autowired
	private DishDao dishDao;


	@Override
	public List<Dish> listDishes() {
		ArrayList<Dish> dishes = new ArrayList<>();
		
		Iterable<DishEntity> dishEntities = dishDao.findAll();
		
		for (Iterator<DishEntity> it = dishEntities.iterator(); it.hasNext(); ) {
			DishEntity entity = it.next();
			
			Dish dish = new Dish();
			dish.setId(entity.getId());
			dish.setName(entity.getName());
			dish.setCost(entity.getCost());
			
			dishes.add(dish);
		}
		
		return dishes;
	}

	@Override
	public Dish getDish(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
