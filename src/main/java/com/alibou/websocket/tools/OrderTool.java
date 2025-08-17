package com.alibou.websocket.tools;

import com.alibou.websocket.model.Order;
import com.alibou.websocket.repository.OrderRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderTool {

    @Autowired
    OrderRepository orderRepository;

    @Tool(name = "getOrderDetails", description = "Get the details of an order")
    public String getOrderDetails(String orderId) {
        // Simulate fetching order status
        System.out.println("Tool called with orderId: " + orderId);
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get().toJson();
        }
        return "Order not found. Please enter a valid order id";
    }
}
