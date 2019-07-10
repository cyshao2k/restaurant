package cl.transbank.tech.restaurant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.transbank.tech.restaurant.entity.OrderEntity;

@Repository
public interface OrderDao extends CrudRepository<OrderEntity, Integer>  {
	
	public List<OrderEntity> findByTable_Id(int id);
	
	@Modifying
	@Query("delete from OrderEntity o where o.table.id=:id")
	void deleteOrdersByTable(@Param("id") int id);

}
