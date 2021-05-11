package es.urjc.etsii.co.clickandbuyweb.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import cacheTests.Sleep;

@Service
@Transactional
@CacheConfig(cacheNames="admins")
public class AdminService implements Serializable{
	
	@Autowired
	private AdminDAO admindao;
	
	private Sleep sleep = new Sleep();
	
	@Cacheable(cacheNames = "admins")
	public List<Admin> getAdmins() {
		sleep.sleep(3000);
		return admindao.findAll();
	}
	
	public Admin getAdmin(String email) {
		return admindao.findByEmail(email);
	}
	
	@CacheEvict(cacheNames = "admins", allEntries=true)
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

	@CacheEvict(cacheNames = "admins", allEntries=true)
	public String deleteById(int id) {
		Optional<Admin> admin =admindao.findById(id);
		if(admin.isPresent()) {
			admindao.deleteById(id);
			return "status: deleted";
		}
		return "status: not found";
	}
	
	@CacheEvict(cacheNames = "admins", allEntries=true)
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
