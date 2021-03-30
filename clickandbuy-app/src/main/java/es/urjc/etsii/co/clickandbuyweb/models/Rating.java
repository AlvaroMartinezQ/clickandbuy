package es.urjc.etsii.co.clickandbuyweb.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="marketplace_rating")
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=true, length=255)
	private String comment;
	@Column(nullable=false, length=1)
	private int rate;
	
	@ManyToOne()
	private User user;


	public Rating() {
		super();
	}
	
	public Rating(String comment, int rate, User user) {
		super();
		this.comment = comment;
		this.rate = rate;
		this.user = user;

	}

	public Rating(int id, String comment, int rate, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.rate = rate;
		this.user = user;
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



	@Override
	public String toString() {
		return "Rating [id=" + id + ", comment=" + comment + ", rate=" + rate + ", user=" + user +"]";
	}
}
