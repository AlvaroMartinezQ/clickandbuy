package es.urjc.etsii.co.clickandbuyweb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class SingUp {
	@Autowired
	private UserDAO udao;
	
	/*
	 * Code errors for user validation:
	 * 0 | All good
	 * -1 | empty fields
	 * -2 | fields don't match
	 * -3 | email taken
	 */
	public int validateUser(String email, String emailConfirmation, String password, String passwordConfirmation) {
		if(email.equals("")||emailConfirmation.equals("")||password.equals("")||passwordConfirmation.equals("")) {
			return -1;
		}
		if(!email.equals(emailConfirmation)||!password.equals(passwordConfirmation)) {
			return -2;
		}
		User u=udao.findByUserEmail(email);
		if(u!=null) {
			return -3;
		}
		return 0;
	}
}
