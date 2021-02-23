package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int order_id;
	private Date date;
	private Date estimated_date;
	private String state;
	
	@OneToOne()
	@JoinColumn(name="prod_id")
	private Product product;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User owner;

	public Order() {
		super();
	}

	public Order(int id, int order_id, Date date, Date estimated_date, String state, Product product,
			User owner) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.date = date;
		this.estimated_date = estimated_date;
		this.state = state;
		this.product = product;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getEstimated_date() {
		return estimated_date;
	}

	public void setEstimated_date(Date estimated_date) {
		this.estimated_date = estimated_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", order_id=" + order_id + ", price=" + date
				+ ", estimated_date=" + estimated_date + ", state=" + state + ", product=" + product + ", owner="
				+ owner + "]";
	}
	
}
