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

	@RequestMapping("/view")
	public ModelAndView view(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);

		model.addAttribute("products", u.getUser_product_list());
		return new ModelAndView("product/productsView");
	}

	@RequestMapping("/management")
	public ModelAndView management(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);

		return new ModelAndView("product/management");
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
		model.addAttribute("products", u.getUser_product_list());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/list");
	}

	@RequestMapping("/search")
	public ModelAndView search(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/search");
	}

	@RequestMapping("/searchdata")
	public ModelAndView searchData(Model model, HttpServletRequest request,
			@RequestParam(required = true) String name) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("products", productservice.search(name));
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("/product/searchview");
	}

	@RequestMapping("/modify")
	public ModelAndView update(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);

		model.addAttribute("product", productservice.getProduct(id));
		return new ModelAndView("/product/modify");
	}

	@RequestMapping("/modifyok")
	public ModelAndView updateOk(Model model, HttpServletRequest request, @RequestParam(required = true) String id,
			@RequestParam(required = true) String name, @RequestParam(required = true) String description,
			@RequestParam(required = true) String price, @RequestParam(required = true) String stock,
			@RequestParam(required = true) boolean active) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		String result = productservice.updateProduct(id, name, description, price, stock, active);
		model.addAttribute("result",result);
		model.addAttribute("updated", true);
		model.addAttribute("product", productservice.getProduct(Integer.parseInt(id)));
		return new ModelAndView("/product/modify");
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		productservice.deleteProduct(u.getId(), id);
		return new ModelAndView("/product/management");
	}
	@RequestMapping("/denied")
	public ModelAndView denied(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("product/denied");
	}
	
}
