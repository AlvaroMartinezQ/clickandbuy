package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_table")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double product_price;
	private String product_name;
	private String product_description;
	private int product_stock;
	private boolean has_stock;
	private boolean is_active;
	
	// Add photo for product
	
	public Product() {
		super();
	}

	public Product(int id, double product_price, String product_name, String product_description, int product_stock,
			boolean has_stock, boolean is_active) {
		super();
		this.id = id;
		this.product_price = product_price;
		this.product_name = product_name;
		this.product_description = product_description;
		this.product_stock = product_stock;
		this.has_stock = has_stock;
		this.is_active = is_active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
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
		return "Product [id=" + id + ", product_price=" + product_price + ", product_name=" + product_name
				+ ", product_description=" + product_description + ", product_stock=" + product_stock + ", has_stock="
				+ has_stock + ", is_active=" + is_active + "]";
	}
}
