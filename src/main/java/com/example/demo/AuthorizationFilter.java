package com.example.demo;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserService userService;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            final String authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.toLowerCase().startsWith("basic")) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Bad Authorization!");
            } else {
                if (userService == null) {
                    ServletContext context = request.getSession().getServletContext();
                    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, context);
                }
                // Authorization: Basic base64credentials
                String base64Credentials = authorization.substring("Basic".length()).trim();
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                // credentials = username:password
                User user = userService.verifyUserData(credentials.split(":", 2));
                UsernamePasswordAuthenticationToken authentication = getAuthentication(user);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (InvalidDataException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid password!");
        } catch (ObjectNotFoundException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid email!");
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
        return new UsernamePasswordAuthenticationToken(user.getEmail(), null, simpleGrantedAuthorities);
    }
}
