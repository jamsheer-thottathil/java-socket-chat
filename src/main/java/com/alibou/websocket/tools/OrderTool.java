package com.alibou.websocket.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class OrderTool {

    @Tool(name = "getOrderDetails", description = "Get the details of an order")
    public String getOrderDetails(String orderId) {
        // Simulate fetching order status
        System.out.println("Tool called with orderId: " + orderId);
        return "{\n" +
                "  \"_id\": \"SHOP:33542:4\",\n" +
                "  \"customer\": {\n" +
                "    \"customerId\": \"CUST001\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Taylor\",\n" +
                "    \"email\": \"john.taylor@example.com\",\n" +
                "    \"phone\": \"+1-555-1001\"\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": \"P1000\",\n" +
                "      \"name\": \"Product 1\",\n" +
                "      \"sku\": \"SKU-001\",\n" +
                "      \"category\": \"Pipes\",\n" +
                "      \"price\": 111.53,\n" +
                "      \"quantity\": 3,\n" +
                "      \"total\": 334.59\n" +
                "    }\n" +
                "  ],\n" +
                "  \"payment\": {\n" +
                "    \"method\": \"PayPal\",\n" +
                "    \"transactionId\": \"TXN1000\",\n" +
                "    \"status\": \"Pending\",\n" +
                "    \"amount\": 115.02,\n" +
                "    \"currency\": \"USD\"\n" +
                "  },\n" +
                "  \"shippingAddress\": {\n" +
                "    \"line1\": \"971 Main St\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"postalCode\": \"85314\",\n" +
                "    \"country\": \"USA\"\n" +
                "  },\n" +
                "  \"billingAddress\": {\n" +
                "    \"line1\": \"407 Main St\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"postalCode\": \"58160\",\n" +
                "    \"country\": \"USA\"\n" +
                "  },\n" +
                "  \"orderStatus\": \"Processing\",\n" +
                "  \"shipping\": {\n" +
                "    \"carrier\": \"FedEx\",\n" +
                "    \"trackingNumber\": \"TRK1000\",\n" +
                "    \"estimatedDelivery\": {\n" +
                "      \"$date\": \"2025-08-11T09:00:00.000Z\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"createdAt\": {\n" +
                "    \"$date\": \"2025-08-01T09:00:00.000Z\"\n" +
                "  },\n" +
                "  \"updatedAt\": {\n" +
                "    \"$date\": \"2025-08-04T01:00:00.000Z\"\n" +
                "  }\n" +
                "}";
    }
}
