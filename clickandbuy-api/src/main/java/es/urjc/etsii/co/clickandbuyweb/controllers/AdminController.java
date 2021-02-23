package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
import es.urjc.etsii.co.clickandbuyweb.service.AdministratorService;

@RestController
@RequestMapping("/api/administrator")
public class AdminController {
	@Autowired
	private AdministratorService adminservice;
	
	@GetMapping("/all")
	public List<Administrator> getAdministrators(){
		return adminservice.getAdministrators();
	}

}
