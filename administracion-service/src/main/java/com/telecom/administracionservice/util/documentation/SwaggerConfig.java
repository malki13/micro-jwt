package com.telecom.administracionservice.util.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.filter.ForwardedHeaderFilter;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableSwagger2
//@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true")
public class SwaggerConfig {

//    public SwaggerConfig() {
//        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class, Pageable.class);
//    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new Components()
                        .addSecuritySchemes("JWT",new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("JWT")))
                .info(new Info().title("REST API Administracion")
                        .description("REST API para la Administracion de la Plataforma")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Malki Yupanki").email("malkiyupanki12@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("springshop-admin")
////                .pathsToMatch("/admin/**")
//                .packagesToScan("com.telecom.administracionservice")
//                .addOpenApiCustomizer(internalApiCustomizer())
//                .build();
//    }
//    @Bean
//    public OpenApiCustomizer internalApiCustomizer() {
//        return openApi -> openApi
//                .addSecurityItem(new SecurityRequirement().addList("apiKey"))
//                .components(new Components()
//                        .addSecuritySchemes("apiKey", new SecurityScheme()
//                                .type(SecurityScheme.Type.APIKEY)
//                                .in(SecurityScheme.In.HEADER)
//                                .name("apiKey")))
//                .info(new Info().title("Internal REST API"));
//    }
//    @Bean
//    ForwardedHeaderFilter forwardedHeaderFilter() {
//        return new ForwardedHeaderFilter();
//    }
//
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .securitySchemes(Arrays.asList(apiKey()))
//                //.securityContexts(Arrays.asList(securityContext()))
//                .select()
//                //.apis(RequestHandlerSelectors.any())
//                //.paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.tarpuq.ups"))
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }

}
