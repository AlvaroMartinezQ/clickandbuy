package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.RatingDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
@Transactional
public class RatingService {
	@Autowired
	private RatingDAO ratingdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private ProductDAO productdao;
	
	public List<Rating> getAll() {
		return ratingdao.findAll();
	}
	
	public Optional<Rating> getRating(int id) {
		return ratingdao.findById(id);
	}
	
	public String newRating(String comment, int rate, int idUser, int idProduct) {
		//We need to know if user has bought a product
		Optional<User> user = userdao.findById(idUser);
		if(!user.isPresent())
			return "status: user doesn't exist!";
		Optional<Product> product = productdao.findById(idProduct);
		if(!product.isPresent())
			return "status: product doesn't exist!";
		Rating rating = new Rating(comment,rate,user.get(),product.get());
		ratingdao.save(rating);
		return "status: saved";
	}
	
	public String save(Rating rating) {
		ratingdao.save(rating);
		return "status: saved";
	}
	
	public String deleteRating(int id, int idUser) {
		Optional<Rating> rating = ratingdao.findById(id);
		Optional<User> user = userdao.findById(idUser);
		if(!rating.isPresent())
			return "status: rating not found";
		if(!user.isPresent())
			return "status: user not found";
		if(rating.get().getUser().getId() != user.get().getId())
			return "status: you are not the owner of this rating";
		ratingdao.deleteById(id);
		return "status: rating deleted";
	}
	
	public List<Rating> getRatingsSorted() {
		List<Rating> ratingSorted = ratingdao.findAll();
		Collections.sort(ratingSorted, new Comparator<Rating>() {
			public int compare(Rating r1, Rating r2) {
				return r1.getRate()>r2.getRate()? -1: (r1.getRate()==r2.getRate()? 0: 1);
			}
		});
		
		return ratingSorted;
	}
	
}
