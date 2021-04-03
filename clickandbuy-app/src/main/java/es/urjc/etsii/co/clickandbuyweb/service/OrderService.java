package es.urjc.etsii.co.clickandbuyweb.service;

import java.time.LocalDate;
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

	public List<Order> getOrders() {
		return orderdao.findAll();
	}

	public Order getOrderById(int id) {
		Optional<Order> order = orderdao.findById(id);
		if (!order.isPresent()) {
			return null;
		}
		return order.get();
	}

	public String buy(int id) {
		Optional<User> user = userdao.findById(id);
		Order order = new Order(LocalDate.now(), LocalDate.now().plusDays((long) ((long) 2 + Math.random() * 7)),
				"COMPLETADO", user.get().getOrderactive().getCarts());
		order.priceTotal();
		for (Cart c : order.getCarts()) {
			c.getProduct().getBuyers().add(id);
			productdao.save(c.getProduct());
		}
		user.get().getMyOrders().add(order);
		orderdao.delete(user.get().getOrderactive());
		user.get().setOrderactive(new Order());
		orderdao.save(order);
		userdao.save(user.get());
		return "order completed!";
	}

	public String addCart(int id, int idproduct, int cuantity) {
		Optional<User> user = userdao.findById(id);
		int aux = 0;
		if (user.isPresent()) {
			Optional<Product> product = productdao.findById(idproduct);
			for (Cart c : user.get().getOrderactive().getCarts()) {
				if (c.getProduct().getId() == idproduct) {
					aux = c.getCantidad();
					user.get().getOrderactive().getCarts().remove(c);
					cartdao.delete(c);
					break;
				}
			}
			Cart cart = new Cart(product.get(), aux + cuantity, product.get().getPrice());
			user.get().getOrderactive().getCarts().add(cart);
			cartdao.save(cart);
			orderdao.save(user.get().getOrderactive());
			userdao.save(user.get());
			return "added product";
		}
		return "product not added";
	}

	public String deleteCart(int id, int idcart) {
		Optional<User> user = userdao.findById(id);

		if (user.isPresent()) {
			Optional<Cart> cart = cartdao.findById(idcart);

			user.get().getOrderactive().getCarts().remove(cart.get());
			cartdao.delete(cart.get());
			return "cart product removed";
		}
		return "cart product not removed";
	}
}
