package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
public class HomeController {

	@Autowired
	private UserService us;
	@Autowired
	private ProductService ps;
	
	@GetMapping("/")
	public ModelAndView home(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal!=null) {
			User u=us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("products", ps.getAll());
			return new ModelAndView("/marketplace/productList");
		}
		return new ModelAndView("home");
	}
	
}
