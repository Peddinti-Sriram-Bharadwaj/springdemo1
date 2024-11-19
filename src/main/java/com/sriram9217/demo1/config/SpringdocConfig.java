package com.sriram9217.demo1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API Documentation")
                        .version("1.0")
                        .description("This is the API documentation for the application.")
                        .contact(new Contact()
                                .name("Preadacons")
                                .email("sriram9217@gmail.com")
                                .url("https://peddinti-sriram-bharadwaj.github.io")));
    }
}
