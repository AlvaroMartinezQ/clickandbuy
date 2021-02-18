package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@OneToOne()
	private User user;

	@OneToOne()
	private Product order;

	public Rating() {
		super();
	}

	public Rating(int id, String comment, int rate, User user, Product order) {
		super();
		this.id = id;
		this.comment = comment;
		this.rate = rate;
		this.user = user;
		this.order = order;
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

	public void setUser(User user) {
		this.user = user;
	}

	public Product getOrder() {
		return order;
	}

	public void setOrder(Product order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", comment=" + comment + ", rate=" + rate + ", user=" + user + ", order=" + order
				+ "]";
	}

}
