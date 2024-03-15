package com.telecom.gestionservice.util.documentation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList("JWT"))
//                .components(new Components()
//                        .addSecuritySchemes("JWT",new SecurityScheme()
//                                .type(SecurityScheme.Type.APIKEY)
//                                .in(SecurityScheme.In.HEADER)
//                                .name("JWT")))
                .info(new Info().title("REST API Gestion")
                        .description("REST API para la Gestion de la Plataforma")
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
