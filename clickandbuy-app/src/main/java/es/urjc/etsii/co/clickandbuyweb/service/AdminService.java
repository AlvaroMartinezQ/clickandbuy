package es.urjc.etsii.co.clickandbuyweb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminDAO admindao;
	
	public List<Admin> getAdmins() {
		return admindao.findAll();
	}
	
	public Admin getAdmin(String email) {
		return admindao.findByEmail(email);
	}
	
	public String newAdmin(String email, String password, String realname, String name, String phone, String charge, String rol) {
		Admin replicate = admindao.findByEmail(email);
		if(replicate!=null) {
			return "status: this administrator already exist";
		}
		replicate = admindao.findByRealname(realname);
		if(replicate!=null) {
			return "status: this administrator already exist";
		}
		Admin admin = new Admin(email,new BCryptPasswordEncoder().encode(password),name,realname,phone,LocalDate.now(),charge,rol);
		admindao.save(admin);
		return "status: saved";
	}

	public String deleteById(int id) {
		Optional<Admin> admin =admindao.findById(id);
		if(admin.isPresent()) {
			admindao.deleteById(id);
			return "status: deleted";
		}
		return "status: not found";
	}
	
	public String adminUpdate(String email, String realname, String name, String phone, String rol) {
		Admin admin = admindao.findByEmail(email);
		if(!realname.isBlank()) {
			admin.setRealname(realname);
		}
		if(!name.isBlank()) {
			admin.setName(name);
		}
		if(!phone.isBlank()) {
			admin.setPhone(phone);
		}
		if(!rol.isBlank()) {
			admin.setName(rol);
		}
		admindao.save(admin);
		return "status: Admin updated";
	}
	
}
