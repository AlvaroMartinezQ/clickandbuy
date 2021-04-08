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
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;
import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminservice;
	@Autowired
	private UserImageService uis;
	@Autowired
	private UserService userservice;
	@Autowired
	private ProductService productservice;
	@Autowired
	private RatingService ratingservice;
	
	@GetMapping("/profile")
	public ModelAndView profile(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		// Admin image
		if(uis.hasPhotoAdmin(admin.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImageAdmin(admin.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("admin/profile");
	}
	
	@PostMapping("/profileUpdate")
	public ModelAndView profileUpdate(Model model, HttpServletRequest request, @RequestParam(required=true) String name, @RequestParam(required=true) String realname, @RequestParam(required=true) String phone) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		adminservice.adminUpdate(admin.getEmail(), realname, name, phone, "");
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		model.addAttribute("updated", true);
		
		
		// Admin image
		if(uis.hasPhotoAdmin(admin.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImageAdmin(admin.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("admin/profile");
	}
	
	@PostMapping("/imageUpload")
	public ModelAndView uploadImage(Model model, HttpServletRequest request, @RequestParam(required=true) String email, @RequestParam MultipartFile img) throws Exception {
		try {
			uis.uploadAdmin(img, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		model.addAttribute("updated", true);
		
		// User image
		if(uis.hasPhotoAdmin(admin.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("image", uis.getImageAdmin(admin.getId()));
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
			return new ModelAndView("admin/register");
		}
		if(!password.equals(passwordconfirmation)) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("admin/register");
		}
		
		String rol = btnradio1? "ROLE_MANAGER":"ROLE_STAFF"; 
		String charge = btnradio1? "Manager":"Staff"; 
		String status = adminservice.newAdmin(email, passwordconfirmation, realname, name, phone, charge, rol);
		if(status.equals("status: this administrator already exist")) {
			model.addAttribute("email_taken", true);
			return new ModelAndView("admin/register");
		}
		
		model.addAttribute("successfully", true);
		return new ModelAndView("admin/register");
	}
	
	@GetMapping("/userList")
	public ModelAndView userList(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		model.addAttribute("adminlist", adminservice.getAdmins());
		model.addAttribute("userlist", userservice.getUsers());
		return new ModelAndView("admin/userList");
	}
	
	@PostMapping("/delete")
	public ModelAndView delete(Model model, HttpServletRequest request, @RequestParam(required=true) int id, @RequestParam(required=true) String type) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		if (type.equals("admin")) {
			if(id==1) {
				model.addAttribute("bad_fields", true);
				model.addAttribute("adminlist", adminservice.getAdmins());
				model.addAttribute("userlist", userservice.getUsers());
				return new ModelAndView("admin/userList");
			}
			adminservice.deleteById(id);
		} else {
			userservice.deleteUser(String.valueOf(id));
		}
		model.addAttribute("adminlist", adminservice.getAdmins());
		model.addAttribute("userlist", userservice.getUsers());
		return new ModelAndView("admin/userList");
	}
	
	@GetMapping("/denied")
	public ModelAndView denied(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		

		return new ModelAndView("admin/denied");
	}
	
	@GetMapping("/productsView")
	public ModelAndView productsView(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		//Falta añadir el nuevo método productsergice.getAllProducts,
		//para que salgan los no activos tambien
		model.addAttribute("products", productservice.getAll());

		return new ModelAndView("admin/productsView");
	}
	
	@GetMapping("/modifyProduct")
	public ModelAndView modifyProduct(Model model, HttpServletRequest request, @RequestParam(required=true) int id) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);

		model.addAttribute("product",productservice.getProduct(id));
		model.addAttribute("check", productservice.getProduct(id).isActive());
		return new ModelAndView("admin/modifyProduct");
	}
	
	@PostMapping("/updateProduct")
	public ModelAndView updateProduct(Model model, HttpServletRequest request, @RequestParam(required=true) int id, @RequestParam(required = true) String name, @RequestParam(required = true) String desc,
			@RequestParam(required = true) String price, @RequestParam(required = true) String stock,
			@RequestParam(required = true) boolean active) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);

		model.addAttribute("updated", true);
		model.addAttribute("result", productservice.updateProduct(String.valueOf(id), name, desc, price, stock, active));
		model.addAttribute("product",productservice.getProduct(id));
		return new ModelAndView("admin/modifyProduct");
	}
	
	@PostMapping("/deleteProduct")
	public ModelAndView deleteProduct(Model model, HttpServletRequest request, @RequestParam(required=true) int id) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
	
		System.out.println(productservice.deleteProductByAdmin(id));
		model.addAttribute("products", productservice.getAll());
		return new ModelAndView("admin/productsView");
	}
	
	@GetMapping("/ratingsView")
	public ModelAndView ratingsView(Model model, HttpServletRequest request) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);

		model.addAttribute("products", productservice.getAll());
		return new ModelAndView("admin/ratingsView");
	}
	
	@PostMapping("/emptyRatings")
	public ModelAndView emptyRatings(Model model, HttpServletRequest request, @RequestParam(required=true) int id) {
		Admin admin = adminservice.getAdmin(request.getUserPrincipal().getName());
		model.addAttribute("mail", admin.getEmail());
		model.addAttribute("userid", admin.getId());
		model.addAttribute("user", admin);
		
		ratingservice.deleteAllRatingsFromProduct(id);
		model.addAttribute("products", productservice.getAll());
		return new ModelAndView("admin/ratingsView");
	}

}
