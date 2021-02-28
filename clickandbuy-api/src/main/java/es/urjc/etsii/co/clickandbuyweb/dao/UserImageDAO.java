package es.urjc.etsii.co.clickandbuyweb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.UserImage;

@Repository
public interface UserImageDAO extends JpaRepository<UserImage, Integer> {
	
	@Query("SELECT img FROM UserImage img WHERE img.usid=:usid")
	public UserImage findByUser_id(@Param("usid") int usid);
	
	@Query("SELECT img FROM UserImage img WHERE img.usid=:usid")
	public Optional<UserImage> findbyid(@Param("usid") int usid);
	
}
