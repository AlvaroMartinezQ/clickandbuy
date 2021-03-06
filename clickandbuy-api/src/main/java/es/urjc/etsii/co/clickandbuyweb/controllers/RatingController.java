package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.service.RatingService;

@RestController
public class RatingController {


	@Autowired
	private RatingService ratingservice;
	
	@GetMapping("/ratings")
	public ModelAndView adminMain(Model model) {
		return new ModelAndView("/ratings/ratingsMain");
	}
}
