package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
public class MainController {
	@Autowired
	private ProductService productservice;
	@Autowired
	private UserService userservice;
	
	@GetMapping("/")
	public ModelAndView getOrders(Model model) {
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("mainView");
	}
	
	@GetMapping("/login")
	public ModelAndView login(Model model) {
		return new ModelAndView("login");
	}
	
	@PostMapping("/weblogin")
	public ModelAndView login(Model model, @RequestParam(required=true) String email, @RequestParam(required=true) String password) {
		User u = userservice.loginUser(email, password);
		if(u==null) {
			model.addAttribute("login_error", true);
			return new ModelAndView("login");
		}
		model.addAttribute("user_id", u.getId());
		model.addAttribute("is_logged", true);
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("mainView");
	}
	
	@GetMapping("/closesession")
	public ModelAndView closesession(Model model) {
		model.addAttribute("user_id", 0);
		model.addAttribute("is_logged", false);
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("mainView");
	}
}
