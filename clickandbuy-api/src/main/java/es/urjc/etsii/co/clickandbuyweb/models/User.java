package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String user_email;
	private String user_password;
	private String user_name;
	private String user_realname;
	private String user_address;
	private int user_phone;
	private int user_bankaccount;
	private Date join_date;
	private Date last_login;
	private boolean is_active;
	private boolean is_supplier;
	
	@OneToMany(targetEntity=Product.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@Column(nullable=true)
	List<Product> user_product_list = new ArrayList<>();
	
	// Add photo for user
	
	public User() {
		super();
	}

	public User(int id, String user_email, String user_password, String user_name, String user_realname,
			String user_address, int user_phone, int user_bankaccount, Date join_date, Date last_login,
			boolean is_active, boolean is_supplier, List<Product> user_product_list) {
		super();
		this.id = id;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_realname = user_realname;
		this.user_address = user_address;
		this.user_phone = user_phone;
		this.user_bankaccount = user_bankaccount;
		this.join_date = join_date;
		this.last_login = last_login;
		this.is_active = is_active;
		this.is_supplier = is_supplier;
		this.user_product_list = user_product_list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_realname() {
		return user_realname;
	}

	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public int getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(int user_phone) {
		this.user_phone = user_phone;
	}

	public int getUser_bankaccount() {
		return user_bankaccount;
	}

	public void setUser_bankaccount(int user_bankaccount) {
		this.user_bankaccount = user_bankaccount;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_supplier() {
		return is_supplier;
	}

	public void setIs_supplier(boolean is_supplier) {
		this.is_supplier = is_supplier;
	}

	public List<Product> getUser_product_list() {
		return user_product_list;
	}

	public void setUser_product_list(List<Product> user_product_list) {
		this.user_product_list = user_product_list;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_email=" + user_email + ", user_name=" + user_name + ", user_phone="
				+ user_phone + ", join_date=" + join_date + ", last_login=" + last_login + ", is_active=" + is_active
				+ ", is_supplier=" + is_supplier + ", user_product_list=" + user_product_list + "]";
	}
	
}
