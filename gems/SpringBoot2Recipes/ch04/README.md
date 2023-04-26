# Chapter 4: Spring MVC - Async

## 4.1 Asynchronous Request Handling with Controllers and `TaskExecutor`

**Problem**

To reduce the throughput on the servlet container, how to asynchronously handle the request?

**Solution**

When a request comes in it is handled synchronously, which blocks the HTTP request handling thread. The response stays open and is available to be written to. This is useful when, for instance, a call takes some time to finish and instead of blocking threads this can be processed in the background and return a value to the user when finished.

## 4.2 Response Writers

**Problem**

Given a service, or multiple calls, how to collect the results from them and send the response to the client.

**Solution**

Use a `ResponseBodyEmitter` (or its subclass `SseEmitter`) to collect and send the response to the client.

## 4.3 WebSockets

**Problem**

How to use bidirectional communication from the client to the server over the web.

**Solution**

Use WebSockets to communicate from the client to the server and vice versa. WebSockets provide full duplex communication, unlike HTTP.

## 4.4 WebSockets with STOMP

**Problem**

How to use STOMP (**S**imple/**S**treaming **T**ext **O**riented **M**essage **P**rotocol) over WebSockets to communicate with the server.

**Solution**

Configure the message broker and use `@MessageMapping` annotated methods in an `@Controller` annotated class to handle the messages.
