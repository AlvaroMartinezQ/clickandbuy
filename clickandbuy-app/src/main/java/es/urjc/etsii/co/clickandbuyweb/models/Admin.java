package es.urjc.etsii.co.clickandbuyweb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core_admin")
public class Admin {
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
	private Date last_login;
	// Charge should be: [SUPERUSER, ADMIN, STAFF]
	@Column(nullable=false, length=255)
	private String admin_charge;
	private boolean active;
	private boolean superuser;
	
	public Admin() {
		super();
	}

	public Admin(int id, String email, String password, String name, String realname,
			String phone, Date last_login, String admin_charge, boolean is_active, boolean is_superuser) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.realname = realname;
		this.phone = phone;
		this.last_login = last_login;
		this.admin_charge = admin_charge;
		this.active = is_active;
		this.superuser = is_superuser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getrealname() {
		return realname;
	}

	public void setrealname(String realname) {
		this.realname = realname;
	}

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getAdmin_charge() {
		return admin_charge;
	}

	public void setAdmin_charge(String admin_charge) {
		this.admin_charge = admin_charge;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean is_active) {
		this.active = is_active;
	}

	public boolean isSuperuser() {
		return superuser;
	}

	public void setSuperuser(boolean is_superuser) {
		this.superuser = is_superuser;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", email=" + email + ", name=" + name
				+ ", realname=" + realname + ", phone=" + phone + ", last_login=" + last_login
				+ ", admin_charge=" + admin_charge + ", is_active=" + active + ", is_superuser=" + superuser
				+ "]";
	}
}
