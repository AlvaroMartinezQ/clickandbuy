package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.RatingDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;

@Service
public class RatingService {

	@Autowired
	private RatingDAO ratingdao;

	public List<Rating> getRatings() {
		return ratingdao.findAll();
	}

}
