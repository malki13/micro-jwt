package com.tutorial.gatewayservice.filter;

import com.tutorial.gatewayservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Error en la cabezera de autorizacion");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                try {
//                    template.getForObject("http://auth-service//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);

                }catch (Exception e){
                    System.out.println("token invalido");
                    throw new RuntimeException("No autorizado");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
