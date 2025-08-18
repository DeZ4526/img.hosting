package com.sservice.img.hosting.config;

import com.sservice.img.hosting.security.JwtAuthenticationFilter;
import com.sservice.img.hosting.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Публичные эндпоинты для регистрации и логина
                        .requestMatchers("/api/Auth/register", "/api/Auth/login").permitAll()
                        // Swagger и OpenAPI публично
                        .requestMatchers(HttpMethod.GET,
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/docs/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // Пример защищённого эндпоинта
                        .requestMatchers("/api/Image/get_all").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/api/users/get_all").hasAnyRole("ADMIN", "MANAGER")
                        // Остальные эндпоинты требуют авторизации
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}