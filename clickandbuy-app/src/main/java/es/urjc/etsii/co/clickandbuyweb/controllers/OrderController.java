package es.urjc.etsii.co.clickandbuyweb.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.Order;
import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.OrderService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;
import mailer.ReadPropertiesValue;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderservice;

	@Autowired
	private UserService us;

	@RequestMapping("/ordermain")
	public ModelAndView singUp(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User u = us.getUser(principal.getName());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			return new ModelAndView("order/ordermain");
		}
		return new ModelAndView("user/singUp");
	}
	
	@RequestMapping("/view")
	public ModelAndView view(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User u = us.getUser(principal.getName());
			model.addAttribute("orders",u.getMyOrders());
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			model.addAttribute("sended",false);
			
			return new ModelAndView("order/orderlist");
		}
		return new ModelAndView("product/management");
	}
	
	@RequestMapping("/seeorder")
	public ModelAndView seeOrder(Model model, HttpServletRequest request, @RequestParam(required = true) int id) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			User u = us.getUser(principal.getName());
			Order order = orderservice.getOrderById(id);
			model.addAttribute("order",order);
			model.addAttribute("mail", u.getEmail());
			model.addAttribute("userid", u.getId());
			model.addAttribute("user", u);
			
			return new ModelAndView("order/seeorder");
		}
		return new ModelAndView("product/management");
	}
	
	@RequestMapping("/ordermail")
	public ModelAndView orderMail(Model model, HttpServletRequest request, @RequestParam(required=true)int id) throws IOException {
		Principal principal = request.getUserPrincipal();
		User u=us.getUser(principal.getName());
		Order order = orderservice.getOrderById(id);
		RestTemplate restTemplate = new RestTemplate();
		String data = order.toString();

		// Properties file
		ReadPropertiesValue appConf = new ReadPropertiesValue();
		/*
		 *  Internal service URL
		 */
		String url="http://" + appConf.getInternalServiceHost() + "/ordermail/send?data=" + data + "&email=" + u.getEmail();
		/*
		 * Fire the end-point call
		 * The end-point sends back a String object
		 */
		String response = restTemplate.getForObject(url, String.class);
		/*
		 * Print the response from the server. For DEBUG purposes
		 */
		System.out.println(response);
		model.addAttribute("sended",true);
		model.addAttribute("mail", u.getEmail());
		model.addAttribute("userid", u.getId());
		model.addAttribute("user", u);
		model.addAttribute("orders",u.getMyOrders());
		
		return new ModelAndView("order/orderlist");
	}

}
