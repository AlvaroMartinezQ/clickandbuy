package es.urjc.etsii.co.clickandbuyweb.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {
	@Autowired
	private ProductService pservice;;
	
	@GetMapping("/all")
	public Iterable<Product> getAll() {
		return pservice.getAll();
	}
	
	@PostMapping("/new")
	public Product addProduct(@RequestParam(required=true)String userEmail, @RequestParam(required=true)Double price,
								@RequestParam(required=true)String name, @RequestParam(required=true)String description,
								@RequestParam(required=true)int stock) {
		return pservice.saveProduct(userEmail, price, name, description, stock);
	}
	
	@PostMapping("/update")
	public String updateProduct(@RequestParam(required=true)String id, @RequestParam(required=false)String price,
								@RequestParam(required=false)String name, @RequestParam(required=false)String description,
								@RequestParam(required=false)String stock) {
		return pservice.updateProduct(id, price, name, description, stock);
	}
	
	@DeleteMapping("/delete")
	public String deleteProduct(@RequestParam(required=true)String id) {
		return pservice.deleteProduct(id);
	}
	
}
