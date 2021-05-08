package es.urjc.etsii.co.clickandbuyweb.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

/*
 * API Controllers are just for testing, no business logic should be here
 * Only CRUD methods for testing
 */

@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@Autowired
	private UserService uservice;
	
	@GetMapping("/all")
	public Iterable<User> getAll() {
		return uservice.getUsers();
	}
	
	@PostMapping("/new")
	public String saveUser(@RequestParam(required=true)String userEmail, @RequestParam(required=true)String password) {
		return uservice.saveUser(userEmail, password);
	}
	
	@PostMapping("/update")
	public String updateUser(@RequestParam(required=true)String userEmail, @RequestParam(required=false)String name,
								@RequestParam(required=false)String realname, @RequestParam(required=false)String realsurnames,
								@RequestParam(required=false)String address, @RequestParam(required=false)String phone,
								@RequestParam(required=false)String bankaccount) {
		return uservice.updateUserApi(userEmail, name, realname, realsurnames, address, phone, bankaccount);
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam(required=true)String usid) {
		return uservice.deleteUser(usid);
	}
	
}
