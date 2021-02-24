package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/all")
	public List<Product> getProduct(){
		return productservice.getProducts();
	}
	
	@GetMapping("/search")
	public Product productNameSearch(@RequestParam(required=true) String name) {
		return productservice.productNameSearch(name);
	}
	@GetMapping("/productstock")
	public int productStock(@RequestParam(required=true) String name) {
		return productservice.productStock(name);
	}
	
	@GetMapping("/description")
	public String productDescription(@RequestParam(required=true) String name) {
		return productservice.productDescription(name);
	}
	
	@GetMapping("/stocklist")
	public List<Product> productStock(){
		return productservice.productHasStock();
	}
	
	@GetMapping("/stock")
	public boolean productStockName(String name){
		return productservice.productHasStockName(name);
	}
	
	@GetMapping("/activelist")
	public List<Product> productActive(){
		return productservice.productIsActive();
	}
	
	@GetMapping("/active")
	public boolean productIsActiveName(String name){
		return productservice.productIsActiveName(name);
	}
	
	@GetMapping("/pricele")
	public List<Product> productsPriceLe(double price){
		return productservice.productsPriceLe(price);
	}
	@GetMapping("/pricege")
	public List<Product> productsPricGe(double price){
		return productservice.productsPriceGe(price);
	}
	
	@GetMapping("/pricebe")
	public List<Product> productsPricBe(double price1, double price2){
		return productservice.productsPriceBe(price1, price2);
	}
	
}
