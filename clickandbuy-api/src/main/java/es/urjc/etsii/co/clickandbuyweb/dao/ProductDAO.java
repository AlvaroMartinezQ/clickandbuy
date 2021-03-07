package es.urjc.etsii.co.clickandbuyweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.urjc.etsii.co.clickandbuyweb.models.Product;


@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.product_name=:name")
	public Product findByProduct_name(String name);
	
	@Query("SELECT p FROM Product p WHERE p.id=:id AND p.is_active=TRUE")
	public Product findByProductId(int id);
	
	@Query("SELECT p FROM Product p WHERE p.is_active=TRUE")
	public List<Product> findAllProducts();
	
	@Query("SELECT p FROM Product p WHERE p.product_name LIKE :name% AND p.is_active=TRUE")
	public List<Product> findByProduct_nameL(String name);
	
	@Query("SELECT p FROM Product p WHERE p.has_stock=TRUE AND p.is_active=TRUE")
	public List<Product> findAllStock();
	
	@Query("SELECT p FROM Product p WHERE p.is_active=TRUE")
	public List<Product> findAllActive();
	
	@Query("SELECT p FROM Product p WHERE p.product_price<=:price AND p.is_active=TRUE")
	public List<Product> findPriceLe(double price);
	
	@Query("SELECT p FROM Product p WHERE p.product_price>=:price AND p.is_active=TRUE")
	public List<Product> findPriceGe(double price);
	
	@Query("SELECT p FROM Product p WHERE p.product_price>=:price1 AND p.product_price<=:price2 AND p.is_active=TRUE")
	public List<Product> findPriceBe(double price1, double price2);

}
