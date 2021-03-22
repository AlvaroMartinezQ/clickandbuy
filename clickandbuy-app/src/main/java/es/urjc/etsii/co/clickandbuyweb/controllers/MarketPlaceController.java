package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/marketplace")
public class MarketPlaceController {
	
	@Autowired
	private ProductService ps;
	
	@Autowired
	private UserService us;
	
	@GetMapping("")
	public ModelAndView marketplaceInit(Model model, HttpServletRequest request) {
		// Get the user
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		
		// Save the last login for the user
		us.updateLogin(u);
		
		// Return products
		model.addAttribute("products", ps.getAll());
		model.addAttribute("userName", u.getName());
		model.addAttribute("name", u.getEmail());
		return new ModelAndView("/marketplace/productList");
	}
	
}
