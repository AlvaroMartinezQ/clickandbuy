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
	public UserImage findByUserId(@Param("usid") int usid);
	
	@Query("SELECT img FROM UserImage img WHERE img.usid=:usid")
	public byte[] findById_bytes(@Param("usid") int usid);
	
	@Query("SELECT img FROM UserImage img WHERE img.usid=:usid")
	public Optional<UserImage> findById(@Param("usid") int usid);
	
}
