package es.urjc.etsii.co.clickandbuyweb.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="marketplace_product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Product price is required.")
	@Basic(optional=false)
	private Double price;
	@NotNull(message="Product name is required.")
    @Basic(optional=false)
	private String name;
	@NotNull(message="Product description is required.")
	@Basic(optional=false)
	private String description;
	@NotNull(message="Product stock is required.")
	@Basic(optional=false)
	private int stock;
	private boolean active;
	
	public Product() {
		super();
	}

	public Product(int id, double price, String name, String description, int stock, boolean active) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.active = active;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getstock() {
		return stock;
	}

	public void setstock(int stock) {
		this.stock = stock;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", name=" + name + ", description=" + description
				+ ", stock=" + stock + ", active=" + active + "]";
	}
}
