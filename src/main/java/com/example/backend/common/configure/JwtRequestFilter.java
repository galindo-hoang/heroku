package com.example.backend.common.configure;

import com.example.backend.common.utils.JwtTokenUtil;
import com.example.backend.exception.ResourceInvalidException;
import com.example.backend.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountService accountService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(isRestrict(request)) {
            final String requestTokenHeader = request.getHeader("Authorization");
            String email;
            String jwtToken;
            if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
                jwtToken = requestTokenHeader.substring("Bearer ".length());
                try {
                    email = jwtTokenUtil.getEmailFromToken(jwtToken);
                    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.accountService.loadUserByUsername(email);
                       /* if(jwtTokenUtil.validateToken(jwtToken, email)) {

//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // After setting the Authentication in the context, we specify
//                    // that the current user is authenticated. So it passes the
//                    // Spring Security Configurations successfully.
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }*/
                        filterChain.doFilter(request, response);
                    }
                } catch (Exception e) {
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    Map<String, String> error = new HashMap<>();
//                    error.put("error_massage", e.getMessage());
                    ResourceInvalidException error = new ResourceInvalidException(e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }else {
                response.setHeader("error", "Token invalid or format invalid");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ResourceInvalidException error = new ResourceInvalidException("Token invalid or format invalid");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isRestrict(HttpServletRequest request) {
        return !(request.getServletPath().contains("/anonymous") || request.getServletPath().contains("/refreshToken") || request.getServletPath().contains("/auth/")||request.getServletPath().contains("/login"));
    }
}
