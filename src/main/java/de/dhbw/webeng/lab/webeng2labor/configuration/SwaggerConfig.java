package de.dhbw.webeng.lab.webeng2labor.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info()
                .title("Books API")
                .description("This is a simple API for managing books.")
                .version("0.0.1")
                .contact(new Contact().name("Jonas Berger").email("berger.jonas-it21@it.dhbw-ravensburg.de"))
                .license(new License().name("Apache 20").url("http://springdoc.org")));
    }

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            ApiResponses responses = operation.getResponses();
            responses.addApiResponse("400", new ApiResponse().description("Invalid request"));
            responses.addApiResponse("404", new ApiResponse().description("Resource not found"));
            responses.addApiResponse("500", new ApiResponse().description("Internal server error"));
        }));
    }
}
