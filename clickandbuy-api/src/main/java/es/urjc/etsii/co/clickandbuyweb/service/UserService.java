package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userdao;
	
	public List<User> getUsers(){
		return userdao.findAll();
	}
}
