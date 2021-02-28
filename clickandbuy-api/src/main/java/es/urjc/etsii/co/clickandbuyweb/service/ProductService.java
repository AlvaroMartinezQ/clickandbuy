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
	
	public Product productNameSearch(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if(p == null) {
			return null;
		}
		return p;
	}
	
	public Product productNameSearchL(String product_name) {
		Product p = productdao.findByProduct_nameL(product_name);
		if(p == null) {
			return null;
		}
		return p;
	}
	
	public int productStock(String product_name){
		Product p = productdao.findByProduct_name(product_name);
		if(p == null) {
			return 0;
		}
		return p.getProduct_stock();
	}
	
	public String productDescription(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if(p == null) {
			return null;
		}
		return p.getProduct_description();
	}
	
	public List<Product> productHasStock() {
		List<Product> list = productdao.findAllStock();
		if(list.isEmpty())return null;
		return list;
	}
	
	public boolean productHasStockName(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if(p == null)return false;
		return p.isHas_stock();
	}
	
	public List<Product> productIsActive() {
		List<Product> list = productdao.findAllActive();
		if(list.isEmpty())return null;
		return list;
	}
	
	public boolean productIsActiveName(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if(p == null)return false;
		return p.isIs_active();
	}
	
	public List<Product> productsPriceLe(double price){
		List<Product> list = productdao.findPriceLe(price);
		if(list.isEmpty())return null;
		return list;
	}
	
	public List<Product> productsPriceGe(double price){
		List<Product> list = productdao.findPriceGe(price);
		if(list.isEmpty())return null;
		return list;
	}
	
	public List<Product> productsPriceBe(double price1, double price2){
		List<Product> list = productdao.findPriceBe(price1, price2);
		if(list.isEmpty())return null;
		return list;
	}	
	
}
