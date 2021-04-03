package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.Product;
import es.urjc.etsii.co.clickandbuyweb.models.Rating;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.AdminService;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.RatingService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/marketplace")
public class MarketPlaceController {

	@Autowired
	private ProductService ps;

	@Autowired
	private UserService us;

	@Autowired
	private AdminDAO admindao;

	@Autowired
	private RatingService ratingservice;

	@Autowired
	private AdminService adminservice;

	@Autowired
	private OrderService orderservice;

	@GetMapping("")
	public ModelAndView marketplaceInit(Model model, HttpServletRequest request) {
		// Get the user
		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());

		// Check if is admin
		Admin admin = (Admin) admindao.findByEmail(principal.getName());

		if (admin != null) {
			model.addAttribute("products", ps.getAll());
			model.addAttribute("mail", admin.getEmail());
			return new ModelAndView("/admin/productList");

		}

		// Check if user is supplier
		if (u != null) {
			if (u.getRoles().contains("ROLE_SUPPLIER")) {
				us.updateLogin(u);
				model.addAttribute("products", ps.getAll());
				model.addAttribute("mail", u.getEmail());
				return new ModelAndView("/marketplace/productList");
			}

			// Save the last login for the user
			if (u.getRoles().contains("ROLE_NOTSUPPLIER")) {
				us.updateLogin(u);
				// Return products
				model.addAttribute("products", ps.getAll());
				model.addAttribute("mail", u.getEmail());
				return new ModelAndView("/marketplace/productList");
			}
		}
		return null;
	}

	@RequestMapping("/productsView")
	public ModelAndView view(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();

		Admin admin = adminservice.getAdmin(principal.getName());
		User user = us.getUser(principal.getName());
		if (admin != null) {
			model.addAttribute("mail", admin.getEmail());
			model.addAttribute("userid", admin.getId());
			model.addAttribute("user", admin);
			model.addAttribute("head", true);
		} else {
			model.addAttribute("orderactive", user.getOrderactive().getCarts());
			model.addAttribute("mail", user.getEmail());
			model.addAttribute("userid", user.getId());
			model.addAttribute("user", user);
			model.addAttribute("head", false);
		}
		Product product = ps.getProduct(id);
		List<Rating> listSorted = product.getRating();
		model.addAttribute("ratinglist", listSorted);

		model.addAttribute("product", ps.getProduct(id));
		return new ModelAndView("/marketplace/productsView");
	}

	@PostMapping("/rate")
	public ModelAndView rate(Model model, HttpServletRequest request, @RequestParam(required = true) int id,
			@RequestParam(required = true) String comment, @RequestParam("btnradio") String rate) {
		Principal principal = request.getUserPrincipal();

		Admin admin = adminservice.getAdmin(principal.getName());
		User user = us.getUser(principal.getName());
		boolean isAdmin = false;
		if (admin != null) {
			model.addAttribute("mail", admin.getEmail());
			model.addAttribute("userid", admin.getId());
			model.addAttribute("user", admin);
			model.addAttribute("head", true);
			isAdmin = true;
		} else {

			model.addAttribute("mail", user.getEmail());
			model.addAttribute("userid", user.getId());
			model.addAttribute("user", user);
			model.addAttribute("head", false);
		}

		// User has bought this product
		Product product = ps.getProduct(id);

		List<Rating> listSorted = product.getRating();
		model.addAttribute("ratinglist", listSorted);

		if (!isAdmin) {
			Rating rating = new Rating(comment, Integer.valueOf(rate), us.getUser(user.getEmail()));
			ratingservice.save(rating);
			product.getRating().add(rating);
			ps.saveUpdateProduct(product);
		}

		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("product", product);
		return new ModelAndView("/marketplace/productsView");
	}

	@GetMapping("/deleteRate")
	public ModelAndView deleteRate(Model model, HttpServletRequest request, @RequestParam(required = true) int id,
			@RequestParam(required = true) int idrating) {
		Principal principal = request.getUserPrincipal();
		Admin admin = adminservice.getAdmin(principal.getName());
		User user = us.getUser(principal.getName());
		String emailUser;
		if (admin != null) {
			model.addAttribute("mail", admin.getEmail());
			model.addAttribute("userid", admin.getId());
			model.addAttribute("user", admin);
			model.addAttribute("head", true);
			emailUser = admin.getEmail();
		} else {

			model.addAttribute("mail", user.getEmail());
			model.addAttribute("userid", user.getId());
			model.addAttribute("user", user);
			model.addAttribute("orderactive", user.getOrderactive().getCarts());
			model.addAttribute("head", false);
			emailUser = user.getEmail();
		}

		Product product = ps.getProduct(id);

		List<Rating> listSorted = product.getRating();
		model.addAttribute("ratinglist", listSorted);

		Optional<Rating> rating = ratingservice.getRating(idrating);
		product.getRating().remove(rating.get());
		String status = ratingservice.deleteRating(idrating, emailUser);
		System.out.println(status);
		ps.saveUpdateProduct(product);
		model.addAttribute("product", ps.getProduct(id));
		return new ModelAndView("/marketplace/productsView");
	}

	@PostMapping("/sortRating")
	public ModelAndView sortRating(Model model, HttpServletRequest request, @RequestParam(required = true) int id,
			@RequestParam("sort") String sortBy) {
		Principal principal = request.getUserPrincipal();
		Admin admin = adminservice.getAdmin(principal.getName());
		User u = us.getUser(principal.getName());
		if (admin != null) {
			model.addAttribute("mail", admin.getEmail());
			model.addAttribute("userid", admin.getId());
			model.addAttribute("user", admin);
			model.addAttribute("head",true);
		} else {

			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("orderactive", u.getOrderactive().getCarts());
			model.addAttribute("head",false);
		}

		Product product = ps.getProduct(id);
		List<Rating> listSorted = product.getRating();
		if (sortBy.equals("rate")) {
			listSorted = ratingservice.getRatingSorted(product.getRating());
			model.addAttribute("ratinglist", listSorted);
		}
		model.addAttribute("ratinglist", listSorted);

		model.addAttribute("product", product);
		return new ModelAndView("/marketplace/productsView");
	}

	@RequestMapping("/add")
	public ModelAndView add(Model model, HttpServletRequest request, @RequestParam(required = true) int id,
			@RequestParam(required = true) int cuantity) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			Product product = ps.getProduct(id);
			User u = us.getUser(principal.getName());
			model.addAttribute("orderactive", u.getOrderactive().getCarts());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("product", product);
			model.addAttribute("head",false);
			orderservice.addCart(u.getId(), id, cuantity);

		}
		return new ModelAndView("/marketplace/productsView");

	}

	@RequestMapping("/buy")
	public ModelAndView buy(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User u = us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("empty", false);
			if (u.getOrderactive().getCarts().isEmpty()) {
				Product product = ps.getProduct(id);
				model.addAttribute("head",false);
				model.addAttribute("empty", true);
				model.addAttribute("product", product);
				return new ModelAndView("/marketplace/productsView");
			}
			orderservice.buy(u.getId());
		}
		return new ModelAndView("/order/orderplaced");
	}

	@RequestMapping("/deletecart")
	public ModelAndView delete(Model model, HttpServletRequest request, @RequestParam(required = true) int idcart,
			@RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			Product product = ps.getProduct(id);
			User u = us.getUser(principal.getName());
			model.addAttribute("orderactive", u.getOrderactive().getCarts());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("product", product);
			model.addAttribute("head",false);
			orderservice.deleteCart(u.getId(), idcart);
		}
		return new ModelAndView("/marketplace/productsView");
	}

	@RequestMapping("/denied")
	public ModelAndView denied(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		User u = us.getUser(principal.getName());
		Admin admin = (Admin) admindao.findByEmail(principal.getName());

		if (admin != null) {
			model.addAttribute("mail", admin.getEmail());
			model.addAttribute("userid", admin.getId());
			model.addAttribute("user", admin);
		} else {
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
		}
		return new ModelAndView("/marketplace/denied");
	}
}
