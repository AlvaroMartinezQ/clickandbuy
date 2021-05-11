package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@SessionScope
@Table(name="core_admin")
public class Admin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false, length=255)
	private String name;
	@Column(nullable=false, length=255)
	private String realname;
	@Column(nullable=false, length=12)
	private String phone;
    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate last_login;
	// Charge should be: [MANAGER, ADMIN]
	private String charge;
	
	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> roles;
	
	public Admin() {
		super();
	}
	
	public Admin(String email, String password, String name, String realname, String phone,
			LocalDate last_login, String charge, String...roles) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.realname = realname;
		this.phone = phone;
		this.last_login = last_login;
		this.charge = charge;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public Admin(int id, String email, String password, String name, String realname, String phone,
			LocalDate last_login, String charge, String...roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.realname = realname;
		this.phone = phone;
		this.last_login = last_login;
		this.charge = charge;
		this.roles = new ArrayList<>(Arrays.asList(roles));
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalDate last_login) {
		this.last_login = last_login;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", realname="
				+ realname + ", phone=" + phone + ", last_login=" + last_login + ", charge=" + charge + ", roles="
				+ roles + "]";
	}
	
}

	