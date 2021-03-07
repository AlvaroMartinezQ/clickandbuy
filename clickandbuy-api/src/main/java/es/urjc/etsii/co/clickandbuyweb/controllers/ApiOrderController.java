package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class ApiOrderController {
	@Autowired
	private OrderService orderservice;

	// GET methods

	@GetMapping("/all")
	public List<Order> getOrders() {
		return orderservice.getOrders();
	}

	@GetMapping("/order")
	public List<Order> getOrder(@RequestParam(required = true) int order_id) {
		return orderservice.getOrder(order_id);
	}

	@GetMapping("/changestatus")
	public String changeStatus(@RequestParam(required = true) int order_id,
			@RequestParam(required = true) int status_id) {
		return orderservice.changeStatus(order_id, status_id);
	}

	@GetMapping("/delete")
	public String deleteOrder(@RequestParam(required = true) int order_id) {
		return orderservice.deleteOrder(order_id);
	}

	@GetMapping("/status")
	public String orderStatus(@RequestParam(required = true) int order_id) {
		return orderservice.orderStatus(order_id);
	}

	@GetMapping("/price")
	public String priceOrder(@RequestParam(required = true) int order_id) {
		return orderservice.priceOrder(order_id);
	}

	// POST methods

	@PostMapping("/make")
	public String makeOrder(@RequestParam(required = true) int usid, @RequestParam(required = true) int prodid,
			@RequestParam(required = true) int orderid) {
		return orderservice.makeOrder(usid, prodid, orderid);
	}

}
