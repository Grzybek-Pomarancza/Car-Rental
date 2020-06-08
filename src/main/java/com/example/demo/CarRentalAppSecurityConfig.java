package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CarRentalAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            //Api
            "/resources/**",
            "/registration",
            "/filters",
            "/brand",
            "/test2",
            "/officesAll",
            "/carsInOffice/*"
            // other public endpoints of your API may be appended to this array
    };
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test1", "/rent").authenticated()
                .anyRequest().permitAll()
                .and().addFilter(new AuthorizationFilter(authenticationManager()))
                .csrf().disable();
    }
}