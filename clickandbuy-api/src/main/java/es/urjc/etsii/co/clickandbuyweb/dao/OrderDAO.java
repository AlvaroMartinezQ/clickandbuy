package es.urjc.etsii.co.clickandbuyweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import es.urjc.etsii.co.clickandbuyweb.models.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order,Integer>{
	
	@Query("SELECT o FROM Order o WHERE o.order_id=:order_id")
	public List<Order> findbyorder_id(@Param("order_id") int order_id);
	
}
