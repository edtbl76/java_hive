package com.example.securingwebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    Configure Spring MVC
    - set up VIEW Controllers to expose the MVC templates.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*
        Overrides the same method from WebMvcConfigurer
        - adds 4 view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
}
