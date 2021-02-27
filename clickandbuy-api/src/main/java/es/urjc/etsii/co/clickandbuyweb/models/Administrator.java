package es.urjc.etsii.co.clickandbuyweb.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrator_table")
public class Administrator implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String admin_email;
	private String admin_password;
	private String admin_name;
	private String admin_realname;
	private int admin_phone;
	private Date last_login;
	private String admin_charge;
	private boolean is_active;
	private boolean is_superuser;
	// Add photo for admin
	
	
	
	public Administrator() {
		super();
	}

	public Administrator(int id, String admin_email, String admin_password, String admin_name, String admin_realname,
			int admin_phone, Date last_login, String admin_charge, boolean is_active, boolean is_superuser) {
		super();
		this.id = id;
		this.admin_email = admin_email;
		this.admin_password = admin_password;
		this.admin_name = admin_name;
		this.admin_realname = admin_realname;
		this.admin_phone = admin_phone;
		this.last_login = last_login;
		this.admin_charge = admin_charge;
		this.is_active = is_active;
		this.is_superuser = is_superuser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_realname() {
		return admin_realname;
	}

	public void setAdmin_realname(String admin_realname) {
		this.admin_realname = admin_realname;
	}

	public int getAdmin_phone() {
		return admin_phone;
	}

	public void setAdmin_phone(int admin_phone) {
		this.admin_phone = admin_phone;
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

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_superuser() {
		return is_superuser;
	}

	public void setIs_superuser(boolean is_superuser) {
		this.is_superuser = is_superuser;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", admin_email=" + admin_email + ", admin_name=" + admin_name
				+ ", admin_realname=" + admin_realname + ", admin_phone=" + admin_phone + ", last_login=" + last_login
				+ ", admin_charge=" + admin_charge + ", is_active=" + is_active + ", is_superuser=" + is_superuser
				+ "]";
	}
	
	
}
