package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;


@Service
public class AdministratorService {
	@Autowired
	private AdminDAO admindao;
	
	public List<Administrator> getAdministrator(){
		return admindao.findAll();
	}

}
