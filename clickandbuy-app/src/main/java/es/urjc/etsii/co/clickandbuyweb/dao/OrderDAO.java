package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order,Integer>{
	
	@Query("SELECT o FROM Order o WHERE o.id=:order_id")
	public Order findByOrderId(@Param("order_id") Long order_id);
	
}
