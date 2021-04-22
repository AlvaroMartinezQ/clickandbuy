package es.urjc.etsii.co.clickandbuyweb.dao;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;

@Repository
public interface RatingDAO extends JpaRepository<Rating, Integer>{
	
	@Query("SELECT rating FROM Rating rating WHERE rating.rate=:rate")
	public Rating findByRate(int rate);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete FROM marketplace_rating WHERE product_id=:product_id", nativeQuery = true)
	public void deleteAllByProduct(@Param("product_id")int product_id);

}
