package es.urjc.etsii.co.clickandbuyweb.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="marketplace_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate date;
	private LocalDate estimated;
	private String state;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id")
	private Set<Cart> carts;
	
	public Order() {
		super();
	}
	
	public Order(int id, LocalDate date, LocalDate estimated, String state, Set<Cart> carts) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", estimated=" + estimated + ", state=" + state + ", carts="
				+ carts + "]";
	}
	
	
	
	
}
