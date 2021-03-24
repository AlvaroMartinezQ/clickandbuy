package es.urjc.etsii.co.clickandbuyweb.initBBDD;



import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private AdminDAO admindao;
	
	@Autowired
	private UserDAO userdao;
	
	@PostConstruct
	private void initDatabse() {
		if(admindao.findByEmail("manager@admin.es")==null)
			admindao.save(new Admin("manager@admin.es",new BCryptPasswordEncoder().encode("1"),"Patrii","Patricia Tarazaga","6456875",LocalDate.now(), "Manager","MANAGER_ROLE"));
		
		if(userdao.findByUserEmail("user@proveedor.es")== null) {
			Date date = new Date();
			userdao.save(new User("user@proveedor.es",new BCryptPasswordEncoder().encode("1"),"LB","Luis","Blay","Calle inventada, 3","123456","123456",date,true,true,"SUPPLIER_ROLE"));
		}
		
		if(userdao.findByUserEmail("user@comprador.es")== null) {
			Date date = new Date();
			userdao.save(new User("user@comprador.es",new BCryptPasswordEncoder().encode("1"),"AQ","Alvaro","Quiroga","Calle inventada, 3","123456","123456",date,true,true,"NOT_SUPPLIER_ROLE"));
		}
			
	}
}