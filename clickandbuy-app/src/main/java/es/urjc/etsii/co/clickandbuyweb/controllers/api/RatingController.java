package es.urjc.etsii.co.clickandbuyweb.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	@Autowired
	private RatingService rservice;
	
	@GetMapping("/all")
	public Iterable<Rating> getAll() {
		return rservice.getAll();
	}
	
	@PostMapping("/new")
	public String saveRating(@RequestParam(required=true)String userEmail, @RequestParam(required=true)String productpk,
								@RequestParam(required=true)String rate, @RequestParam(required=true)String comment) {
		return rservice.saveRating(userEmail, productpk, rate, comment);
	}
	
}
