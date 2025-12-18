package com.example.demo.security;



import io.jsonwebtoken.Claims;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;



import java.io.IOException;

import java.util.List;



public class JwtAuthenticationFilter implements Filter {



    private final JwtUtil jwtUtil;



    public JwtAuthenticationFilter(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;

    }



    @Override

    public void doFilter(

        ServletRequest request,

        ServletResponse response,

        FilterChain chain)

        throws IOException, ServletException {



            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String header = httpRequest.getHeader("Authorization");



            if (header != null && header.startsWith("Bearer ")) {



                String token = header.substring(7);

                Claims claims = jwtUtil.parseClaims(token);



                String email = claims.get("email", String.class);

                String role = claims.get("role", String.class);



                UsernamePasswordAuthenticationToken auth =

                new UsernamePasswordAuthenticationToken(

                    email,

                    null,

                    List.of(() -> "ROLE_" + role)

                );



                auth.setDetails(

                    new WebAuthenticationDetailsSource().buildDetails(httpRequest)

                );



                SecurityContextHolder.getContext().setAuthentication(auth);

            }



            chain.doFilter(request, response);

        }

}