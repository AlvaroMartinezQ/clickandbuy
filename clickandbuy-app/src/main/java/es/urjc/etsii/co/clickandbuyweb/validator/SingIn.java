package es.urjc.etsii.co.clickandbuyweb.validator;

import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class SingIn {
	
	/*
	 * Return error for users shouldn't give out information if the user exists or not.
	 * Return only TRUE or FALSE
	 */
	public boolean validateLogin(User user, String password) {
		return user.getPassword().equals(password) ? true : false;
	}
}
