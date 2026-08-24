package com.bruno10log.customer.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
interface OrderClient {

    @GetMapping("/api/orders/user/{userId}")
    List<Order> getUserOrders(@PathVariable Long userId);
}