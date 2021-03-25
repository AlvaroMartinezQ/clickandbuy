package es.urjc.etsii.co.clickandbuyweb.controllers.api;

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
public class OrderApiController {
	@Autowired 
	private OrderService oservice;
	
	@GetMapping("/all")
	public Iterable<Order> getAll(){
		return oservice.getAll();
	}
	
	@PostMapping("/new")
	public Order create(@RequestParam String idus, @RequestParam String idprod, @RequestParam String quantity, @RequestParam(required=false,defaultValue="0") String order) {
		return oservice.create(idus, idprod, quantity, order);
	}
}
