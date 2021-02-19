package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productdao;
	
	public List<Product> getProducts(){
		return productdao.findAll();
	}

}
