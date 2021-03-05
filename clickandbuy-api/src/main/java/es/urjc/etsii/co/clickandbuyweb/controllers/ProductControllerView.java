package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/products")
public class ProductControllerView {
	
	@Autowired
	private ProductService productservice;
	@Autowired
	private UserService userservice;
	
	@GetMapping("/main")
	public ModelAndView userMain(Model model) {
		return new ModelAndView("/products/productsMain");
	}
	
	@GetMapping("/all")
	public ModelAndView productsAll(Model model) {
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("/products/productList");
	}
	
	@GetMapping("/stock")
	public ModelAndView productsStock(Model model) {
		model.addAttribute("products",productservice.productHasStock());
		return new ModelAndView("/products/productList");	
	}
	
	@GetMapping("/search")
	public ModelAndView productSearch() {
		return new ModelAndView("/products/productSearch");	
	}
	
	@GetMapping("/searchdata")
	public ModelAndView productSearchData(@RequestParam(required=true, defaultValue="@") String name, Model model) {
		model.addAttribute("products",productservice.productNameSearchL(name));
		return new ModelAndView("/products/productList");	
	}
	
	@GetMapping("/pricele")
	public ModelAndView productPriceLe(@RequestParam(required=true, defaultValue="-1") String price, Model model) {
		model.addAttribute("products", productservice.productsPriceLe(Double.parseDouble(price)));
		return new ModelAndView("/products/");	
	}
	
	@GetMapping("/pricege")
	public ModelAndView productPriceGe(@RequestParam(required=true, defaultValue="10000000") String price, Model model) {
		model.addAttribute("products", productservice.productsPriceGe(Double.parseDouble(price)));
		return new ModelAndView("/products/");	
	}
	
	@GetMapping("/pricebe")
	public ModelAndView productPriceBe(@RequestParam(required=true, defaultValue="-10") String price1, @RequestParam(required=true, defaultValue="-1") String price2, Model model) {
		model.addAttribute("products", productservice.productsPriceBe(Double.parseDouble(price1),Double.parseDouble(price2)));
		return new ModelAndView("/products/");	
	}
	

}
