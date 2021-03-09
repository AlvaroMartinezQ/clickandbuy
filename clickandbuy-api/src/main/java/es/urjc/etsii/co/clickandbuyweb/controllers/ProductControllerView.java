package es.urjc.etsii.co.clickandbuyweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.urjc.etsii.co.clickandbuyweb.models.User;
import es.urjc.etsii.co.clickandbuyweb.service.ProductService;
import es.urjc.etsii.co.clickandbuyweb.service.UserService;

@RestController
@RequestMapping("/products")
public class ProductControllerView {

	@Autowired
	private ProductService productservice;

	@Autowired
	private UserService userservice;

	@GetMapping("/main")
	public ModelAndView userMain(Model model) {
		return new ModelAndView("/products/productsMain");
	}

	@GetMapping("/all")
	public ModelAndView productsAll(Model model) {
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/stock")
	public ModelAndView productsStock(Model model) {
		model.addAttribute("products", productservice.productHasStock());
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/search")
	public ModelAndView productSearch() {
		return new ModelAndView("/products/productSearch");
	}

	@GetMapping("/searchdata")
	public ModelAndView productSearchData(@RequestParam(required = true, defaultValue = "@") String name, Model model) {
		model.addAttribute("products", productservice.productNameSearchL(name));
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/pricele")
	public ModelAndView productPriceLe(@RequestParam(required = true, defaultValue = "-1") String price, Model model) {
		model.addAttribute("products", productservice.productsPriceLe(Double.parseDouble(price)));
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/pricege")
	public ModelAndView productPriceGe(@RequestParam(required = true, defaultValue = "10000000") String price,
			Model model) {
		model.addAttribute("products", productservice.productsPriceGe(Double.parseDouble(price)));
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/pricebe")
	public ModelAndView productPriceBe(@RequestParam(required = true, defaultValue = "-10") String price1,
			@RequestParam(required = true, defaultValue = "-1") String price2, Model model) {
		model.addAttribute("products",
				productservice.productsPriceBe(Double.parseDouble(price1), Double.parseDouble(price2)));
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/price")
	public ModelAndView productPrice() {
		return new ModelAndView("/products/productPrice");
	}

	@GetMapping("/upload")
	public ModelAndView upload() {
		return new ModelAndView("/products/uploadForm");
	}

	@PostMapping("/upload-form")
	public ModelAndView uploadForm(@RequestParam(required = true) String email,
			@RequestParam(required = true) String product_name, @RequestParam(required = true) String product_desc,
			@RequestParam(required = true) String product_price, @RequestParam(required = true) String product_stock,
			Model model) {
		/*
		 * 0=201 CREATED -1=bad fields -2=wrong user -3=price or stock NaN -4=user not
		 * supplier
		 */
		int status = productservice.productUpload(email, product_name, product_desc, product_price, product_stock);
		if (status != 0) {
			model.addAttribute("bad_fields", true);
			return new ModelAndView("/products/uploadForm");
		}
		model.addAttribute("products", productservice.getProducts());
		return new ModelAndView("/products/productList");
	}

	@GetMapping("/deleteform")
	public ModelAndView deleteForm() {
		return new ModelAndView("/products/productDelete");
	}

	@PostMapping("/delete")
	public ModelAndView userDelete(@RequestParam(required = true, defaultValue = "-1") int usid,
			@RequestParam(required = true, defaultValue = "-1") int prodid, Model model) {
		String cad = userservice.deleteUserProduct(usid, prodid);
		model.addAttribute("result", cad);
		model.addAttribute("updated", true);
		return new ModelAndView("/products/productDelete");
	}

	@GetMapping("/modify")
	public ModelAndView modifySearch() {
		return new ModelAndView("/products/productModify");
	}

	@PostMapping("/update")
	public ModelAndView modifyUpdate(@RequestParam(required = true) String product_name,
			@RequestParam(required = true) String product_description,
			@RequestParam(required = true) String product_price, @RequestParam(required = true) String product_stock,
			@RequestParam(required = true) String is_active, Model model) {
		String cad = productservice.dataUpdate(product_name, product_description, product_price, product_stock,
				is_active);
		model.addAttribute("updated", true);
		model.addAttribute("result", cad);
		return new ModelAndView("/products/productModify");
	}

}
