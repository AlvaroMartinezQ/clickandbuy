package es.urjc.etsii.co.clickandbuyweb.api.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mailer.UserInfo;

@RestController
@RequestMapping("/legacy")
public class PrivacyPolicyController {

	final String userInfo = "https://127.0.0.1:8443/api/user/info";
	
	@RequestMapping("/user/info")
	public String userInfo(@RequestParam(required=true)String data, @RequestParam(required=true)String email) {
		System.out.println("\nRequest from main server. Payload: " + data + "\n");
		UserInfo mail = new UserInfo(email, data);
		/*
		 * Next lines runs the email send in a different thread
		 */
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(() -> mail.setUp());
		executor.shutdown();
		return "status: sending user data email";
	}
	
}
