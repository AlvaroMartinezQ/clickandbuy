package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;

@Repository
public interface AdminDAO extends JpaRepository<Administrator, Integer>{

	@Query("SELECT admin FROM Administrator admin WHERE admin.admin_email=:email")
	public Administrator findByAdmin_email(String email);
	
	@Query("SELECT admin FROM Administrator admin WHERE admin.admin_realname=:realname")
	public Administrator findByAdmin_realname(String realname);
}
