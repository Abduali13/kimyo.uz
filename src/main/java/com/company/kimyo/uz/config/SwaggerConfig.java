package com.company.kimyo.uz.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedProductsApi(){
        return GroupedOpenApi.builder()
                .group("Products")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedUsersApi(){
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedCardApi(){
        return GroupedOpenApi.builder()
                .group("Card")
                .pathsToMatch("/cards/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOrdersApi(){
        return GroupedOpenApi.builder()
                .group("Orders")
                .pathsToMatch("/orders/**")
                .build();
    }
    @Bean
    public GroupedOpenApi groupedFileApi(){
        return GroupedOpenApi.builder()
                .group("File")
                .pathsToMatch("/file/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedCategoryApi(){
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch("/category/**")
                .build();
    }

}
