package es.urjc.etsii.co.clickandbuyweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="core_pics_user")
public class UserImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Lob
	private byte[] content;
	@Column(nullable=false)
	private int usid;
	@Column(nullable=false)
	private int asid;
	
	public UserImage(int id, byte[] content, int usid, int asid) {
		super();
		this.id = id;
		this.content = content;
		this.usid = usid;
		this.asid = asid;
	}

	public UserImage() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public int getAsid() {
		return asid;
	}

	public void setAsid(int asid) {
		this.asid = asid;
	}
	
}

