package es.urjc.etsii.co.clickandbuyweb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class UpdateUser {
	@Autowired
	private UserDAO udao;
	
	/*
	 * Code errors for user validation:
	 * 0 | all good
	 * -1 | bad fields
	 * -2 | user not found
	 */
	
	public int updateUser(String email, String name, String realname, String phone, String bankaccount, String address, String is_active, String is_supplier, String realsurnames) {
		User u=udao.findByUserEmail(email);
		if(u==null) {
			return -2;
		}
		if(!name.equals("")) {
			u.setName(name);
		}
		if(!realname.equals("")) {
			u.setRealname(realname);
		}
		if(!phone.equals("")) {
			u.setPhone(phone);
		}
		if(!bankaccount.equals("")) {
			u.setBankaccount(bankaccount);
		}
		if(!address.equals("")) {
			u.setAddress(address);
		}
		if(!realsurnames.equals("")) {
			u.setRealsurnames(realsurnames);
		}
		if(!is_active.equals("")) {
			int active=Integer.parseInt(is_active);
			if(active<0) {
				u.setIs_active(false);
			} else {
				u.setIs_active(true);
			}
		}
		if(!is_supplier.equals("")) {
			int supplier=Integer.parseInt(is_active);
			if(supplier<0) {
				u.setIs_supplier(false);
			} else {
				u.setIs_supplier(true);
			}
		}
		udao.save(u);
		return 0;
	}
	
}
