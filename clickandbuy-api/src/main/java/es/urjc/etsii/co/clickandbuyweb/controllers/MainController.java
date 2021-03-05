package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public ModelAndView home(Model model) {
		return new ModelAndView("home");
	}
	
	@GetMapping("/prouctView")
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
	
	@GetMapping("/users/search")
	public ModelAndView userSearch(Model model) {
		return new ModelAndView("/users/userSearch");
	}
	
	@GetMapping("/users/search/user-email")
	public ModelAndView userEmailSearchView(@RequestParam(required=true) String email, Model model) {
		// These steps should be inside the service but idc :)
		User u = userservice.userEmailSearch(email);
		List<User> list = new ArrayList<>();
		if(u!=null) {
			list.add(u);
		}
		model.addAttribute("userlist", list);
		return new ModelAndView("/users/userList");
	}
	
	@GetMapping("/users/search/user-name")
	public ModelAndView userNameSearchView(@RequestParam(required=true) String name, Model model) {
		// These steps should be inside the service but idc :)
		List<User> list = userservice.UserNameSearch(name);
		model.addAttribute("userlist", list);
		return new ModelAndView("/users/userList");
	}
	
	@GetMapping("/users/search/user-id")
	public ModelAndView userIdSearchView(@RequestParam(required=true) int id, Model model) {
		// These steps should be inside the service but idc :)
		User u = userservice.idSearch(id);
		List<User> list = new ArrayList<>();
		if(u!=null) {
			list.add(u);
		}
		model.addAttribute("userlist", list);
		return new ModelAndView("/users/userList");
	}
	
	@GetMapping("/users/data/search")
	public ModelAndView userDataSearchView(Model model) {
		return new ModelAndView("/users/userDataSearch");
	}
	
	@GetMapping("/users/data/search/user-email")
	public ModelAndView userDataEmailSearchView(@RequestParam(required=true) String email, Model model) {
		User u = userservice.userEmailSearch(email);
		if(u!=null) {
			model.addAttribute("user", u);
		}
		return new ModelAndView("/users/userData");
	}
	
	@GetMapping("/users/data/search/id")
	public ModelAndView userDataIdSearchView(@RequestParam(required=true, defaultValue="0") String id, Model model) {
		int usid=Integer.parseInt(id);
		User u = userservice.idSearch(usid);
		model.addAttribute("user", u);
		return new ModelAndView("/users/userData");
	}
	
	@PostMapping("/users/data/user-update")
	public ModelAndView userDataUpdate(@RequestParam(required=true) String email, 
										@RequestParam(required=false) String user_name,
										@RequestParam(required=false) String user_realname,
										@RequestParam(required=false) int  user_phone,
										@RequestParam(required=false) int  user_bankaccount,
										@RequestParam(required=false) String user_address,
										@RequestParam(required=false) int is_active,
										@RequestParam(required=false) int is_supplier,
										Model model) {
		System.out.println(email);
		User u = userservice.dataUpdate(email, user_name, user_realname, user_phone, user_bankaccount, user_address, is_active, is_supplier);
		System.out.println(u);
		model.addAttribute("user", u);
		return new ModelAndView("/users/userData");
	}
}
