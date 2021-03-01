package es.urjc.etsii.co.clickandbuyweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserImageDAO;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.models.UserImage;

@Service
public class UserImageService {

	@Autowired
	private UserImageDAO userimagedao;
	@Autowired
	private UserDAO userdao;
	
	public int upload(MultipartFile image, int usid) throws Exception {
		User u = userdao.findByUser_id(usid);
		if(u==null) {
			// Error code for a non existing user
			return -1;
		}
		if(usid<=0) {
			// error code for a non valid user id
			return 0;
		}
		UserImage existingImage = userimagedao.findByUser_id(usid);
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
	
	public Resource getImage(int usid) {
		// Server status response shouldn't be shown by the browser - sensitive info
		byte[] image = userimagedao.findbyid(usid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getContent();
		return new ByteArrayResource(image);
	}
	
}
