package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	private RatingService ratingservice;

	@GetMapping("/all")
	public List<Rating> getRating() {
		return ratingservice.getRatings();
	}

}
