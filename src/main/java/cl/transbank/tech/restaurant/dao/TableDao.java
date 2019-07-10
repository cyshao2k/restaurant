package cl.transbank.tech.restaurant.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.transbank.tech.restaurant.entity.TableEntity;

@Repository
public interface TableDao extends CrudRepository<TableEntity, Integer> {

}
