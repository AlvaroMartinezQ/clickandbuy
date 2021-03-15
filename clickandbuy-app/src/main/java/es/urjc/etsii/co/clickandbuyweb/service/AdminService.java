package es.urjc.etsii.co.clickandbuyweb.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminDAO adao;
	
	public Iterable<Admin> getAll() {
		return adao.findAll();
	}
	
	public String saveAdmin(String adminEmail, String password) {
		Admin a=new Admin();
		// Validations should be before
		a.setemail(adminEmail);
		a.setpassword(password);
		return "status: saved";
	}
	
	public String updateUser(String adminEmail, String name, String realname, String phone, String adminCharge, boolean active, boolean superuser) {
		Admin a=adao.findByAdminEmail(adminEmail);
		if(a==null) {
			return "status: admin not found";
		}
		if(!name.equals("")) {
			a.setname(name);
		}
		if(!realname.equals("")) {
			a.setrealname(realname);
		}
		if(!phone.equals("")) {
			a.setphone(phone);
		}
		if(!adminCharge.equals("")) {
			/*
			 * SUPERUSER | ADMIN | STAFF
			 */
			if(adminCharge.equals("SUPERUSER")||adminCharge.equals("ADMIN")||adminCharge.equals("STAFF")) {
				a.setAdmin_charge(adminCharge);
			} else {
				return "status: bad role for admin";
			}
		}
		if(active) {
			a.setActive(true);
		} else {
			a.setActive(false);
		}
		if(superuser) {
			a.setSuperuser(true);
		} else {
			a.setSuperuser(false);
		}
		adao.save(a);
		return "status: saved";
	}
	
	public String deleteAdmin(String adminid) {
		int id=Integer.parseInt(adminid);
		Admin a=adao.findByAdminId(id);
		if(a==null) {
			return "status: admin not found";
		}
		adao.delete(a);
		return "status: deleted";
	}
	
}
