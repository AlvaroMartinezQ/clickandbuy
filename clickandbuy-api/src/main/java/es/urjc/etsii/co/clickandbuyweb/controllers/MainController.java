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
		if(!model.containsAttribute("is_logged")) {
			model.addAttribute("is_logged", false);
		}
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
		model.addAttribute("user_name", u.getUser_email());
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
	
	@GetMapping("/createaccount")
	public ModelAndView createaccount(Model model) {
		return new ModelAndView("newuser");
	}
	
	@PostMapping("/webcreateaccount")
	public ModelAndView webcreateaccount(Model model,  @RequestParam(required=true) String email, @RequestParam(required=true) String emailconfirmation, @RequestParam(required=true) String password, @RequestParam(required=true) String passwordconfirmation) {
		if(!email.equals(emailconfirmation) || !password.equals(passwordconfirmation)) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("newuser");
		}
		if(email.equals("")||emailconfirmation.equals("")||password.equals("")||passwordconfirmation.equals("")) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("newuser");
		}
		String status = userservice.newUser(email, password);
		if(status.equals("status: user email already exists")) {
			model.addAttribute("email_taken", true);
			return new ModelAndView("newuser");
		}
		model.addAttribute("comes_from_login", true);
		return new ModelAndView("login");
	}
	
	@GetMapping("/users")
	public ModelAndView userMain(Model model) {
		return new ModelAndView("/users/usersMain");
	}
	
	@GetMapping("/users/all")
	public ModelAndView userAll(Model model) {
		model.addAttribute("userlist", userservice.getUsers());
		return new ModelAndView("/users/userList");
	}
	
	@GetMapping("/users/delete")
	public ModelAndView userDelete(@RequestParam(required=true) int usid, Model model) {
		userservice.deleteUserId(usid);
		model.addAttribute("userlist", userservice.getUsers());
		return new ModelAndView("/users/userList");
	}
}
