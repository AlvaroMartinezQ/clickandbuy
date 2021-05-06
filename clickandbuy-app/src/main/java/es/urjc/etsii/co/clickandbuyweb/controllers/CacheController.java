package es.urjc.etsii.co.clickandbuyweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hazelcast.core.HazelcastInstance;

@RestController
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	// cambiar por cache
	
	@GetMapping("/data")
	public ModelAndView getCacheContent(Model model) {
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("products");
		model.addAttribute("cache", cache.getNativeCache().toString());
		return new ModelAndView("cachedata");
	}

}
