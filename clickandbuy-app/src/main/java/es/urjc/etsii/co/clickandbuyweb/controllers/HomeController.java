package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.AdminService;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
public class HomeController {

	@Autowired
	private UserService us;
	@Autowired
	private ProductService ps;
	@Autowired
	private AdminService adminservice;
	
	@GetMapping("/")
	public ModelAndView home(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal!=null) {
			Admin admin = adminservice.getAdmin(principal.getName());
			if(admin!=null) {
				model.addAttribute("email", admin.getName());
				model.addAttribute("userid", admin.getId());
				model.addAttribute("user", admin);
				model.addAttribute("products", ps.getAll());
				return new ModelAndView("admin/productListAdmin");
			}
			User u=us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("products", ps.getAll());
			return new ModelAndView("marketplace/productList");
		}
		return new ModelAndView("home");
	}
	
}
