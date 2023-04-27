package com.example.demo.config;

import com.example.demo.service.PayrollUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.demo.data.Manager.PASSWORD_ENCODER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PayrollUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(PayrollUserDetailsService payrollUserDetailsService) {
        this.userDetailsService = payrollUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/built/**", "/main.css").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                    .and()
                .httpBasic()
                   .and()
                .csrf().disable()
                .logout()
                    .logoutSuccessUrl("/");

        // Turn off Frame Options to fix H2 console?
        http
                .headers().frameOptions().disable();

    }
}
