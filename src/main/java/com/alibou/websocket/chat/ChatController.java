package com.alibou.websocket.chat;

import com.alibou.websocket.config.WebSocketConfig;

import java.util.List;

import com.alibou.websocket.tools.OrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    OllamaChatModel model;


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
        String response = ChatClient.create(model).prompt(chatMessage.getContent()).tools(new OrderTool()).call().content();

        // Automated bot reply
        ChatMessage botReply = ChatMessage.builder().type(MessageType.CHAT).sender("BOT")
                .content(response).build();

        messagingTemplate.convertAndSend("/topic/public", botReply);

    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
