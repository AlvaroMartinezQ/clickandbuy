package es.urjc.etsii.co.clickandbuyweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.user_email=:email")
	public User findByUser_email(String email);
	
	@Query("SELECT u FROM User u WHERE u.user_name LIKE %:name%")
	public List<User> searchByUser_name(@Param("name") String name);
	
	@Query("SELECT u FROM User u WHERE u.id=:id")
	public User findByUser_id(int id);
	
}
