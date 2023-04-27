package com.example.demo.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
    @EnableWebSocketMessageBroker - enables WebSocket support

    WebSocketMessageBrokerConfigurer
        - provides a base class to configure basic features (convenience, magic plumbing)
 */
@Component
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /*
        prefix that is prepended to every message's route.
     */
    public static final String MESSAGE_PREFIX = "/topic";

    /*
        Configures the backend endpoint for clients and servers to link /payroll
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/payroll").withSockJS();
    }

    /*
        configures broker used to relay messages between server and client.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
       registry.enableSimpleBroker(MESSAGE_PREFIX);
       registry.setApplicationDestinationPrefixes("/app");
    }
}
