package com.aleksandrbogomolov.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService detailsService;

    @Autowired
    public SecurityConfiguration(@Qualifier("userService") UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
            .authorizeRequests()
            .antMatchers("/", "/login").permitAll()
            .antMatchers("/meals", "/meals/**").authenticated()
            .antMatchers("/users", "/users/**").authenticated()
            .and().formLogin()
            .and().logout()
            .and().csrf().disable();
    }

    @Autowired
    protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService);
    }
}
