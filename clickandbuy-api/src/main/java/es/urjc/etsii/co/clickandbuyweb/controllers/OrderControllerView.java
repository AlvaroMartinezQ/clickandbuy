package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
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

	@GetMapping("/cmorder")
	public ModelAndView cmOrder() {
		return new ModelAndView("/orders/cmOrder");
	}

	@PostMapping("/cmorderdata")
	public ModelAndView cmOrder(@RequestParam(required = true) String user_id,
			@RequestParam(required = true) String prod_id,
			@RequestParam(required = true) String order_id, Model model) {
		String cad = orderservice.makeOrder(Integer.parseInt(user_id), Integer.parseInt(prod_id), Integer.parseInt(order_id));
		model.addAttribute("result", cad);
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
	
	@GetMapping("/search")
	public ModelAndView search(Model model) {
		return new ModelAndView("/orders/orderSearch");
	}
	
	@GetMapping("/search-id")
	public ModelAndView searchId(@RequestParam(required=true, defaultValue="0") String id, Model model) {
		int order_id=0;
		try {
			order_id=Integer.parseInt(id);
		} catch (Exception e) {
			System.out.println("bad params");
			model.addAttribute("error", true);
			return new ModelAndView("/orders/orderSearch");
		}
		List<Order> orderlist=orderservice.getOrder(order_id);
		if(orderlist==null||orderlist.size()==0) {
			model.addAttribute("no_result", true);
			return new ModelAndView("/orders/orderSearch");
		}
		//List<Product>productlist=orderservice.getProductList(orderlist);
		for(Order o: orderlist) {
			System.out.println(o);
		}
		//model.addAttribute("productlist", productlist);
		model.addAttribute("orderlist", orderlist);
		return new ModelAndView("/orders/orderList");
	}

}
