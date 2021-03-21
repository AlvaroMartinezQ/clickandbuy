package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;

@RestController
@RequestMapping("/userPhoto")
public class UserPhotoController {
	
	@Autowired
	private UserImageService uis;
	
	@GetMapping(value="/retreive", produces = MediaType.IMAGE_JPEG_VALUE)
	public Resource imageGet(@RequestParam int usid) throws Exception {
		return uis.getImage(usid);
	}
	
}
