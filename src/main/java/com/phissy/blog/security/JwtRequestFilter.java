package com.phissy.blog.security;

import com.phissy.blog.service.user.CustomUserDetailService;
import com.phissy.blog.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader("Authentication");
        String username = null;
        String jwtToken = null;
        if (requestHeader != null &&  requestHeader.startsWith("Bearer")){
            jwtToken = requestHeader.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            }catch(ExpiredJwtException e){
                throw new RuntimeException("JWT has expired!");
            }catch(IllegalArgumentException e){
                throw new RuntimeException("Unable to get JWToken!");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities() );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
