package com.tutorial.gatewayservice.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = Arrays.asList(
            "/auth/register",
            "/auth/token",
            "/eureka",
            "/uploads/**",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/csrf"
//            "/telecom-cedia/api/administracion/swagger-ui/**",
//            "/telecom-cedia/api/administracion/v3/api-docs/**",
//            "/telecom-cedia/api/administracion/v3/api-docs",
//            "/telecom-cedia/api/administracion/swagger-ui/index.html/**",
//            "/**/swagger-ui/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
