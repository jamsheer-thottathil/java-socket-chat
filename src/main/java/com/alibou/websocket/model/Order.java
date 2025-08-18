package com.alibou.websocket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "order")
public class Order {

    @Id
    private String id;

    private Customer customer;
    private List<Product> products;
    private Payment payment;
    private Address shippingAddress;
    private Address billingAddress;
    private String orderStatus;
    private Shipping shipping;
    private Instant createdAt;
    private Instant updatedAt;

    // ---- toJson() method ----
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"_id\":\"").append(id).append("\",");

        if (customer != null) sb.append("\"customer\":").append(customer.toJson()).append(",");
        if (products != null) {
            sb.append("\"products\":[");
            for (int i = 0; i < products.size(); i++) {
                sb.append(products.get(i).toJson());
                if (i < products.size() - 1) sb.append(",");
            }
            sb.append("],");
        }
        if (payment != null) sb.append("\"payment\":").append(payment.toJson()).append(",");
        if (shippingAddress != null) sb.append("\"shippingAddress\":").append(shippingAddress.toJson()).append(",");
        if (billingAddress != null) sb.append("\"billingAddress\":").append(billingAddress.toJson()).append(",");
        if (orderStatus != null) sb.append("\"orderStatus\":\"").append(orderStatus).append("\",");
        if (shipping != null) sb.append("\"shipping\":").append(shipping.toJson()).append(",");
        if (createdAt != null) sb.append("\"createdAt\":\"").append(createdAt).append("\",");
        if (updatedAt != null) sb.append("\"updatedAt\":\"").append(updatedAt).append("\",");

        // remove trailing comma if present
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("}");
        return sb.toString();
    }

    // ---------- Nested Classes ----------
    public static class Customer {
        private String customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public String toJson() {
            return String.format("{\"customerId\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}",
                    customerId, firstName, lastName, email, phone);
        }
    }

    public static class Product {
        private String productId;
        private String name;
        private String sku;
        private String category;
        private double price;
        private int quantity;
        private double total;

        public String toJson() {
            return String.format("{\"productId\":\"%s\",\"name\":\"%s\",\"sku\":\"%s\",\"category\":\"%s\",\"price\":%.2f,\"quantity\":%d,\"total\":%.2f}",
                    productId, name, sku, category, price, quantity, total);
        }
    }

    public static class Payment {
        private String method;
        private String transactionId;
        private String status;
        private double amount;
        private String currency;

        public String toJson() {
            return String.format("{\"method\":\"%s\",\"transactionId\":\"%s\",\"status\":\"%s\",\"amount\":%.2f,\"currency\":\"%s\"}",
                    method, transactionId, status, amount, currency);
        }
    }

    public static class Address {
        private String line1;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        public String toJson() {
            return String.format("{\"line1\":\"%s\",\"city\":\"%s\",\"state\":\"%s\",\"postalCode\":\"%s\",\"country\":\"%s\"}",
                    line1, city, state, postalCode, country);
        }
    }

    public static class Shipping {
        private String carrier;
        private String trackingNumber;
        private Instant estimatedDelivery;

        public String toJson() {
            return String.format("{\"carrier\":\"%s\",\"trackingNumber\":\"%s\",\"estimatedDelivery\":\"%s\"}",
                    carrier, trackingNumber, estimatedDelivery);
        }
    }
}
