package es.urjc.etsii.co.clickandbuyweb.controllers.api;

import java.util.List;

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
	private AdminService adminservice;
	
	@GetMapping("/all")
	public List<Admin> getAll() {
		return adminservice.getAdmins();
	}
	
	@PostMapping("/new")
	public String saveAdmin(@RequestParam(required=true)String email, @RequestParam(required=true)String password, String realname, String name, String phone, String charge, String rol) {
		return adminservice.newAdmin(email, password,realname,name,phone,charge, rol);
	}
	
	@PostMapping("/update")
	public String updateManager(@RequestParam(required=true)String email, @RequestParam(required=false)String name,
								@RequestParam(required=false)String realname, @RequestParam(required=false)String phone,
								@RequestParam(required=false)String adminCharge, @RequestParam(required=false) String rol) {
		return adminservice.adminUpdate(email, realname, name, phone, rol);
	}
	
	@DeleteMapping("/delete")
	public String deleteAdmin(@RequestParam(required=true)int id) {
		return adminservice.deleteById(id);
	}
	
}
