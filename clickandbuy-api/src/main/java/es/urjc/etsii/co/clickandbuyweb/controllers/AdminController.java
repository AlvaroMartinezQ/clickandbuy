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

import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.AdministratorService;

@RestController
public class AdminController {
	@Autowired
	private AdministratorService adminservice;
	
	@GetMapping("/admins")
	public ModelAndView adminMain(Model model) {
		return new ModelAndView("/admins/adminsMain");
	}
	
	@GetMapping("/admins/all")
	public ModelAndView adminAll(Model model) {
		model.addAttribute("adminlist", adminservice.getAdministrators());
		return new ModelAndView("/admins/adminsList");
	}
	
	@GetMapping("/admins/add")
	public ModelAndView adminAdd(Model model) {
		model.addAttribute("adminlist", adminservice.getAdministrators());
		return new ModelAndView("/admins/adminsNew");
	}
	
	@PostMapping("/webcreateaccountadmin")
	public ModelAndView webcreateaccountadmin(Model model,  @RequestParam(required=true) String email, @RequestParam(required=true) String admin_realname, @RequestParam(required=true) String admin_name, @RequestParam(required=true) String admin_charge, @RequestParam(required=true) int admin_phone,  @RequestParam(required=true) String password, @RequestParam(required=true) String passwordconfirmation) {
		if(email.equals("")||password.equals("")||passwordconfirmation.equals("")||admin_realname.equals("")||admin_name.equals("")||admin_charge.equals("")) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/admins/adminsNew");
		}
		if(!password.equals(passwordconfirmation)) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/admins/adminsNew");
		}
		String status = adminservice.newAdmin(email, password, admin_realname, admin_name, admin_phone, admin_charge);
		if(status.equals("status: this administrator already exist")) {
			model.addAttribute("email_taken", true);
			return new ModelAndView("/admins/adminsNew");
		}
		model.addAttribute("comes_from_login", true);
		return new ModelAndView("login");
	}
	
	@GetMapping("/admins/delete")
	public ModelAndView adminDelete(@RequestParam(required=true) int id, Model model) {
		adminservice.deleteAdminId(id);
		model.addAttribute("adminlist", adminservice.getAdministrators());
		return new ModelAndView("/admins/adminsList");
	}
	
	@GetMapping("/admins/search")
	public ModelAndView adminSearch(Model model) {
		return new ModelAndView("/admins/adminsSearch");
	}
	
	@GetMapping("/admins/search/admin-email")
	public ModelAndView adminEmailSearchView(@RequestParam(required=true) String email, Model model) {
		List<Administrator> admins = new ArrayList<>();
		admins.add(adminservice.adminEmailSearch(email));
		model.addAttribute("adminlist", admins);
		return new ModelAndView("/admins/adminsList");
	}
	
	@GetMapping("/admins/search/admin-name")
	public ModelAndView adminNameSearchView(@RequestParam(required=true) String name, Model model) {
		List<Administrator> admins = new ArrayList<>();
		admins.add(adminservice.adminRealNameSearch(name));
		model.addAttribute("adminlist", admins);
		return new ModelAndView("/admins/adminsList");
	}
	
	@GetMapping("/admins/search/admin-id")
	public ModelAndView adminIdSearchView(@RequestParam(required=true) int id, Model model) {
		List<Administrator> admins = new ArrayList<>();
		admins.add(adminservice.adminIdSearch(id));
		model.addAttribute("adminlist", admins);
		return new ModelAndView("/admins/adminsList");
	}
	
	@GetMapping("/admins/data/search")
	public ModelAndView adminDataSearch(Model model) {
		return new ModelAndView("/admins/adminsDataSearch");
	}
	
	@GetMapping("/admins/data/search/admin-email")
	public ModelAndView adminDataEmailSearchView(@RequestParam(required=true) String email, Model model) {
		model.addAttribute("admin", adminservice.adminEmailSearch(email));
		return new ModelAndView("/admins/adminsData");
	}
	
	@PostMapping("/admins/data/admin-update")
	public String adminDataUpdate(@RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String realname, @RequestParam(defaultValue="defaultName") String name, @RequestParam(defaultValue="123456") int phone, @RequestParam(required=true) String charge) {
		//TODO
		return "Working on it";
	}
	
}
