import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigController {
    
    private String message;

    private String logLevel;

    public Map<String, String> getConfig() {
        return Map.of("message", message, "logLevel", logLevel);
    }
}
