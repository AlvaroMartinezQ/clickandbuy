package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="core_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=true, length=255)
	private String name;
	@Column(nullable=true, length=255)
	private String realname;
	@Column(nullable=true, length=255)
	private String realsurnames;
	@Column(nullable=true, length=255)
	private String address;
	@Column(nullable=true, length=12)
	private String phone;
	@Column(nullable=true, length=17)
	private String bankaccount;
	private Date join_date;
	private Date last_login;
	private boolean is_active;
	private boolean is_supplier;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Order> myOrders;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Order orderactive;
	
	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> roles;

	@JsonIgnore
	@OneToMany(targetEntity=Product.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@Column(nullable=true)
	List<Product> user_product_list = new ArrayList<>();
	
	public User() {
		super();
		this.orderactive = new Order();
	}
	
	public User(String email, String password, String name, String realname, String realsurnames,
			String address, String phone, String user_bankaccount, Date join_date, boolean is_active,
			boolean is_supplier, String... roles) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.realname = realname;
		this.realsurnames = realsurnames;
		this.address = address;
		this.phone = phone;
		this.bankaccount = user_bankaccount;
		this.join_date = join_date;
		this.is_active = is_active;
		this.is_supplier = is_supplier;
		this.myOrders = new HashSet<>();
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.orderactive = new Order();
	}

	public User(int id, String email, String password, String name, String realname, String realsurnames,
			String address, String phone, String user_bankaccount, Date join_date, Date last_login, boolean is_active,
			boolean is_supplier, List<Product> user_product_list, String... roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.realname = realname;
		this.realsurnames = realsurnames;
		this.address = address;
		this.phone = phone;
		this.bankaccount = user_bankaccount;
		this.join_date = join_date;
		this.last_login = last_login;
		this.is_active = is_active;
		this.is_supplier = is_supplier;
		this.user_product_list = user_product_list;
		this.myOrders = new HashSet<>();
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.orderactive = new Order();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealsurnames() {
		return realsurnames;
	}

	public void setRealsurnames(String realsurnames) {
		this.realsurnames = realsurnames;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String user_bankaccount) {
		this.bankaccount = user_bankaccount;
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
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<Product> getUser_product_list() {
		return user_product_list;
	}

	public void setUser_product_list(List<Product> user_product_list) {
		this.user_product_list = user_product_list;
	}
	

	public Set<Order> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(Set<Order> myOrders) {
		this.myOrders = myOrders;
	}
	

	public Order getOrderactive() {
		return orderactive;
	}

	public void setOrderactive(Order orderactive) {
		this.orderactive = orderactive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", realname=" + realname + ", realsurnames="
				+ realsurnames + ", address=" + address + ", phone=" + phone + ", join_date=" + join_date
				+ ", last_login=" + last_login + ", is_active=" + is_active + ", is_supplier=" + is_supplier
				+ ", user_product_list=" + user_product_list + "]";
	}

}
