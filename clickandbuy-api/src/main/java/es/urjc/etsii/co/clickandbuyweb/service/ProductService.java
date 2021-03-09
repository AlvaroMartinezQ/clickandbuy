package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productdao;
	@Autowired
	private UserService us;
	@Autowired
	private UserDAO udao;

	public List<Product> getProducts() {

		List<Product> list = productdao.findAllProducts();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public Product productNameSearch(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if (p == null) {
			return null;
		}
		return p;
	}

	public List<Product> productNameSearchL(String product_name) {
		List<Product> list = productdao.findByProduct_nameL(product_name);
		if (list == null) {
			return null;
		}
		return list;
	}

	public int productStock(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if (p == null) {
			return 0;
		}
		return p.getProduct_stock();
	}

	public String productDescription(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if (p == null) {
			return null;
		}
		return p.getProduct_description();
	}

	public List<Product> productHasStock() {
		List<Product> list = productdao.findAllStock();
		if (list.isEmpty())
			return null;
		return list;
	}

	public boolean productHasStockName(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if (p == null)
			return false;
		return p.isHas_stock();
	}

	public List<Product> productIsActive() {
		List<Product> list = productdao.findAllActive();
		if (list.isEmpty())
			return null;
		return list;
	}

	public boolean productIsActiveName(String product_name) {
		Product p = productdao.findByProduct_name(product_name);
		if (p == null)
			return false;
		return p.isIs_active();
	}

	public List<Product> productsPriceLe(double price) {
		List<Product> list = productdao.findPriceLe(price);
		if (list.isEmpty())
			return null;
		return list;
	}

	public List<Product> productsPriceGe(double price) {
		List<Product> list = productdao.findPriceGe(price);
		if (list.isEmpty())
			return null;
		return list;
	}

	public List<Product> productsPriceBe(double price1, double price2) {
		List<Product> list = productdao.findPriceBe(price1, price2);
		if (list.isEmpty())
			return null;
		return list;
	}

	public String unSetActive(String name) {
		Product p = productdao.findByProduct_name(name);
		if (p == null) {
			return "status: not found";
		}
		p.setIs_active(false);
		productdao.save(p);
		return "status: product is not active";
	}

	public String setActive(String name) {
		Product p = productdao.findByProduct_name(name);
		if (p == null) {
			return "status: not found";
		}
		p.setIs_active(true);
		productdao.save(p);
		return "status: product is not active";
	}

	public String setStock(String name) {
		Product p = productdao.findByProduct_name(name);
		if (p == null) {
			return "status: not found";
		} else if (p.getProduct_stock() <= 0) {
			return "status: the product has " + p.getProduct_stock() + " units";
		}
		p.setHas_stock(true);
		productdao.save(p);
		return "status: the product has stock";
	}

	public String unSetStock(String name) {
		Product p = productdao.findByProduct_name(name);
		if (p == null) {
			return "status: not found";
		} else if (p.getProduct_stock() >= 1) {
			return "status: the product has " + p.getProduct_stock() + " units";
		}
		p.setHas_stock(false);
		productdao.save(p);
		return "status: out-of-stock product";
	}
	
	
	public int productUpload(String email, String product_name, String product_desc, String product_price, String product_stock) {
		if(email.equals("")||product_name.equals("")) {
			// bad fields
			return -1;
		}
		User u = us.userEmailSearch(email);
		if(u==null) {
			// bad user
			return -2;
		}
		if(!u.isIs_supplier()) {
			// Not supplier
			return -4;
		}
		Product p = new Product();
		p.setProduct_name(product_name);
		p.setProduct_description(product_desc);
		int price=0, stock=0;
		try {
			price=Integer.parseInt(product_price);
			stock=Integer.parseInt(product_stock);
		} catch (Exception e) {
			System.out.println("Non valid price or stock fields");
			return -3;
		}
		if(price<0) {
			price=0;
		}
		p.setProduct_price(price);
		if(stock<0) {
			stock=0;
			p.setHas_stock(false);
		} else {
			p.setHas_stock(true);
			p.setProduct_stock(stock);
			p.setIs_active(true);
		}
		List<Product> list = u.getUser_product_list();
		list.add(p);
		u.setUser_product_list(list);
		udao.save(u);
		return 0;
	}
}
