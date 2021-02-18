package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.urjc.etsii.co.clickandbuyweb.models.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order,Integer>{

}
