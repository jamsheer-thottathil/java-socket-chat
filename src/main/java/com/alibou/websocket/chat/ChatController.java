package com.alibou.websocket.chat;

import com.alibou.websocket.tools.OrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    OrderTool orderTool;

    @Autowired
    ChatClient chatClient;

    @Autowired
    ChatMemory chatMemory;

    private final SimpMessageSendingOperations messagingTemplate;
    private final String nothink = "/nothink ";

    // Constructor injection
    public ChatController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("----------------Received-----------------");
        // Forward the user message
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
        // Create a user message
        UserMessage userMessage = new UserMessage(nothink + chatMessage.getContent());

        // Create a prompt with the user message
        Prompt prompt = new Prompt(userMessage);
        String response = chatClient.prompt(prompt)
                .tools(orderTool)
                .call().content();

        // Automated bot reply
        ChatMessage botReply = ChatMessage.builder().type(MessageType.CHAT).sender("BOT")
                .content(response.replaceAll("</?think>", "").trim()).build();

        messagingTemplate.convertAndSend("/topic/public", botReply);

    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatMemory.clear("order response");
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
