package es.urjc.etsii.co.clickandbuyweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@GetMapping("/")
	public ModelAndView home(Model model, HttpServletRequest request) {
		return new ModelAndView("home");
	}
	
}
