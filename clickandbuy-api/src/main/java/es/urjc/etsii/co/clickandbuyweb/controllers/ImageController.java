package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.UserImageService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private UserImageService uis;
	@Autowired
	private UserService us;
	
	@GetMapping("")
	public ModelAndView getImage(Model model) {
		return new ModelAndView("/userImages/userImagesMain");
	}
	
	@GetMapping("/user/get")
	public ModelAndView getImage(Model model, @RequestParam(required=true) String email) {
		User u = us.userEmailSearch(email);
		if(u==null) {
			model.addAttribute("email", email);
			model.addAttribute("usid", 0);
			model.addAttribute("no_user", true);
			return new ModelAndView("/userImages/userImageData");
		}
		boolean img = uis.hasPhoto(u.getId());
		if(!img) {
			model.addAttribute("email", email);
			model.addAttribute("usid", u.getId());
			model.addAttribute("error", true);
			model.addAttribute("no_user", false);
			return new ModelAndView("/userImages/userImageData");
		}
		model.addAttribute("email", email);
		model.addAttribute("usid", u.getId());
		model.addAttribute("no_user", false);
		return new ModelAndView("/userImages/userImageData");
	}
	
	@PostMapping("/user/upload")
	public ModelAndView uploadImage(Model model, @RequestParam(required=true) String email, @RequestParam MultipartFile img) throws Exception {
		User u = us.userEmailSearch(email);
		int status = -1;
		try {
			status = uis.upload(img, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status<=0) {
			model.addAttribute("error", true);
			model.addAttribute("usid", u.getId());
			model.addAttribute("email", email);
			model.addAttribute("no_user", false);
			return new ModelAndView("/userImages/userImageData");
		}
		model.addAttribute("error", false);
		model.addAttribute("usid", u.getId());
		model.addAttribute("email", email);
		model.addAttribute("no_user", false);
		return new ModelAndView("/userImages/userImageData");
	}
}
