package com.example.messagingstompwebsocket;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/*
    Message Handling Controller
    - STOMP messages can be routed to generic Controller classes (i.e. @Controller vs. @RestController)
 */
@Controller
public class GreetingController {

    /*
        The MessageMapping annotation ensures that this method is called  if a message is sent to /hello destination
        - the message payload is bound to a HelloMessage which is passed into the method.
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {

        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName() + "!"));
    }
}
