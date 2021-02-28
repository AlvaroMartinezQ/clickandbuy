package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;

@Repository
public interface RatingDAO extends JpaRepository<Rating, Integer>{
	
	@Query("SELECT rating FROM Rating rating WHERE rating.rate=:rate")
	public Rating findByRate(int rate);

}
