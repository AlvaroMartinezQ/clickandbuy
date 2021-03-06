package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
public class RatingController {


	@Autowired
	private RatingService ratingservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/ratings")
	public ModelAndView ratingMain(Model model) {
		return new ModelAndView("/ratings/ratingsMain");
	}
	
	@GetMapping("/ratings/all")
	public ModelAndView ratingAll(Model model) {
		model.addAttribute("ratinglist",ratingservice.getRatings());
		return new ModelAndView("/ratings/ratingsList");
	}
	
	@GetMapping("/ratings/add")
	public ModelAndView ratingAdd(Model model) {
		model.addAttribute("ratinglist",ratingservice.getRatings());
		return new ModelAndView("/ratings/ratingsNew");
	}
	
	@PostMapping("/ratings/createrating")
	public ModelAndView ratingCreate(Model model, @RequestParam(required=true) String comment, @RequestParam(required=true) String rate, @RequestParam(required=true) String email, @RequestParam(required=true) String password, @RequestParam(required=true) String productName) {
		if(email.equals("")||password.equals("")||comment.equals("")||rate.equals("")||productName.equals("")) {
			model.addAttribute("fill_fields", true);
			return new ModelAndView("/ratings/ratingsNew");
		}
		User user = userservice.userEmailSearch(email);
		if(user==null) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/ratings/ratingsNew");
		}
		if(!user.getUser_password().equals(password)) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/ratings/ratingsNew");
		}
		Product product = productservice.productNameSearch(productName);
		if(product==null) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/ratings/ratingsNew");
		}
		ratingservice.newRating(comment,Integer.parseInt(rate) , user.getId(), product.getId());
		return new ModelAndView("/ratings/ratingsList");
	}
}
