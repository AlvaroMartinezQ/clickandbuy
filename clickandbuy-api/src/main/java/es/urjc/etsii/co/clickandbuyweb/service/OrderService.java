package es.urjc.etsii.co.clickandbuyweb.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.urjc.etsii.co.clickandbuyweb.dao.OrderDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.ProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private ProductDAO productdao;

	String[] orderstatus = { "POR APROBAR", "EN PREPARACION", "EN REPARTO", "ENTREGADO", "CANCELADO" };

	public List<Order> getOrders() {
		return orderdao.findAll();
	}

	public List<Order> getOrder(int order_id) {
		return orderdao.findbyorder_id(order_id);
	}

	public String makeOrder(int usid, int prodid, int orderid) {
		// Search if the given user and product exist
		User u = userdao.findById(usid).orElseThrow();
		Product p = productdao.findByProductId(prodid);
		if (p == null)
			return "status: product not available";
		if (p.isHas_stock()) {
			if (orderid == 0) {
				// New order
				checkStock(p);
				Order neworder = new Order();
				Date d = new Date();
				neworder.setDate(d);
				List<Order> orderlist = orderdao.findAll();
				int id = 0;
				for (Order o : orderlist) {
					if (o.getOrder_id() > id) {
						id = o.getOrder_id();
					}
				}
				id++;
				neworder.setOrder_id(id);
				neworder.setState("POR APROBAR");
				neworder.setOwner(u);
				neworder.setProduct(p);
				orderdao.save(neworder);
				return "status: new order created successfully";
			} else {
				List<Order> list = orderdao.findbyorder_id(orderid);
				if (list.isEmpty() || list.get(0).getOwner().getId() != usid)
					return "status: non-existent order";
				Order existingorder = new Order();
				checkStock(p);
				existingorder.setOrder_id(orderid);
				existingorder.setOwner(u);
				existingorder.setProduct(p);
				orderdao.save(existingorder);
				return "status: successfully modified order";
			}

		}
		return "status: product out of stock";
	}

	public String changeStatus(int order_id, int status_id) {
		List<Order> newlist = orderdao.findbyorder_id(order_id);
		if (newlist.isEmpty())
			return "status: order not found";
		;
		Order o = newlist.get(0);
		o.setState(orderstatus[status_id]);
		if (status_id == 1) {
			Date d = o.getDate();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DATE, (int) (Math.random() * 7 + 2));
			d = c.getTime();
			o.setEstimated_date(d);
		}
		orderdao.save(o);
		return "status: order status update";
	}

	public String deleteOrder(int order_id) {
		List<Order> list = orderdao.findbyorder_id(order_id);
		if (list.isEmpty())
			return "status: order not found";
		for (Order o : list) {
			orderdao.delete(o);
		}
		return "status: the order was removed";
	}

	public String orderStatus(int order_id) {
		List<Order> list = orderdao.findbyorder_id(order_id);
		if (list.isEmpty())
			return "status: order not found";
		return list.get(0).getState();
	}

	public String priceOrder(int order_id) {
		List<Order> list = orderdao.findbyorder_id(order_id);
		if (list.isEmpty())
			return "status: order not found";
		double finalprice = 0;
		for (Order o : list) {
			finalprice += o.getProduct().getProduct_price();
		}
		return Double.toString(finalprice);
	}

	public List<Product> productOrder(int order_id) {
		List<Order> list = orderdao.findbyorder_id(order_id);
		if (list.isEmpty()) {
			return null;
		}
		List<Product> listproduct = new ArrayList<Product>();
		for (Order p : list) {
			listproduct.add(p.getProduct());
		}

		return listproduct;
	}

	private void checkStock(Product p) {
		p.setProduct_stock(p.getProduct_stock() - 1);
		if (p.getProduct_stock() <= 0)
			p.setHas_stock(false);
		productdao.save(p);
	}
}
