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

	public String updateProduct(String id, String name, String description, String price, String stock, boolean active) {
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

	public String deleteProduct(String id) {
		int pid = Integer.parseInt(id);
		Product p = pdao.findByProductId(pid);
		if (p == null) {
			return "status: product doesn't exist";
		}
		pdao.delete(p);
		return "status: deleted";
	}

	public Product search(String name) {
		Product p = pdao.findByname(name);
		if (p == null)
			return null;
		return p;
	}
}
