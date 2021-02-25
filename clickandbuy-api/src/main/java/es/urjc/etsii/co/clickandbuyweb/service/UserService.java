package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userdao;
	
	public List<User> getUsers(){
		return userdao.findAll();
	}
	
	public String newUser(String email, String password) {
		User duplicate = userdao.findByUser_email(email);
		if(duplicate!=null) {
			return "status: user email already exists";
		}
		User u = new User();
		u.setUser_email(email);
		u.setUser_password(password);
		Date d = new Date();
		u.setJoin_date(d);
		userdao.save(u);
		return "status: saved";
	}
	
	public User userEmailSearch(String email) {
		User u = userdao.findByUser_email(email);
		if(u==null) {
			return null;
		}
		return u;
	}
	
	public String deleteUserId(int id) {
		Optional<User> u = userdao.findById(id);
		if(u.isEmpty()) {
			return "status: not found";
		}
		userdao.deleteById(id);
		return "status: deleted";
	}
	
	public User loginUser(String email, String password) {
		User u = userdao.findByUser_email(email);
		if(u==null) {
			return null;
		}
		if(!u.getUser_password().equals(password)) {
			return null;
		}
		Date d = new Date();
		u.setLast_login(d);
		userdao.save(u);
		return u;
	}
	
	public List<User> UserNameSearch (String name){
		return userdao.searchByUser_name(name);
	}
	
	public String addUserProduct(String name, String desc, double price, int units, int usid) {
		User u = userdao.findByUser_id(usid);
		if(u==null) {
			return "status: user doesn't exist";
		}
		if(!u.isIs_supplier()) {
			return "status: this user is not a supplier and is not allowed to add products";
		}
		Product p = new Product();
		p.setProduct_name(name);
		p.setProduct_description(desc);
		if(units<0) {
			p.setProduct_stock(0);
		} else {
			p.setProduct_stock(units);
		}
		p.setProduct_price(price);
		if(p.getProduct_stock()>0) {
			p.setHas_stock(true);
			p.setIs_active(true);
		}
		List<Product> productlist = u.getUser_product_list();
		productlist.add(p);
		u.setUser_product_list(productlist);
		userdao.save(u);
		return "status: new product saved -> " + p.toString();
	}
}
