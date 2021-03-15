package es.urjc.etsii.co.clickandbuyweb.service;

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
	private RatingDAO rdao;
	@Autowired
	private UserDAO udao;
	@Autowired
	private ProductDAO pdao;
	
	public Iterable<Rating> getAll() {
		return rdao.findAll();
	}
	
	// The user SHOULD have purchased the product first
	public String saveRating(String userEmail, String productpk, String rate, String comment) {
		User u=udao.findByUserEmail(userEmail);
		if(u==null) {
			return "status: user not found";
		}
		int productid=Integer.parseInt(productpk);
		Product p=pdao.findByProductId(productid);
		if(p==null) {
			return "status: product not found";
		}
		Rating r=new Rating();
		r.setComment(comment);
		int rating=Integer.parseInt(rate);
		r.setRate(rating);
		r.setUser(u);
		r.setProduct(p);
		rdao.save(r);
		return "status: saved";
	}
	
}
