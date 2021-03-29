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

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
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

	@Autowired
	private AdminDAO admindao;

	@GetMapping("")
	public ModelAndView marketplaceInit(Model model, HttpServletRequest request) {
		// Get the user
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());

		// Check if is admin
		Admin admin = (Admin) admindao.findByEmail(principal.getName());

		if (admin != null) {
			model.addAttribute("products", ps.getAll());
			model.addAttribute("mail", admin.getEmail());
			return new ModelAndView("/admin/productList");
			
		}

		// Check if user is supplier
		if (u != null) {
			if (u.getRoles().contains("ROLE_SUPPLIER")) {
				us.updateLogin(u);
				model.addAttribute("products", ps.getAll());
				model.addAttribute("mail", u.getEmail());
				return new ModelAndView("/marketplace/productList");
			}

			// Save the last login for the user
			if (u.getRoles().contains("ROLE_NOTSUPPLIER")) {
				us.updateLogin(u);
				// Return products
				model.addAttribute("products", ps.getAll());
				model.addAttribute("mail", u.getEmail());
				return new ModelAndView("/marketplace/productList");
			}
		}
		return null;
	}
	
	@RequestMapping("/productsView")
	public ModelAndView view(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();

		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);

		model.addAttribute("product", ps.getProduct(id));
		return new ModelAndView("/marketplace/productsView");
	}
	
	@GetMapping("/rate")
	public ModelAndView rate(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		User user = us.getUser(principal.getName());
		model.addAttribute("mail", user.getEmail());
		model.addAttribute("userid", user.getId());
		model.addAttribute("user", user);
		
		model.addAttribute("product", ps.getProduct(id));
		return new ModelAndView("/marketplace/productsView");
	}

}
