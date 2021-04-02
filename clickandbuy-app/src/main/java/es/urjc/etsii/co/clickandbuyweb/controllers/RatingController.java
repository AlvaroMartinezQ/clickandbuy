package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	private RatingService ratingservice;
	@Autowired
	private UserService userservice;

	@Autowired
	private ProductService productservice;
	
	@GetMapping("/all")
	public Iterable<Rating> getAll() {
		return ratingservice.getAll();
	}
	
	@GetMapping("/rate")
	public ModelAndView rate(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		User user = userservice.getUser(principal.getName());
		model.addAttribute("mail", user.getEmail());
		model.addAttribute("userid", user.getId());
		model.addAttribute("user", user);
		
		return new ModelAndView("marketplace/productsView?id={{id}}");
		
	}

}
