package es.urjc.etsii.co.clickandbuyweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.service.ProductService;

@RestController
@RequestMapping("/marketplace")
public class MarketPlaceController {
	
	@Autowired
	private ProductService ps;
	
	@GetMapping("/")
	public ModelAndView marketplaceInit(Model model, HttpServletRequest request) {
		model.addAttribute("products", ps.getAll());
		return new ModelAndView("/marketplace/productList");
	}
	
}
