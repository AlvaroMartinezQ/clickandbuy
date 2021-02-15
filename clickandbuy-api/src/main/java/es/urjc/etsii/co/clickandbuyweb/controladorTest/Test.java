package es.urjc.etsii.co.clickandbuyweb.controladorTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class Test {
	
	/*
	 * Logger class useful methods:
	 * -> .trace
	 * -> .debug
	 * -> .info
	 * -> .warn
	 * -> .error
	 */
	Logger logger = LoggerFactory.getLogger(Test.class);
	
	@GetMapping(value="/hello")
	public String testMethod() {
		logger.info("Hello endpoint working fine...");
		return "hello.html";
	}
	
	@GetMapping(value="/test-2")
	public String test2(Model model) {
		model.addAttribute("nombre", "Nombre de ejemplo");
		return "test";
	}
	
}
