package es.urjc.etsii.co.clickandbuyweb.controllers;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Admin;
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
	
	@PostMapping("/imageUpload")
	public ModelAndView uploadImage(Model model, HttpServletRequest request, @RequestParam(required=true) String email, @RequestParam MultipartFile img) throws Exception {
		try {
			uis.upload(img, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		model.addAttribute("updated", true);
		
		// User image
		if(uis.hasPhoto(admin.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("image", uis.getImage(admin.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("admin/profile");
	}
	
	@GetMapping("/management")
	public ModelAndView management(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		return new ModelAndView("admin/management");
	}
	
	@GetMapping("/register")
	public ModelAndView register(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		return new ModelAndView("admin/register");
	}
	
	@PostMapping("/newAdmin")
	public ModelAndView newAdmin(Model model, HttpServletRequest request, @RequestParam(required=true) String email, @RequestParam(required=true) String realname, @RequestParam(required=true) String name, @RequestParam(required=true) String phone,  @RequestParam(required=true) String password, @RequestParam(required=true) String passwordconfirmation, boolean btnradio1, boolean btnradio2) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		if(email.isBlank()||password.isBlank()||passwordconfirmation.isBlank()||realname.isBlank()||name.isBlank()||phone.isBlank()) {
			model.addAttribute("fill_fields", true);
			return new ModelAndView("/register");
		}
		if(!password.equals(passwordconfirmation)) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/register");
		}
		
		String rol = btnradio1? "MANAGER_ROLE":"STAFF_ROLE"; 
		String charge = btnradio1? "Manager":"Staff"; 
		String status = adminservice.newAdmin(email, passwordconfirmation, realname, name, phone, charge, rol);
		if(status.equals("status: this administrator already exist")) {
			model.addAttribute("email_taken", true);
			return new ModelAndView("/register");
		}
		
		return new ModelAndView("admin/register");
	}

}
