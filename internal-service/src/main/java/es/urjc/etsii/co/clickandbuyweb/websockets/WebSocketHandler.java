package es.urjc.etsii.co.clickandbuyweb.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
	
	private List<WebSocketSession> connectedClients = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		if(!connectedClients.contains(session)) {
			System.out.println("New session connected: " + session.toString());
			connectedClients.add(session);
		}
		String value = message.getPayload();
		if(value.equals("connection petition")) {
			System.out.println("Connection petition");
			System.out.println("Connected clients: " + connectedClients);
			TextMessage newMsg = new TextMessage("From server: Connected");
			session.sendMessage(newMsg);
			return;
		} 
		if(value.equals("connection close petition")) {
			System.out.println("Disconnect petition");
			connectedClients.remove(session);
			System.out.println("Connected clients: " + connectedClients);
			TextMessage newMsg = new TextMessage("From server: Disconnected");
			session.sendMessage(newMsg);
			return;
		}
		System.out.println("New message received | Sending message to all connected clients");
		for(WebSocketSession client: connectedClients) {
			client.sendMessage(message);
		}
	}

	// Binary messages for example photos, PDFs... Not used
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
	    System.out.println("New Binary Message Received");
	    session.sendMessage(message);
	}
}
