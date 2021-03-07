package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderControllerView {

	@Autowired
	private OrderService orderservice;

	@GetMapping("/main")
	public ModelAndView orderMain(Model model) {
		return new ModelAndView("/orders/ordersMain");
	}

	@GetMapping("/status")
	public ModelAndView orderStatus() {
		return new ModelAndView("/orders/orderStatus");
	}

	@GetMapping("/all")
	public ModelAndView orderAll(Model model1, Model model2) {
		model1.addAttribute("orderlist", orderservice.getOrders());
		// model2.addAttribute("productlist",orderservice.p);
		return new ModelAndView("/orders/orderList");
	}

	// crear y modificar pedido
	@GetMapping("/cmorder")
	public ModelAndView cmOrder() {
		return new ModelAndView("/orders/cmOrder");
	}

	@PostMapping("/cmorderdata")
	public ModelAndView cmOrder(@RequestParam(required = true) String usid,
			@RequestParam(required = true) String prodid,
			@RequestParam(required = true) String orderid, Model model) {
		String cad = orderservice.makeOrder(Integer.parseInt(usid), Integer.parseInt(prodid), Integer.parseInt(orderid));
		model.addAttribute(cad);
		model.addAttribute("updated",true);
		return new ModelAndView("/orders/cmOrder");
	}

	@PostMapping("/statuschange")
	public ModelAndView orderStatus(@RequestParam(required = true, defaultValue = "-1") String order_id,
			@RequestParam(required = true, defaultValue = "-1") String status_id, Model model) {
		String cad = orderservice.changeStatus(Integer.parseInt(order_id), Integer.parseInt(status_id));
		model.addAttribute("updated", true);
		model.addAttribute("cad", cad);
		return new ModelAndView("/orders/orderStatus");
	}

	@GetMapping("/deleted")
	public ModelAndView deleted() {
		return new ModelAndView("/orders/deletedProduct");
	}

	@GetMapping("/deletedorder")
	public ModelAndView deletedOrder(@RequestParam(required = true, defaultValue = "-1") String order_id, Model model) {
		String cad = orderservice.deleteOrder(Integer.parseInt(order_id));
		model.addAttribute("updated", true);
		model.addAttribute("cad", cad);
		return new ModelAndView("/orders/deletedProduct");

	}

}
