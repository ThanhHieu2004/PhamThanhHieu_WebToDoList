package edu.ute.PhamThanhHieu_WebToDoList.security;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Filter to log authentication status for debugging purposes
 */
@Component
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
            System.out.println("Request to " + request.getRequestURI() + " | User: " + authentication.getName() + 
                " | Authenticated: " + authentication.isAuthenticated());
        } else {
            System.out.println("Request to " + request.getRequestURI() + " | No authentication in context");
        }
        
        filterChain.doFilter(request, response);
    }
}