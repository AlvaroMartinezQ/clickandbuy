package es.urjc.etsii.co.clickandbuyweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.name=:name")
	public Product findByname(String name);
	
	@Query("SELECT p FROM Product p WHERE p.id=:id")
	public Product findByProductId(int id);
	
	@Query("SELECT p FROM Product p WHERE p.active=TRUE")
	public List<Product> findAllActive();
	
	@Query("SELECT p FROM Product p WHERE p.price<=:price")
	public List<Product> findPriceLe(double price);
	
	@Query("SELECT p FROM Product p WHERE p.price>=:price")
	public List<Product> findPriceGe(double price);
	
	@Query("SELECT p FROM Product p WHERE p.price>=:price1 AND p.price<=:price2")
	public List<Product> findPriceBe(double price1, double price2);
	

}
