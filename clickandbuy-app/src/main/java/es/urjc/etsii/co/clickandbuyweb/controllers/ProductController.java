package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productservice;

	@Autowired
	private UserService us;

	@RequestMapping("/productmain")
	public ModelAndView singUp(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/productmain");

	}

	@RequestMapping("/upload")
	public ModelAndView upload(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/upload");
	}

	@RequestMapping("/uploadform")
	public ModelAndView uploadForm(Model model, HttpServletRequest request, @RequestParam(required = true) String name,
			@RequestParam(required = true) String desc, @RequestParam(required = true) Double price,
			@RequestParam(required = true) int stock) {

		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		productservice.saveProduct(u.getEmail(), price, name, desc, stock);
		model.addAttribute("updated", true);
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/upload");
	}
	
	@RequestMapping("/owner")
	public ModelAndView owner(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("products",u.getUser_product_list());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/owner");
	}
}
