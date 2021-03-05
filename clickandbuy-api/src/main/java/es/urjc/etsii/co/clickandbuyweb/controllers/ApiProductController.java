package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ApiProductController {
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/all")
	public List<Product> getProduct(){
		return productservice.getProducts();
	}
	
	@GetMapping("/search")
	public List<Product> productNameSearch(@RequestParam(required=true) String name) {
		return productservice.productNameSearchL(name);
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
	public boolean productStockName(@RequestParam(required=true) String name){
		return productservice.productHasStockName(name);
	}
	
	@GetMapping("/activelist")
	public List<Product> productActive(){
		return productservice.productIsActive();
	}
	
	@GetMapping("/active")
	public boolean productIsActiveName(@RequestParam(required=true)String name){
		return productservice.productIsActiveName(name);
	}
	
	@GetMapping("/pricele")
	public List<Product> productsPriceLe(@RequestParam(required=true)double price){
		return productservice.productsPriceLe(price);
	}
	@GetMapping("/pricege")
	public List<Product> productsPricGe(@RequestParam(required=true)double price){
		return productservice.productsPriceGe(price);
	}
	
	@GetMapping("/pricebe")
	public List<Product> productsPricBe(@RequestParam(required=true) double price1, @RequestParam(required=true) double price2){
		return productservice.productsPriceBe(price1, price2);
	}
	
	@GetMapping("/unset-active")
	public String unSetActive(@RequestParam(required=true)String name) {
		return productservice.unSetActive(name);
	}
	
	@GetMapping("/set-active")
	public String setActive(@RequestParam(required=true)String name) {
		return productservice.setActive(name);
	}
	
	@GetMapping("/set-stock")
	public String setStock(@RequestParam(required=true)String name) {
		return productservice.setStock(name);
	}
	
	@GetMapping("/unset-stock")
	public String unSetStock(@RequestParam(required=true)String name) {
		return productservice.unSetStock(name);
	}
	
}
