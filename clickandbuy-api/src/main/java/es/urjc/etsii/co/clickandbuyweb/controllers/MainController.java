package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.service.ProductService;

@RestController
public class MainController {
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/")
	public ModelAndView getOrders(Model model){
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("mainView");
	}
}
