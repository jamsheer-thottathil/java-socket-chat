//package com.alibou.websocket.config;
//
//
//import com.alibou.websocket.chat.ChatMessage;
//import com.alibou.websocket.chat.MessageType;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionSubscribeEvent;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class WebSocketEventListener {
//
//	private final SimpMessageSendingOperations messagingTemplate;
//
//	// This event triggers when user sends a message
//	@EventListener
//	public void handleChatMessage(SessionSubscribeEvent event) {
//		System.out.println("=========listner");
//		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//		String username = (String) headerAccessor.getSessionAttributes().get("username");
//		String userMessage = (String) headerAccessor.getSessionAttributes().get("message");
//
//		if (username != null && userMessage != null) {
//			log.info("User message: {}", userMessage);
//
//			ChatMessage botReply = ChatMessage.builder().type(MessageType.CHAT).sender("BOT")
//					.content(userMessage + " automated bot reply").build();
//
//			messagingTemplate.convertAndSend("/topic/public", botReply);
//		}
//	}
//}
