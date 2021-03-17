package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.validator.SingIn;
import es.urjc.etsii.co.clickandbuyweb.validator.SingUp;
import mailer.WelcomeEmail;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDAO udao;
	@Autowired
	private SingUp singUpValidator;
	@Autowired
	private SingIn singInValidator;
	
	public Iterable<User> getUsers(){
		return udao.findAll();
	}
	
	public String saveUser(String email, String password) {
		User duplicate = udao.findByUserEmail(email);
		if(duplicate!=null) {
			return "status: user email already exists";
		}
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		Date d = new Date();
		u.setJoin_date(d);
		u.setIs_active(true);
		udao.save(u);
		return "status: saved";
	}
	
	public String updateUser(String userEmail, String name, String realname, String realsurnames, String address, String phone, String bankaccount) {
		User u=udao.findByUserEmail(userEmail);
		if(u==null) {
			return "status: no existing user";
		}
		if(!name.equals("")) {
			u.setName(name);
		}
		if(!realname.equals("")) {
			u.setRealname(realname);
		}
		if(!realsurnames.equals("")) {
			u.setRealsurnames(realsurnames);
		}
		if(!address.equals("")) {
			u.setAddress(address);
		}
		if(!phone.equals("")) {
			u.setPhone(phone);
		}
		if(!bankaccount.equals("")) {
			u.setBankaccount(bankaccount);
		}
		return "status: updated";
	}
	
	public String deleteUser(String usid) {
		int id=Integer.parseInt(usid);
		User u=udao.findByUserId(id);
		if(u==null) {
			return "status: user doesn't exist";
		}
		udao.delete(u);
		return "status: deleted";
	}
	
	public int singUpUser(String email, String emailConfirmation, String password, String passwordConfirmation) {
		int status=singUpValidator.validateUser(email, emailConfirmation, password, passwordConfirmation);
		if(status!=0) {
			return status;
		}
		User u=new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setIs_active(true);
		udao.save(u);
		sendWelcomeMail(u.getEmail());
		return status;
	}
	
	private void sendWelcomeMail (String emailTo) {
		System.out.println("sending email");
		WelcomeEmail we=new WelcomeEmail(emailTo);
		we.setUp();
	}
	
	public boolean singInUser(String email, String password) {
		if(email.equals("")||password.equals("")) {
			return false;
		}
		User u=udao.findByUserEmail(email);
		if(u==null) {
			return false;
		}
		boolean success=singInValidator.validateLogin(u, password);
		return success;
	}
}
