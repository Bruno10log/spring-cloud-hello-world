package com.bruno10log.gateway.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    
    private static final Logger logger = Logger.getLogger(FallbackController.class.getName());

    @RequestMapping("/fallback/customer-service")
    public ResponseEntity<Map<String, Object>> customerServiceFallback() {
        logger.warning("⚠️ Fallback acionado para customer-service");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "DEGRADED");
        response.put("message", "Serviço de usuários temporariamente indisponível");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "customer-service");
        
        return ResponseEntity
            .status(HttpStatus.SC_SERVICE_UNAVAILABLE)
            .body(response);
    }
    
    @RequestMapping("/fallback/order-service")
    public ResponseEntity<Map<String, Object>> orderServiceFallback() {
        logger.warning("⚠️ Fallback acionado para order-service");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "DEGRADED");
        response.put("message", "Serviço de pedidos temporariamente indisponível");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "order-service");
        
        return ResponseEntity
            .status(HttpStatus.SC_SERVICE_UNAVAILABLE)
            .body(response);
    }
}
