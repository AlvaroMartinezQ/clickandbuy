package es.urjc.etsii.co.clickandbuyweb.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
	
	// TODO: delete session connections when clients disconnect
	private List<WebSocketSession> connectedClients = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		if(!connectedClients.contains(session)) {
			connectedClients.add(session);
		}
		System.out.println(message);
		String value = message.getPayload();
		if(value.equals("connection petition")) {
			TextMessage newMsg = new TextMessage("Connected");
			session.sendMessage(newMsg);
			return;
		}
		for(WebSocketSession client: connectedClients) {
			client.sendMessage(message);
		}
	}

	// Binary messages for example photos, PDFs...
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
	    System.out.println("New Binary Message Received");
	    session.sendMessage(message);
	}
}
