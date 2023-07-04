package com.example.OrderManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringdocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {

        return new OpenAPI().info(new Info().title("Spring Doc").version("1.0.0").description("Spring doc"));
    }
    @Bean
    public GroupedOpenApi authenticationApi(){
        String [] paths = {"/auth/**"};
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi CustomerApi(){
        String [] paths = {"/customers/**"};
        return GroupedOpenApi.builder()
                .group("Customer")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi StockApi(){
        String [] paths = {"/stocks/**"};
        return GroupedOpenApi.builder()
                .group("Stock")
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi OrderApi(){
        String [] paths = {"/orders/**"};
        return GroupedOpenApi.builder()
                .group("Order")
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi ProductApi(){
        String [] paths = {"/products/**"};
        return GroupedOpenApi.builder()
                .group("Product")
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi ProductOrderApi(){
        String [] paths = {"/productOrders/**"};
        return GroupedOpenApi.builder()
                .group("ProductOrder")
                .pathsToMatch(paths)
                .build();
    }
}
