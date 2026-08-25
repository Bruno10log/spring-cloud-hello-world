package com.bruno10log.order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Order Service API")
                .description("""
                    API para consulta de pedidos.

                    ## Funcionalidades
                    - Buscar pedidos de um usuário

                    ## Autenticação
                    Por enquanto, sem autenticação (development mode)
                    """)
                .version("v1.0.0")
                .contact(new Contact()
                    .name("Bruno")
                    .email("bruno@email.com")
                    .url("https://github.com/Bruno10log"))
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Gateway"),
                new Server()
                    .url("http://localhost:8082")
                    .description("Serviço Direto")
            ));
    }
}
