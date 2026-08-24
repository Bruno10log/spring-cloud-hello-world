package com.bruno10log.customer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/users")
public class CustomerController {
    
    @Autowired
    private OrderClient orderClient;

    @GetMapping("/{id}")
    public Customer getUser(@PathVariable Long id) {
        return new Customer(id, "Bruno", "bruno@mail.com");
    }

    @GetMapping("/{id}/orders")
    public CustomerAndOrders getCustomerOrders(@PathVariable Long id) {
        var customer = new Customer(id, "Bruno", "bruno@mail.com");

        List<Order> orders = orderClient.getUserOrders(id);

        // Simulate fetching customer orders
        return new CustomerAndOrders(customer, orders);
    }
}


record Customer(Long id, String name, String email) {}
record Order(Long id, Long userId, String name) {}
record CustomerAndOrders(Customer customer, List<Order> orders) {}