package es.urjc.etsii.co.clickandbuyweb.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
	@Autowired
	private AdminService aservice;
	
	@GetMapping("/all")
	public Iterable<Admin> getAll() {
		return aservice.getAll();
	}
	
	@PostMapping("/new")
	public String saveAdmin(@RequestParam(required=true)String adminEmail, @RequestParam(required=true)String password) {
		return aservice.saveAdmin(adminEmail, password);
	}
	
	@PostMapping("/update")
	public String updateUser(@RequestParam(required=true)String email, @RequestParam(required=false)String name,
								@RequestParam(required=false)String realname, @RequestParam(required=false)String phone,
								@RequestParam(required=false)String adminCharge, @RequestParam(required=false)boolean active,
								@RequestParam(required=false)boolean superuser) {
		return aservice.updateUser(email, name, realname, phone, adminCharge, active, superuser);
	}
	
	@DeleteMapping("/delete")
	public String deleteAdmin(@RequestParam(required=true)String adminid) {
		return aservice.deleteAdmin(adminid);
	}
	
}
