package com.alibou.websocket.tools;

import com.alibou.websocket.model.Order;
import com.alibou.websocket.repository.OrderRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderTool {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ChatMemory chatMemory;

    @Tool(name = "getOrderDetails", description = "Get the details of an order")
    public ToolResponseMessage getOrderDetails(String orderId) {
        System.out.println("Tool called with orderId: " + orderId);

        Optional<Order> order = orderRepository.findById(orderId);

        ToolResponseMessage.ToolResponse toolResponse;

        if (order.isPresent()) {
            toolResponse = new ToolResponseMessage.ToolResponse(
                    orderId,
                    "getOrderDetails",
                    order.get().toJson()
            );
        } else {
            toolResponse = new ToolResponseMessage.ToolResponse(
                    orderId,
                    "getOrderDetails",
                    "Order not found. Please enter a valid order id"
            );
        }

        ToolResponseMessage response = new ToolResponseMessage(List.of(toolResponse));

        // Persist in memory
        chatMemory.add("default", response);

        return response;
    }


}
