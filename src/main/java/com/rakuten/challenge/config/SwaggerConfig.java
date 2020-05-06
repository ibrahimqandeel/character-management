package com.rakuten.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket characterManagementApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rakuten.challenge.controller"))
                .paths(regex("/character-management.*"))
                .build();
    }

    //
    private ApiInfo metaInfo() {
        Contact contact = new Contact("Ibrahim Qandeel", "Github", "ibrahim.s.qandeel@gmail.com");
        return new ApiInfo("Character Management API", "Rakuten Challenge", "1.0", "", contact, "Apache License Version 2.0", "", null);
    }
}
