package com.sservice.img.hosting.security;

import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.repository.UserRepository;
import com.sservice.img.hosting.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService; // твой сервис для работы с JWT
    private final UserRepository userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String path = request.getRequestURI();

        // Список публичных URL, которые не требуют проверки токена
        if (path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/docs") ||
                path.startsWith("/swagger-ui.html") ||
                path.startsWith("/api/Auth/register") ||
                path.startsWith("/api/Auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 1. Проверяем наличие и формат заголовка
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Извлекаем токен
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        // 3. Если токен валидный и пользователь ещё не в контексте — загружаем его
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserData userDetails = userDetailsService.findByUsername(username).get();

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 4. Продолжаем цепочку фильтров
        filterChain.doFilter(request, response);
    }
}
