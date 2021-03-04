package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;


@Service
public class AdministratorService {
	@Autowired
	private AdminDAO admindao;
	
	public List<Administrator> getAdministrators(){
		return admindao.findAll();
	}
	
	public String newAdmin(String email, String password, String realname, String name, int phone, String charge) {
		Administrator replicate = admindao.findByAdmin_email(email);
		if(replicate!=null) {
			return "status: this administrator already exist";
		}
		replicate = admindao.findByAdmin_realname(realname);
		if(replicate!=null) {
			return "status: this administrator already exist";
		}
		Administrator admin = new Administrator();
		admin.setAdmin_email(email);
		admin.setAdmin_password(password);
		admin.setAdmin_realname(realname);
		admin.setAdmin_name(name);
		admin.setAdmin_phone(phone);
		admin.setAdmin_charge(charge);
		admin.setIs_active(false);
		admin.setIs_superuser(false);
		admin.setLast_login(new Date());
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
	
	public Administrator adminRealNameSearch(String realname) {
		Administrator admin = admindao.findByAdmin_realname(realname);
		if(admin==null) {
			return null;
		}
		return admin;
	}
	
	public Administrator adminIdSearch(int id) {
		Optional<Administrator> admin = admindao.findById(id);
		if(!admin.isPresent()) {
			return null;
		}
		return admin.get();
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
	
	public String adminChangeSuperuser(String email, String password, Boolean superuser) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		if(!admin.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		if(!admin.getAdmin_charge().equals("Manager")) {
			return "You are not allow to do this action";
		}
		admin.setIs_superuser(superuser);
		admindao.save(admin);
		if(superuser)
			return "status: superuser is activated";
		return "status: superuser is desactivated";
	}
	
	public String adminChangeRealName(String email, String password, String newRealname) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		if(!admin.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		if(!admin.getAdmin_charge().equals("Manager")) {
			return "You are not allow to do this action";
		}
		admin.setAdmin_realname(newRealname);
		admindao.save(admin);
		return "status: real name changed";
	}
	
	public String adminChangePhone(String email, String password, int newPhone) {
		Administrator admin = admindao.findByAdmin_email(email);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		if(!admin.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}

		admin.setAdmin_phone(newPhone);
		admindao.save(admin);
		return "status: phone number changed";
	}
	
	public String managerModifyRealname(String email, String password, String adminEmail, String newRealname) {
		Administrator manager = admindao.findByAdmin_email(email);
		if(manager==null) {
			return "This administrator doesn't exist";
		}
		if(!manager.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		if(!manager.getAdmin_charge().equals("Manager")) {
			return "You are not allow to do this action";
		}
		Administrator admin = admindao.findByAdmin_email(adminEmail);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		admin.setAdmin_realname(newRealname);
		admindao.save(admin);
		return "status: "+adminEmail+" real name has changed";	
	}
	
	public String managerModifyCharge(String email, String password, String adminEmail, String newCharge) {
		Administrator manager = admindao.findByAdmin_email(email);
		if(manager==null) {
			return "This administrator doesn't exist";
		}
		if(!manager.getAdmin_password().equals(password)) {
			return "Incorrect password";
		}
		if(!manager.getAdmin_charge().equals("Manager")) {
			return "You are not allow to do this action";
		}
		Administrator admin = admindao.findByAdmin_email(adminEmail);
		if(admin==null) {
			return "This administrator doesn't exist";
		}
		admin.setAdmin_charge(newCharge);
		admindao.save(admin);
		return "status: "+adminEmail+" charge has changed";	
	}
	

}
