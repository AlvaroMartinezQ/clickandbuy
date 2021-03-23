package es.urjc.etsii.co.clickandbuyweb.initBBDD;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private AdminDAO admindao;
	
	@PostConstruct
	private void initDatabse() {
		//Create first manager in BBDD, after that, comment this line
		//admindao.save(new Admin("manager@admin.es",new BCryptPasswordEncoder().encode("1"),"Patrii","Patricia Tarazaga","6456875",LocalDate.now(), "Manager","MANAGER_ROLE"));
	}
}