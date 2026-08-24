package com.bruno10log.order.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        // Logic to retrieve orders
        return Arrays.asList(
            new Order(1L, userId, "Order 1"), 
            new Order(2L, userId, "Order 2")
        );
    }
}

record Order(Long id, Long userId, String name) {}