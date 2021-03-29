package es.urjc.etsii.co.clickandbuyweb.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.CartDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.OrderDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Cart;
import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderDAO orderdao;
	
	@Autowired
	private UserDAO userdao;
	
	@Autowired
	private CartDAO cartdao;
	
	@Autowired
	private ProductDAO productdao;
	
	String[] orderstatus = { "POR APROBAR", "EN PREPARACION", "EN REPARTO", "ENTREGADO", "CANCELADO" };
	
	public List<Order> getOrdes(){
		return orderdao.findAll();
	}

	public Order getOrderById(int id) {
		Optional<Order> order = orderdao.findById(id);
		if (!order.isPresent()) {
			return null;
		}
		return order.get();
	}
	
	public String makeOrder(int usid, int prodid, int cuantity, Cart cart) {
		Optional<User> user = userdao.findById(usid);
		Optional<Product> product = productdao.findById(prodid);
		
		return "";
	}
	
	public void addCart(int id, Cart cart) {
		Optional<Order> order = orderdao.findById(id);
		if(order.isPresent()) {
			order.get().getCarts().add(cart);
		}
	}
}
