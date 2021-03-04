package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;

@RestController
@RequestMapping("/api/image")
public class ApiUserImageController {
	@Autowired
	private UserImageService userimageservice;
	
	@PostMapping(value="/upload")
	public int imageUpload(@RequestParam MultipartFile image, @RequestParam int usid) throws Exception {
		return userimageservice.upload(image, usid);
	}
	
	@GetMapping(value="/retreive", produces = MediaType.IMAGE_JPEG_VALUE)
	public Resource imageGet(@RequestParam int usid) throws Exception {
		return userimageservice.getImage(usid);
	}
}
