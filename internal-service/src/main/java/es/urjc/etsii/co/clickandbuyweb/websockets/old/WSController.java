package es.urjc.etsii.co.clickandbuyweb.websockets.old;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ws")
public class WSController {
	private ServerSocketConnection ssc = new ServerSocketConnection();
	
	@RequestMapping("")
	public void test() {
		System.out.println("Socket controller");
		try {
			ssc.init();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Connection failed");
		}
	}
	
}
