package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderservice;

	@Autowired
	private UserService us;

	@RequestMapping("/ordermain")
	public ModelAndView singUp(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User u = us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			return new ModelAndView("/order/ordermain");
		}
		return new ModelAndView("user/singUp");
	}

}
