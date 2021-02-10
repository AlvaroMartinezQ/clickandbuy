package es.urjc.etsii.co.clickandbuyweb.controladorTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
	
	@GetMapping(value="/test")
	public String testMethod() {
		return "test.html";
	}
	
	@GetMapping(value="/test-2")
	public String test2(Model model) {
		model.addAttribute("nombre", "Nombre de ejemplo");
		return "test";
	}
	
}
