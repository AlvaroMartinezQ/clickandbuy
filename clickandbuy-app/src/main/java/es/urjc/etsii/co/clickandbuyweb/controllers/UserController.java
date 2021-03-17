package es.urjc.etsii.co.clickandbuyweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	
	/*
	 * SingUp: View and Form
	 */
	
	@GetMapping("/singUp")
	public ModelAndView singUp(Model model) {
		return new ModelAndView("user/singUp");
	}
	
	@PostMapping("/singUpForm")
	public ModelAndView singUpForm(Model model, @RequestParam(required=true) String email, @RequestParam(required=true) String emailConfirmation,
									@RequestParam(required=true) String password, @RequestParam(required=true) String passwordConfirmation) {
		int status=us.singUpUser(email, emailConfirmation, password, passwordConfirmation);
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
	
	@GetMapping("/singIn")
	public ModelAndView singIn(Model model, HttpServletRequest request) {
		return new ModelAndView("user/singIn");
	}
	
	@PostMapping("/singInForm")
	public String singInForm(Model model, @RequestParam(required=true) String email, @RequestParam(required=true) String password, HttpServletRequest request) {
		System.out.println("Sing in form");
		/*
		boolean status=us.singInUser(email, password);
		if(!status) {
			System.out.println("bad login");
			model.addAttribute("badFields", true);
			return new ModelAndView("user/singIn");
		}
		*/
		//model.addAttribute("products", ps.getAll());
		return ("singInForm");
	}
	
}
