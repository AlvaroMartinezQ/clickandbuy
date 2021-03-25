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
	
	public int updateUser(String email, String name, String realname, String phone, String bankaccount, String address, String realsurnames) {
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
		udao.save(u);
		return 0;
	}
	
}
