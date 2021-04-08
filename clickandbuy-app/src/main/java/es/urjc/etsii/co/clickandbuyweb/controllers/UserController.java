package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	@Autowired
	private UserImageService uis;
	@Autowired
	private ProductService ps;
	
	/*
	 * SingUp: View and Form
	 */
	
	@GetMapping("/singUp")
	public ModelAndView singUp(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal!=null) {
			User u=us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("products", ps.getAll());
			return new ModelAndView("marketplace/productList");
		}
		return new ModelAndView("user/singUp");
	}
	
	@PostMapping("/singUpForm")
	public ModelAndView singUpForm(Model model, HttpServletRequest request, @RequestParam(required=true) String email, @RequestParam(required=true) String emailConfirmation,
									@RequestParam(required=true) String password, @RequestParam(required=true) String passwordConfirmation, @RequestParam(required=false) boolean is_supplier) {
		int status=us.singUpUser(email, emailConfirmation, password, passwordConfirmation, is_supplier);
		if(status!=0) {
			if(status==-1) {
				model.addAttribute("emptyFields", true);
				return new ModelAndView("user/singUp");
			} else if(status==-2) {
				model.addAttribute("fieldsNoMatch", true);
				return new ModelAndView("user/singUp");
			} else if(status==-3) {
				model.addAttribute("emailTaken", true);
				return new ModelAndView("user/singUp");
			} else {
				model.addAttribute("unknownError", true);
				return new ModelAndView("user/singUp");
			}
		}
		model.addAttribute("userCreated", true);
		return new ModelAndView("user/singIn");
	}
	
	/*
	 * SingIp: View and Form
	 */
	
	@GetMapping("/singIn")
	public ModelAndView singIn(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal!=null) {
			User u=us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("products", ps.getAll());
			return new ModelAndView("marketplace/productList");
		}
		return new ModelAndView("user/singIn");
	}
	
	@PostMapping("/singInForm")
	public String singInForm(Model model, @RequestParam(required=true) String email, @RequestParam(required=true) String password, HttpServletRequest request) {
		return ("singInForm");
	}
	
	@GetMapping("/singFail")
	public ModelAndView singInError(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal!=null) {
			User u=us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("products", ps.getAll());
			return new ModelAndView("marketplace/productList");
		}
		model.addAttribute("error", true);
		return new ModelAndView("user/singIn");
	}
	
	/*
	 * Profile: View and Methods
	 */
	
	@GetMapping("/profile")
	public ModelAndView profile(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		
		// User image
		if(uis.hasPhoto(u.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImage(u.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("user/profile");
	}
	
	@PostMapping("/profileUpdate")
	public ModelAndView profileUpdate(Model model, HttpServletRequest request, @RequestParam(required=true) String email,
										@RequestParam(required=true) String name, @RequestParam(required=true) String realname,
										@RequestParam(required=true) String phone, @RequestParam(required=true) String bankaccount,
										@RequestParam(required=true) String address, @RequestParam(required=true) String realsurnames) {
		us.updateUser(email, name, realname, phone, bankaccount, address, realsurnames);
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		model.addAttribute("updated", true);
		
		// User image
		if(uis.hasPhoto(u.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImage(u.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("user/profile");
	}
	
	@PostMapping("/imageUpload")
	public ModelAndView uploadImage(Model model, HttpServletRequest request, @RequestParam(required=true) String email, @RequestParam MultipartFile img) throws Exception {
		try {
			uis.upload(img, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		model.addAttribute("updated", true);
		
		// User image
		if(uis.hasPhoto(u.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("image", uis.getImage(u.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("user/profile");
	}
	
	// Chat - Internal service
	@GetMapping("/chat")
	public ModelAndView chat(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		return new ModelAndView("user/globalChat");
	}
	
	// User info - Internal service
	@RequestMapping("/info")
	public ModelAndView infoUser(Model model, HttpServletRequest request) throws IOException {
		System.out.println("Info request from user");
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		
		RestTemplate restTemplate = new RestTemplate();
		String data = u.toString();
		/*
		 *  Keep this URL hardcoded as it is not going to change
		 */
		String url="http://127.0.0.1:8081/legacy/user/info?data=" + data + "&email=" + u.getEmail();
		/*
		 * Fire the end-point call
		 * The end-point sends back a String object
		 */
		String response = restTemplate.getForObject(url, String.class);
		/*
		 * Print the response from the server. For DEBUG purposes
		 */
		System.out.println(response);
		
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		model.addAttribute("info", true);
		
		// User image
		if(uis.hasPhoto(u.getId())) {
			model.addAttribute("userImage", true);
			model.addAttribute("imageFile", uis.getImage(u.getId()));
		} else {
			model.addAttribute("userImage", false);
		}
		return new ModelAndView("user/profile");
	}
}
