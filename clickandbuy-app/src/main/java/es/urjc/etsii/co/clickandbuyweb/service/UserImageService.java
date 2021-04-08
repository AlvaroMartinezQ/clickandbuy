package es.urjc.etsii.co.clickandbuyweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserImageDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.models.UserImage;

@Service
public class UserImageService {

	@Autowired
	private UserImageDAO userimagedao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private AdminDAO admindao;
	
	public int upload(MultipartFile image, int usid) throws Exception {
		User u = userdao.findByUserId(usid);
		if(u==null) {
			// Error code for a non existing user
			return -1;
		}
		if(usid<=0) {
			// error code for a non valid user id
			return 0;
		}
		UserImage existingImage = userimagedao.findByUserId(usid);
		if(existingImage==null) {
			UserImage uploadImage = new UserImage();
			uploadImage.setContent(image.getBytes());
			uploadImage.setUsid(u.getId());
			return userimagedao.save(uploadImage).getId();
		} else {
			existingImage.setContent(image.getBytes());
			return userimagedao.save(existingImage).getId();
		}
	}
	
	
	public int upload(MultipartFile image, String email) throws Exception {
		User u = userdao.findByUserEmail(email);
		if(u==null) {
			// Error code for a non existing user
			return -1;
		}
		int usid = u.getId();
		UserImage existingImage = userimagedao.findByUserId(usid);
		if(existingImage==null) {
			UserImage uploadImage = new UserImage();
			uploadImage.setContent(image.getBytes());
			uploadImage.setUsid(usid);
			return userimagedao.save(uploadImage).getId();
		} else {
			existingImage.setContent(image.getBytes());
			return userimagedao.save(existingImage).getId();
		}
	}
	
	public int uploadAdmin(MultipartFile image, String email) throws Exception {
		Admin admin = admindao.findByEmail(email);
		if(admin==null) {
			// Error code for a non existing user
			return -1;
		}
		int asid = admin.getId();
		UserImage existingImage = userimagedao.findByAdminId(asid);
		if(existingImage==null) {
			UserImage uploadImage = new UserImage();
			uploadImage.setContent(image.getBytes());
			uploadImage.setAsid(asid);
			return userimagedao.save(uploadImage).getId();
		} else {
			existingImage.setContent(image.getBytes());
			return userimagedao.save(existingImage).getId();
		}
	}
	
	public Resource getImage(int usid) {
		// Server status response shouldn't be shown by the browser - sensitive info
		byte[] image = userimagedao.findById(usid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getContent();
		return new ByteArrayResource(image);
	}
	
	public Resource getImageAdmin(int asid) {
		// Server status response shouldn't be shown by the browser - sensitive info
		byte[] image = userimagedao.findByAdminIdOp(asid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getContent();
		return new ByteArrayResource(image);
	}
	
	public boolean hasPhoto(int usid) {
		byte[] image = null;
		try {
			image = userimagedao.findById(usid).orElseThrow(() -> new Exception("User has no photo")).getContent();
		} catch (Exception e) {
			// print stack trace for DEBUG ??
		}
		if(image!=null) {
			return true;
		}
		return false;
	}
	
	public boolean hasPhotoAdmin(int asid) {
		byte[] image = null;
		try {
			image = userimagedao.findByAdminIdOp(asid).orElseThrow(() -> new Exception("User has no photo")).getContent();
		} catch (Exception e) {
			// print stack trace for DEBUG ??
		}
		if(image!=null) {
			return true;
		}
		return false;
	}
	
	public Resource getImage(String email) {
		// Server status response shouldn't be shown by the browser - sensitive info
		User u = userdao.findByUserEmail(email);
		if(u==null) {
			return null;
		}
		int usid = u.getId();
		byte[] image = userimagedao.findById_bytes(usid);
		if(image==null) {
			return null;
		}
		return new ByteArrayResource(image);
		//return new ByteArrayResource(image);
	}
	
	public Resource getImageAdmin(String email) {
		// Server status response shouldn't be shown by the browser - sensitive info
		Admin admin = admindao.findByEmail(email);
		if(admin==null) {
			return null;
		}
		int asid = admin.getId();
		byte[] image = userimagedao.findByAdminId_bytes(asid);
		if(image==null) {
			return null;
		}
		return new ByteArrayResource(image);
		//return new ByteArrayResource(image);
	}
	
}
