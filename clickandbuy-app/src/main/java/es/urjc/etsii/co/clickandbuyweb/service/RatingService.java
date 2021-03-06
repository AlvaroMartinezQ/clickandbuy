package es.urjc.etsii.co.clickandbuyweb.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.RatingDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
@Transactional
public class RatingService implements Serializable{
	
	@Autowired
	private RatingDAO ratingdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private ProductDAO productdao;
	@Autowired
	private AdminDAO admindao;
	@Autowired
	private ProductService productservice;
	
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
		Rating rating = new Rating(comment,rate,user.get());
		ratingdao.save(rating);
		return "status: saved";
	}
	
	public String save(Rating rating) {
		ratingdao.save(rating);
		return "status: saved";
	}
	
	public boolean deleteRating(int id, String email) {
		Optional<Rating> rating = ratingdao.findById(id);
		User user = userdao.findByUserEmail(email);
		Admin admin = admindao.findByEmail(email);
		if(!rating.isPresent())
			return false;
		if(user==null && admin==null)
			return false;
		if(user!=null) {
			if(rating.get().getUser().getEmail() != email)
				return false;
		}
		ratingdao.deleteById(id);
		return true;
	}
	
	public List<Rating> getAllRatingsSorted() {
		List<Rating> ratingSorted = ratingdao.findAll();
		Collections.sort(ratingSorted, new Comparator<Rating>() {
			public int compare(Rating r1, Rating r2) {
				return r1.getRate()>r2.getRate()? -1: (r1.getRate()==r2.getRate()? 0: 1);
			}
		});
		
		return ratingSorted;
	}
	
	public List<Rating> getRatingSorted(List<Rating> ratingSorted) {
		Collections.sort(ratingSorted, new Comparator<Rating>() {
			public int compare(Rating r1, Rating r2) {
				return r1.getRate()>r2.getRate()? -1: (r1.getRate()==r2.getRate()? 0: 1);
			}
		});
		
		return ratingSorted;
	}
	
	public String deleteAllRatingsFromProduct(int product_id) {
		Product product = productservice.getProduct(product_id);
		ratingdao.deleteAll(product.getRating());
		product.getRating().clear();
		productservice.saveUpdateProduct(product);
		return "status: all ratings deleted";
	}
	
	public String deleteAllRatingsFromProductWithoutUpdate(int product_id) {
		Product product = productservice.getProduct(product_id);
		ratingdao.deleteAll(product.getRating());
		return "status: all ratings deleted";
	}
	
}
