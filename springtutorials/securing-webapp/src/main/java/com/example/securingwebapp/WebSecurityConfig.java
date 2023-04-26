package com.example.securingwebapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
    EnableWebSecurity
    - enables Spring Security's web security support.
    - provides hooks into Spring MVC

    WebSecurityConfigurerAdapter
    - helps configuration of web security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
        overrides from WebSecurityConfigurerAdapter
        - defines which URL paths should be secured/not secured

            - / and /home <-- no auth required.
            - /login requires auth.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    /*
        This is an inmemory user-store w/ a single user configured.
        - You would never do this. It's just for demo.
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        @SuppressWarnings({"unused", "deprecation"})
        UserDetails user =
               /*
                    This is OK for examples. However, this is a security faux pas otherwise.
                */
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
