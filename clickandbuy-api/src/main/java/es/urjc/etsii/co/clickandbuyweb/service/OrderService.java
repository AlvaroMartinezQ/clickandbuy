package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.OrderDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderdao;
	
	public List<Order> getOrders() {
		return orderdao.findAll();
	}

}
