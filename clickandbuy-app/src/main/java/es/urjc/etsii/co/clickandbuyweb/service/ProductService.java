package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductDAO pdao;
	@Autowired
	private UserDAO udao;
	@Autowired
	private UserService userservice;
	@Autowired
	private RatingService ratingservice;

	public Iterable<Product> getAll() {
		return pdao.findAll();
	}

	public Product getProduct(int id) {
		Product p = new Product();
		try {
			p = pdao.findById(id).orElseThrow(() -> new Exception("Product not found"));
		} catch (Exception e) {
			System.out.println("Exception: Product " + id + " not found.");
		}
		return p;
	}

	public Product saveProduct(String userEmail, Double price, String name, String description, int stock) {
		User u = udao.findByUserEmail(userEmail);
		if (u == null) {
			return null;
		}

		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setstock(stock);
		p.setActive(true);
		List<Product> list = u.getUser_product_list();
		list.add(p);
		u.setUser_product_list(list);
		udao.save(u);
		return p;
	}
	
	public String saveUpdateProduct(Product product) {
		pdao.save(product);
		return "status: saved";
	}

	public String updateProduct(String id, String name, String description, String price, String stock,
			boolean active) {
		int pid = Integer.parseInt(id);
		Product p = pdao.findByProductId(pid);
		if (p == null) {
			return "status: product doesn't exist";
		}
		if (!name.equals("")) {
			p.setName(name);
		}
		if (!description.equals("")) {
			p.setDescription(description);
		}
		if (!price.equals("")) {
			double pprice = Double.parseDouble(price);
			p.setPrice(pprice);
		}
		if (!stock.equals("")) {
			int pstock = Integer.parseInt(stock);
			p.setstock(p.getstock() + pstock);
		}
		p.setActive(active);
		pdao.save(p);
		return "status: saved";
	}

	public void deleteProduct(int uid, int id) {
		User user = udao.findByUserId(uid);
		Product product = pdao.findByProductId(id);
		List<Product> list = user.getUser_product_list();
		list.remove(product);
		user.setUser_product_list(list);
		udao.save(user);
	}
	
	public String deleteProductByAdmin(int id) {
		Product product = pdao.findByProductId(id);
		Iterable<User> users = userservice.getUsers();
		for(User user: users) {
			if(user.getUser_product_list().contains(product)) {
				System.out.println("el usuario se ha encontrado");
				List<Product> list = user.getUser_product_list();
				list.remove(product);
				System.out.println("se ha eliminado el producto de su lista");
				user.setUser_product_list(list);
				udao.save(user);
				
				ratingservice.deleteAllRatingsFromProduct(id);
				return "Product has been deleted correctly";
			}
		}
		return "There have been a problem during deleting product";
		
	}

	public Product search(String name) {
		Product p = pdao.findByname(name);
		if (p == null)
			return null;
		return p;
	}
}
