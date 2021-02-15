package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_table")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double price;
	private String description;
	private boolean has_stock;
	private boolean is_active;
	
	// Add photo for admin
	
	public Product() {
		super();
	}

	public Product(int id, double price, String description, boolean has_stock, boolean is_active) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.has_stock = has_stock;
		this.is_active = is_active;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHas_stock() {
		return has_stock;
	}

	public void setHas_stock(boolean has_stock) {
		this.has_stock = has_stock;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", description=" + description + ", has_stock=" + has_stock
				+ ", is_active=" + is_active + "]";
	}
}
