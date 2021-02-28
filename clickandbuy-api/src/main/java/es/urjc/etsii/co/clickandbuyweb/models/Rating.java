package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating_table")
public class Rating implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comment;

	@Column(nullable = false)
	private int rate;

	@ManyToOne()
	private User user;

	@OneToOne()
	private Product product;

	public Rating() {
		super();
	}

	public Rating(int id, String comment, int rate, User user, Product product) {
		super();
		this.id = id;
		this.comment = comment;
		this.rate = rate;
		this.user = user;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(Optional<User> user) {
		this.user = user.get();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Optional<Product> product) {
		this.product = product.get();
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", comment=" + comment + ", rate=" + rate + ", user=" + user + ", order=" + product
				+ "]";
	}

}
