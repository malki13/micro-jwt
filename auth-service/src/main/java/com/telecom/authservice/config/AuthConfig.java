package com.telecom.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/auth/register",
            "/auth/token",
            "/auth/validate",
            "/**/auth/login",
            "/**/auth/validate",
            "/**/renew",
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
            "/csrf",
            "/"
//            "/telecom-cedia/api/administracion/swagger-ui/**",
//            "/telecom-cedia/api/administracion/v3/api-docs/**",
//            "/telecom-cedia/api/administracion/swagger-ui/index.html/**",
//            "/telecom-cedia/api/administracion/v3/api-docs",
//            "/**/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .anyRequest().authenticated()
                ).build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(AUTH_WHITELIST);
    }

//    @Bean
//    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
////                "/swagger-resources/**", "/configuration/security",
////                "/swagger-ui.html", "/webjars/**");
//        //web.ignoring().antMatchers("/**");
//        web.ignoring().requestMatchers(AUTH_WHITELIST);
//    }

}
