package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
import es.urjc.etsii.co.clickandbuyweb.models.User;


@Service
public class AdministratorService {
	@Autowired
	private AdminDAO admindao;
	
	public List<Administrator> getAdministrators(){
		return admindao.findAll();
	}
	
	public String newAdmin(String email, String password, String charge) {
		Administrator admin = new Administrator();
		admin.setAdmin_email(email);
		admin.setAdmin_password(password);
		admin.setAdmin_charge(charge);
		Date d = new Date();
		admin.setLast_login(d);
		admindao.save(admin);
		return "status: saved";
	}
	
	public String deleteAdminId(int id) {
		Optional<Administrator> admin =admindao.findById(id);
		if(admin.isPresent()) {
			admindao.deleteById(id);
			return "status: deleted";
		}
		return "status: not found";
	}
	
	public Administrator loginAdmin(String email, String password) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return null;
		}
		if(!admin.getAdmin_password().equals(password)) {
			return null;
		}
		Date d = new Date();
		admin.setLast_login(d);
		admindao.save(admin);
		return admin;
	}
	
	public Administrator adminEmailSearch(String email) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return null;
		}
		return admin;
	}
	
	public String adminChangePassword(String email, String password, String newPassword) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		if(!admin.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		admin.setAdmin_password(newPassword);
		admindao.save(admin);
		return "status: password changed";
	}
	
	public String adminChangeName(String email, String password, String newName) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		if(!admin.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		admin.setAdmin_name(newName);
		admindao.save(admin);
		return "status: name changed";
	}

}
