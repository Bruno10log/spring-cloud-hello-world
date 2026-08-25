package com.bruno10log.customer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/users")
public class CustomerController {
    
    @Autowired
    private OrderClient orderClient;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Customer getUser(@PathVariable Long id) {
        return new Customer(id, "Bruno", "bruno@mail.com");
    }

    @GetMapping("/{id}/orders")
    @Operation(summary = "Get customer orders", description = "Returns the orders for a specific customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders found"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
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