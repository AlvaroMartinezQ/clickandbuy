package es.urjc.etsii.co.clickandbuyweb.service;

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
	
	public List<Order> getOrders() {
		return orderdao.findAll();
	}
	
	public List<Order> getOrder(int order_id){
		return orderdao.findbyorder_id(order_id);
	}
	
	public Order makeOrder(int usid, int prodid, int orderid) {
		// Search if the given user and product exist
		User u = userdao.findById(usid).orElseThrow();
		Product p = productdao.findById(prodid).orElseThrow();
		if(orderid==0) {
			// New order
			Order neworder = new Order();
			Date d =  new Date();
			neworder.setDate(d);
			List<Order> orderlist = orderdao.findAll();
			int id = 0;
			for(Order o: orderlist) {
				if(o.getOrder_id()>id) {
					id = o.getOrder_id();
				}
			}
			id++;
			neworder.setOrder_id(id);
			neworder.setState("POR APROBAR");
			neworder.setOwner(u);
			neworder.setProduct(p);
			orderdao.save(neworder);
			return neworder;
		} else {
			// Existing order
			Order existingorder = new Order();
			Date d =  new Date();
			existingorder.setDate(d);
			existingorder.setOrder_id(orderid);
			existingorder.setOwner(u);
			existingorder.setProduct(p);
			orderdao.save(existingorder);
			return existingorder;
		}
	}

}
