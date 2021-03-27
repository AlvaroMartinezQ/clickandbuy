package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			if (admin.getRoles().contains("MANAGER_ROLE")) {
				model.addAttribute("products", ps.getAll());
				model.addAttribute("mail", admin.getEmail());
				return new ModelAndView("/admin/productList");
			}
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

}
