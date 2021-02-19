package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userservice;
	
	// GET methods
	
	@GetMapping("/all")
	public List<User> getUsers() {
		return userservice.getUsers();
	}
	
	@GetMapping("/search")
	public User userEmailSearch(@RequestParam(required=true) String email) {
		return userservice.userEmailSearch(email);
	}
	
	// POST methods
	
	@PostMapping("/add")
	public String newUser(@RequestParam(required=true) String email, @RequestParam(required=true) String password) {
		return userservice.newUser(email, password);
	}
	
	// DELETE methods
	
	@DeleteMapping("/delete")
	public String deleteUserId(@RequestParam(required=true) int id) {
		return userservice.deleteUserId(id);
	}
	
}
