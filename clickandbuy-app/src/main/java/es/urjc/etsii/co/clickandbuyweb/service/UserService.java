package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.validator.SingIn;
import es.urjc.etsii.co.clickandbuyweb.validator.SingUp;
import es.urjc.etsii.co.clickandbuyweb.validator.UpdateUser;
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
	@Autowired
	private UpdateUser updateUserValidator;
	
	public Iterable<User> getUsers(){
		return udao.findAll();
	}
	
	public User getUser(String email) {
		return udao.findByUserEmail(email);
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
	
	public String updateUserApi(String userEmail, String name, String realname, String realsurnames, String address, String phone, String bankaccount) {
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
	
	public int singUpUser(String email, String emailConfirmation, String password, String passwordConfirmation, boolean is_supplier) {
		int status=singUpValidator.validateUser(email, emailConfirmation, password, passwordConfirmation);
		if(status!=0) {
			return status;
		}
		User u=new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setIs_active(true);
		u.setIs_supplier(is_supplier);
		if (is_supplier) {
			List<String> roles = new ArrayList<>();
			roles.add("ROLE_SUPPLIER");
			u.setRoles(roles);
		} else {
			List<String> roles = new ArrayList<>();
			roles.add("ROLE_NOTSUPPLIER");
			u.setRoles(roles);
		}
		u.setJoin_date(new Date());
		udao.save(u);
		
		/*
		 * Next lines runs the email send in a different threads
		 */
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(() -> sendWelcomeMail(u.getEmail()));
		executor.shutdown();
		return status;
	}
	
	private void sendWelcomeMail (String emailTo) {
		WelcomeEmail we=new WelcomeEmail(emailTo);
		we.setUp();
	}
	
	public void updateLogin(User u) {
		Date d=new Date();
		u.setLast_login(d);
		udao.save(u);
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
	
	public int updateUser(String email, String name, String realname, String phone, String bankaccount, String address, String realsurnames) {
		return updateUserValidator.updateUser(email, name, realname, phone, bankaccount, address,  realsurnames);
	}
}
