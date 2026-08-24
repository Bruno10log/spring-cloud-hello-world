# Cloud Hello World

A simple Spring Boot microservices project that demonstrates service discovery, API routing, and service-to-service communication.

The project is composed of four services:

- **Discovery Server**: Provides service registration and discovery through Netflix Eureka.
- **API Gateway**: Routes incoming requests to the appropriate service and provides circuit-breaker fallbacks with Resilience4j.
- **Customer Service**: Exposes customer endpoints and retrieves customer orders through an OpenFeign client.
- **Order Service**: Provides order data for customers.

All services use Spring Boot and Spring Cloud. The gateway and customer service communicate with registered services by logical service name, allowing the application to avoid hard-coded service locations.

## Service Ports

| Service | Port |
|---|---:|
| Discovery Server | 8761 |
| API Gateway | 8080 |
| Customer Service | 8081 |
| Order Service | 8082 |

## Example Request

```bash
curl http://localhost:8080/api/users/1/orders
```

This request is routed through the API Gateway to the Customer Service, which uses OpenFeign to retrieve the customer's orders from the Order Service.
