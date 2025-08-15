package com.alibou.websocket.chat;

import com.alibou.websocket.config.WebSocketConfig;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	private final SimpMessageSendingOperations messagingTemplate;

	// Constructor injection
	public ChatController(SimpMessageSendingOperations messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		System.out.println("----------------Received-----------------");
		// Forward the user message
		messagingTemplate.convertAndSend("/topic/public", chatMessage);

		// Automated bot reply
		ChatMessage botReply = ChatMessage.builder().type(MessageType.CHAT).sender("BOT")
				.content(chatMessage.getContent() + " automated bot reply").build();

		// We can add model integration here
		// ==============================

		messagingTemplate.convertAndSend("/topic/public", botReply);

		// ==============================
	}

	@MessageMapping("/chat.addUser")
	public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		messagingTemplate.convertAndSend("/topic/public", chatMessage);
	}
}
