package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userdao;
	
	public List<User> getUsers(){
		return userdao.findAll();
	}
	
	public String newUser(String email, String password) {
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
}
