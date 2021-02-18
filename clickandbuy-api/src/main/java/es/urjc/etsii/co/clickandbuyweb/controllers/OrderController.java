package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	@GetMapping("/all")
	public List<Order> getOrders(){
		return orderservice.getOrders();
	}

}
