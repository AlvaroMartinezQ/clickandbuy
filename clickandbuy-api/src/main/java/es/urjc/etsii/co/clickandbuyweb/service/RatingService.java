package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.RatingDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
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
	private AdminDAO admindao;
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
	
	public String deleteRatingByAdmin(int id, int idAdmin) {
		Optional<Rating> rating = ratingdao.findById(id);
		Optional<Administrator> admin = admindao.findById(idAdmin);
		if(!rating.isPresent())
			return "status: rating not found";
		if(!admin.isPresent())
			return "status: administrator not found";
		ratingdao.deleteById(id);
		return "status: rating deleted";
	}
	
	

}
