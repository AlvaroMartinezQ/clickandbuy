package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class ApiRatingController {
	@Autowired
	private RatingService ratingservice;

	@GetMapping("/all")
	public List<Rating> getRating() {
		return ratingservice.getRatings();
	}
	
	@PostMapping("/add")
	public String newRating(@RequestParam(required=true) String comment, @RequestParam(required=true) int rate, @RequestParam(required=true) int idUser, @RequestParam(required=true) int idProduct) {
		return ratingservice.newRating(comment, rate, idUser, idProduct);
	}
	
	@DeleteMapping("/delete")
	public String deleteRating(@RequestParam(required=true) int id, @RequestParam(required=true) int idUser) {
		return ratingservice.deleteRating(id, idUser);
	}
	
	@DeleteMapping("/deleteByAdmin")
	public String deleteRatingByAdmin(@RequestParam(required=true) int id, @RequestParam(required=true) int idAdmin) {
		return ratingservice.deleteRatingByAdmin(id, idAdmin);
	}

}
