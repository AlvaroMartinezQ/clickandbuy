package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "marketplace_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDate date;
	private LocalDate estimated;
	private String state;
	private double price;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Set<Cart> carts = new HashSet<>();

	public Order() {
		super();
		this.state = "ORDER ACTIVE";
	}

	public Order(LocalDate date, LocalDate estimated, String state, Set<Cart> carts) {
		super();
		this.date = date;
		this.estimated = estimated;
		this.state = state;
		this.carts = carts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getEstimated() {
		return estimated;
	}

	public void setEstimated(LocalDate estimated) {
		this.estimated = estimated;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	
	public double getPriceTotal() {
		return price;
	}

	public void setPriceTotal(double price) {
		this.price = price;
	}

	public void priceTotal() {
		this.price = 0;
		for (Cart c : this.getCarts()) {
			this.price += c.getPrice();
		}
	}

	@Override
	public String toString() {
		String cad = "";
		for(Cart cart : this.getCarts()) {
			cad += cart.getProduct().getName() + " Cantidad: " + cart.getCantidad() + " Precio: " + cart.getPrice() + "\n";
		}
		return "Order id= " + id + "\nDate= " + date + "\nEstimated= " + estimated + "\nState= " + state + "\ncarts=\n"
				+ cad + "\nPrecio final: "+ this.getPriceTotal() + "\n";
	}

}
