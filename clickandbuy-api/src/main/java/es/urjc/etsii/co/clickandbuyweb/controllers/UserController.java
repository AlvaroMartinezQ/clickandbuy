package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;
	
	@GetMapping("/all")
	public List<User> getUsers(){
		return userservice.getUsers();
	}
	
}
