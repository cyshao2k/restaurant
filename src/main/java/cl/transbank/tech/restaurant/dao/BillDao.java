package cl.transbank.tech.restaurant.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.transbank.tech.restaurant.entity.BillEntity;

@Repository
public interface BillDao extends CrudRepository<BillEntity, Integer> {

	public List<BillEntity> findAllByBillDate(Date date);
}
