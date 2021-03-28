package es.urjc.etsii.co.clickandbuyweb.websockets.old;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketConnection {
	
	private final int socketPort = 8082;
	private ServerSocket serverSocket;
	
	public ServerSocketConnection() {
		super();
	}
	
	public void init() throws IOException {
		serverSocket = new ServerSocket(socketPort);
		// Up to 20 clients
		ExecutorService executor = Executors.newFixedThreadPool(20);
		while(true) {
			Socket socket = serverSocket.accept();
			executor.execute(() -> new ProcessPetition(socket));
		}
		
	}
	
	
	
}
