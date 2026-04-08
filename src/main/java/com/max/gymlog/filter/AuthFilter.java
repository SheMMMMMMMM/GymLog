package com.max.gymlog.filter;

import com.max.gymlog.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    public AuthFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(PUBLIC_PATHS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.warn("Authorization header is invalid");
            return;
        }
        String token = header.substring("Bearer ".length());
        Optional<Long> userId = tokenService.getUserId(token);

        if(!userId.isPresent()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.warn("token not valid");
            return;
        }
        filterChain.doFilter(request, response);
    }
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/auth/login",
            "/auth/register"
    );
}

