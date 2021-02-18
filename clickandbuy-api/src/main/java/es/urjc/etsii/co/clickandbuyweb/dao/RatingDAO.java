package es.urjc.etsii.co.clickandbuyweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Rating;

@Repository
public interface RatingDAO extends JpaRepository<Rating, Integer>{

}
