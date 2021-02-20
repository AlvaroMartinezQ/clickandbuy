package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Administrator;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@RestController
@RequestMapping("/database")
public class DatabaseController {
	@Autowired
	private UserDAO userdao;
	@Autowired
	private AdminDAO admindao;
	@Autowired
	private ProductDAO productdao;
	
	@PostConstruct
	private void initData() {
		/*
		Product p = new Product();
		p.setIs_active(true);
		p.setProduct_price(12.07);
		p.setProduct_name("Limpiador");
		p.setProduct_description("Limpiador de teclados");
		
		Product p2 = new Product();
		p2.setIs_active(true);
		p2.setProduct_price(15.00);
		p2.setProduct_name("Alfombrilla");
		p2.setProduct_description("Alfombrilla para ratones");
		
		Product p3 = new Product();
		p3.setIs_active(true);
		p3.setProduct_price(2.09);
		p3.setProduct_name("Goma de borrar");
		p3.setProduct_description("Goma de borrar para lapices");
		
		Product p4 = new Product();
		p4.setIs_active(true);
		p4.setProduct_price(7.90);
		p4.setProduct_name("Cuadernos");
		p4.setProduct_description("Set de 3 cuadernos escolares");
		
		Product p5 = new Product();
		p5.setIs_active(true);
		p5.setProduct_price(29.09);
		p5.setProduct_name("Raton");
		p5.setProduct_description("Raton con cable para ordenadores");
		
		Product p6 = new Product();
		p6.setIs_active(true);
		p6.setProduct_price(102.89);
		p6.setProduct_name("Teclado Razer");
		p6.setProduct_description("Teclado Razer Chroma BlackWidow Elite - Mars white edition");
		
		List<Product> list_products = new ArrayList<>();
		List<Product> list_products2 = new ArrayList<>();
		list_products.add(p3);
		list_products.add(p2);
		list_products.add(p);
		list_products2.add(p4);
		list_products2.add(p5);
		list_products2.add(p6);
		
		User u = new User();
		u.setUser_email("alvaro@gmail.com");
		u.setUser_password("12345");
		u.setUser_product_list(list_products);
		userdao.save(u);
		User u2 = new User();
		u2.setUser_email("joaquin@gmail.com");
		u2.setUser_password("12345");
		userdao.save(u2);
		User u3 = new User();
		u3.setUser_email("alberto@gmail.com");
		u3.setUser_password("12345");
		u3.setUser_product_list(list_products2);
		userdao.save(u3);
		
		Administrator a = new Administrator();
		a.setAdmin_email("luis@gmail.com");
		admindao.save(a);
		Administrator a2 = new Administrator();
		a2.setAdmin_email("patricia@gmail.com");
		admindao.save(a2);
		*/
	}
	
}
