package es.urjc.etsii.co.clickandbuyweb.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marketplace_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	private int cuantity;
	private double price;

	public Cart() {
		super();
	}

	public Cart(int id, Product product, int cuantity, double price) {
		super();
		this.id = id;
		this.product = product;
		this.cuantity = cuantity;
		this.price = price * cuantity;
	}

	public Cart(Product product, int cuantity, double price) {
		super();
		this.product = product;
		this.cuantity = cuantity;
		this.price = price * cuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCantidad() {
		return cuantity;
	}

	public void setCantidad(int cantidad) {
		this.cuantity = cantidad;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price * this.getCantidad();
	}

}
