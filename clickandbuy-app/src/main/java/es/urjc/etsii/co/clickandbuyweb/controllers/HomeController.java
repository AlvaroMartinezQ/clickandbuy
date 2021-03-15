package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@GetMapping("/")
	public ModelAndView home(Model model) {
		return new ModelAndView("home");
	}
	
}
