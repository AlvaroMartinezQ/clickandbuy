package es.urjc.etsii.co.clickandbuyweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@GetMapping("/data")
	public ModelAndView getCacheContent(Model model) {
		model.addAttribute("cache", cacheManager.getCacheNames());
		return new ModelAndView("cachedata");
	}

}
