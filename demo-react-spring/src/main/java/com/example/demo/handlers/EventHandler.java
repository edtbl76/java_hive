package com.example.demo.handlers;

import com.example.demo.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.example.demo.config.WebSocketConfiguration.MESSAGE_PREFIX;

/*
    @RepositoryEventHandler
        (Value() is deprecated, so I'm not passing a pojo to the annotation)
 */
@Component
@RepositoryEventHandler
public class EventHandler {

    /*
        class attributes are autowired from app context (via constructor injection)
     */
    private final SimpMessagingTemplate template;
    private final EntityLinks entityLinks;

    @Autowired
    public EventHandler(SimpMessagingTemplate template, EntityLinks entityLinks) {
        this.template = template;
        this.entityLinks = entityLinks;
    }

    /*
        Handler methods
        - create, delete, save each flag methods that listen to specific events.
        - each method invokes `SimpMessagingTemplate.convertAndSend()` to transmit a
        message over WebSockets.
            - pub-sub approach
            - 1 message relayed to every attached customer.

        - each message has a different route, which allows multiple messages to be sent to
        distinct receivers on the client while needing only a single open WebSocket
            (Resource efficient)

        - must be public.

     */
    @HandleAfterCreate
    public void newEmployee(Employee employee) {
        this.template.convertAndSend(MESSAGE_PREFIX + "/newEmployee", getPath(employee));
    }

    @HandleAfterDelete
    public void deleteEmployee(Employee employee) {
        this.template.convertAndSend(MESSAGE_PREFIX + "/deleteEmployee", getPath(employee));
    }

   @HandleAfterSave
   public void updateEmployee(Employee employee) {
       this.template.convertAndSend(MESSAGE_PREFIX + "/updateEmployee", getPath(employee));
   }

    /*
        This is a helper
     */
    private String getPath(Employee employee) {
        return this.entityLinks
                .linkForItemResource(employee.getClass(), employee.getId())
                .toUri().getPath();
    }
}
