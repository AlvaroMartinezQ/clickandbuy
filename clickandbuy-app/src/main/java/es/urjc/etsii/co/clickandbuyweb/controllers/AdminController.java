package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.AdminService;
import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminservice;
	@Autowired
	private UserImageService uis;
	
	@GetMapping("/profile")
	public ModelAndView profile(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		// Admin image
		if(uis.hasPhoto(admin.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImage(admin.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("admin/profile");
	}

}
