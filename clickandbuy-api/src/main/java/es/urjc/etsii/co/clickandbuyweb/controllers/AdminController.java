package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.AdministratorService;

@RestController
@RequestMapping("/api/administrator")
public class AdminController {
	@Autowired
	private AdministratorService adminservice;
	
	@GetMapping("/all")
	public List<Administrator> getAdministrators(){
		return adminservice.getAdministrators();
	}
	
	@PostMapping("/add")
	public String newAdmin(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String realname, @RequestParam(defaultValue="defaultName") String name, @RequestParam(defaultValue="123456") int phone, @RequestParam(required=true) String charge) {
		return adminservice.newAdmin(email, password, realname, name, phone, charge);
	}
	
	@DeleteMapping("/delete")
	public String deleteAdminId(@RequestParam(required=true) int id) {
		return adminservice.deleteAdminId(id);
	}
	
	@GetMapping("/searchByEmail")
	public Administrator adminEmailSearchByEmail(@RequestParam(required=true) String email) {
		return adminservice.adminEmailSearch(email);
	}
	
	@GetMapping("/searchByRealName")
	public Administrator adminEmailSearchByRealname(@RequestParam(required=true) String realname) {
		return adminservice.adminRealNameSearch(realname);
	}
	
	@PostMapping("/changePassword")
	public String changeAdminPassword(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String newPassword) {
		return adminservice.adminChangePassword(email,password,newPassword);
	}
	
	@PostMapping("/changeName")
	public String changeAdminName(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String newName) {
		return adminservice.adminChangeName(email,password,newName);
	}
	
	@PostMapping("/changeSuperuser")
	public String changeAdminSuperuser(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) Boolean superuser) {
		return adminservice.adminChangeSuperuser(email,password,superuser);
	}
	
	@PostMapping("/changeRealname")
	public String changeAdminRealName(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String newRealname) {
		return adminservice.adminChangeRealName(email,password,newRealname);
	}
	
	@PostMapping("/changePhone")
	public String changeAdminPhone(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) int newPhone) {
		return adminservice.adminChangePhone(email,password,newPhone);
	}
	
	@PostMapping("/modifyRealname")
	public String modifyAdminRealName(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String adminEmail, @RequestParam(required=true) String newRealname) {
		return adminservice.managerModifyRealname(email,password,adminEmail,newRealname);
	}
	
	@PostMapping("/modifyCharge")
	public String modifyAdminCharge(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String adminEmail, @RequestParam(required=true) String newCharge) {
		return adminservice.managerModifyCharge(email,password,adminEmail,newCharge);
	}
}
