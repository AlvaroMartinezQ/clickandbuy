package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.RatingDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.models.User;


@Service
public class RatingService {

	@Autowired
	private RatingDAO ratingdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private ProductDAO productdao;

	public List<Rating> getRatings() {
		return ratingdao.findAll();
	}
	
	public String newRating(String comment, int rate, int idUser, int idProduct) {
		Rating rating = new Rating();
		rating.setComment(comment);
		rate = (rate >= 0 && rate<=5) ? rate : 0;
		rating.setRate(rate);
		Optional<User> user = userdao.findById(idUser);
		if(!user.isPresent())
			return "status: user doesn't exist!";
		rating.setUser(user);
		Optional<Product> product = productdao.findById(idProduct);
		if(!product.isPresent())
			return "status: product doesn't exist!";
		rating.setProduct(product);
		ratingdao.save(rating);
		return "status: saved";
	}
	
	

}
