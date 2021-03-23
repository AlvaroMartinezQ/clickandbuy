package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Admin;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer>{

	@Query("SELECT admin FROM Admin admin WHERE admin.email=:email")
	public Admin findByEmail(String email);
	
	@Query("SELECT admin FROM Admin admin WHERE admin.realname=:realname")
	public Admin findByRealname(String realname);
	
}
