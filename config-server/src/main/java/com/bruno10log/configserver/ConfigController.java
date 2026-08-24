package com.bruno10log.configserver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigController {

    @Value("${custom.message:}")
    private String message;

    @Value("${logging.level.root:}")
    private String logLevel;

    @GetMapping
    public Map<String, String> getConfig() {
        return Map.of("message", message, "logLevel", logLevel);
    }
}
