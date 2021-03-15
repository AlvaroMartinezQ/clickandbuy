package es.urjc.etsii.co.clickandbuyweb.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.urjc.etsii.co.clickandbuyweb.dao.OrderDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.OrderProductDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.models.OrderProduct;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Service
@Transactional
public class OrderService {
	@Autowired 
	private OrderDAO odao;
	@Autowired
	private UserDAO udao;
	@Autowired
	private OrderProductDAO opdao;
	@Autowired
	private ProductService pservice;
	
	public Iterable<Order> getAll() {
        return odao.findAll();
    }
	
	public Order create(String idus, String idprod, String quantity, String order) {
		int productid=Integer.parseInt(idprod);
		Product p = pservice.getProduct(productid);
		if(p==null) {
			return null;
		}
		int usid=Integer.parseInt(idus);
		User u=udao.findByUserId(usid);
		if(u==null) {
			return null;
		}
		int orderid=Integer.parseInt(order);
		if(orderid==0) {
			// new
			Order neworder=new Order();
			neworder.setDateCreated(LocalDate.now());
			neworder.setStatus("PENDING");
			int product_quantity=Integer.parseInt(quantity);
			OrderProduct op=new OrderProduct(neworder, p, product_quantity);
			// On this point we have all 3 entities
			// Next list should be empty as this is a new Order
			List<OrderProduct> prod_list=neworder.getOrderProducts();
			prod_list.add(op);
			neworder.setOrderProducts(prod_list);
			neworder.setOwner(u);
			odao.save(neworder);
			for(OrderProduct orderproduct:prod_list) {
				// Should only be 1 iteration
				opdao.save(orderproduct);
			}
			return neworder;
			// return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			// Order id is type long
			Long order_id=Long.parseLong(order);
			Order existingOrder=odao.findByOrderId(order_id);
			if(existingOrder==null) {
				return null;
			}
			int product_quantity=Integer.parseInt(quantity);
			List<OrderProduct> orderproductlist=existingOrder.getOrderProducts();
			OrderProduct orderproduct=new OrderProduct(existingOrder, p, product_quantity);
			orderproductlist.add(orderproduct);
			existingOrder.setOrderProducts(orderproductlist);
			// Save the OrderProduct entity FIRST!!!
			opdao.save(orderproduct);
			odao.save(existingOrder);
			return existingOrder;
			// return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
}
