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
	
	@GetMapping("/name-search")
	public List<User> UserNameSearch(@RequestParam(required=true) String name) {
		return userservice.UserNameSearch(name);
	}
	
	@GetMapping("/products")
	public String userProducts(@RequestParam(required=true) int usid) {
		return userservice.userProducts(usid);
	}
	
	@GetMapping("/set-bankaccount")
	public String setUserBankaccount(@RequestParam(required=true) int usid, @RequestParam(required=true) int account) {
		return userservice.setUserBankaccount(usid, account);
	}
	
	// POST methods
	
	@PostMapping("/add")
	public String newUser(@RequestParam(required=true) String email, @RequestParam(required=true) String password) {
		return userservice.newUser(email, password);
	}
	
	@PostMapping("/product-add")
	public String newUserProduct(@RequestParam(required=true) String name, @RequestParam(required=true) String desc, @RequestParam(required=true) double price, @RequestParam(required=true) int units, @RequestParam(required=true) int usid) {
		return userservice.addUserProduct(name, desc, price, units, usid);
	}
	
	@PostMapping("/set-supplier")
	public String setUserSupplier(@RequestParam(required=true) int usid) {
		return userservice.setUserSupplier(usid);
	}
	
	@PostMapping("/unset-supplier")
	public String unsetUserSupplier(@RequestParam(required=true) int usid) {
		return userservice.unsetUserSupplier(usid);
	}
	
	@PostMapping("/activate")
	public String activateUser(@RequestParam(required=true) int usid) {
		return userservice.activateUser(usid);
	}
	
	@PostMapping("/deactivate")
	public String deactivateUser(@RequestParam(required=true) int usid) {
		return userservice.deactivateUser(usid);
	}
	
	@PostMapping("/set-address")
	public String addressUser(@RequestParam(required=true) int usid, @RequestParam(required=true) String address) {
		return userservice.addressUser(usid, address);
	}
	
	@PostMapping("/set-name")
	public String nameUser(@RequestParam(required=true) int usid, @RequestParam(required=true) String name) {
		return userservice.nameUser(usid, name);
	}
	
	// DELETE methods
	
	@DeleteMapping("/delete")
	public String deleteUserId(@RequestParam(required=true) int id) {
		return userservice.deleteUserId(id);
	}
	
	@DeleteMapping("/product-delete")
	public String deleteUserProduct(@RequestParam(required=true) int usid, @RequestParam(required=true) int prodid) {
		return userservice.deleteUserProduct(usid, prodid);
	}
	
}
