package es.urjc.etsii.co.clickandbuyweb.websockets.old;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProcessPetition implements Runnable {
	
	private Socket socket;
	
	public ProcessPetition(Socket socket) {
		super();
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// Send and receive info
			System.out.println(is.readAllBytes());
			
			os.write(1);
			
			System.out.println("Closing client connection");
			is.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Connection failed");
		}

	}

}
