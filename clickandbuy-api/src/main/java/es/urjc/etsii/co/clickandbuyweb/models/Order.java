package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="order_table")
public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private double price;
	private Date date;
	private Date estimated_date;
	private String state;
	
	@OneToMany()
	private List<Product> product_list;
	
	private int[] cuantity_per_product;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private User owner;

	public Order() {
		super();
	}

	public Order(int id, double price, Date date, Date estimated_date, String state, List<Product> product_list,
			int[] cuantity_per_product, User owner) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.estimated_date = estimated_date;
		this.state = state;
		this.product_list = product_list;
		this.cuantity_per_product = cuantity_per_product;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int[] getCuantity_per_product() {
		return cuantity_per_product;
	}



	public void setCuantity_per_product(int[] cuantity_per_product) {
		this.cuantity_per_product = cuantity_per_product;
	}



	public void setProduct_list(List<Product> product_list) {
		this.product_list = product_list;
	}



	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", price=" + price + ", date=" + date + ", estimated_date=" + estimated_date
				+ ", state=" + state + ", product_list=" + product_list + ", cuantity_per_product="
				+ cuantity_per_product + ", owner=" + owner + "]";
	}
	
}
