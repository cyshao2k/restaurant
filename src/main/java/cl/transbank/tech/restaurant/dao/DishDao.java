package cl.transbank.tech.restaurant.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.transbank.tech.restaurant.entity.DishEntity;

@Repository
public interface DishDao extends CrudRepository<DishEntity, Integer> {

}
