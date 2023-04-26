# Event-Bus
An event bus is a vehicle for sending/receiving messages in asynchronous fashion. 
- DESTINATIONS
    - usually just Strings w/ 
- MESSAGES consist of:
    - BODY of data
        - usually come in form of: 
            - Strings
            - Java primitives
            - Vert.x JsonObject
        - supports registration of custom encoders/decoders to support specialized forms
        of body serialization.
            - AVOID THIS IF AT ALL POSSIBLE! :)
    - optional HEADERS for storing metadata about the destination
    - optional EXPIRATION DELAY
        - when it expires, the messages are discarded even if they haven't been processed yet.
        
Supports Decoupling Between Verticles
- there is no requirement that one verticle has to access another verticle
    - just need to agree on DESTINATION NAMES and DATA REPRESENTATION
    
- polyglot framework, so there is no need for a complex Interoperabiliity layer. 

Supports being extended OUTSIDE of the application process. 

## POINT TO POINT MESSAGING
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/EventBus_3/src/main/java/Overview_1/Screen Shot 2019-11-04 at 2.57.50 PM.png)

Messages are distributed in a round-robin fashion from producers to consumers:
- Producers are sending messages to some destinations (a.b.c in the diagram)
- A consumer then picks a message from the destination and processes it. 


## REQUEST-REPLY MESSAGING
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/EventBus_3/src/main/java/Overview_1/Screen Shot 2019-11-04 at 3.20.32 PM.png)

Variation on P2P messaging
- When a message is sent, a REPLY handler is registered
- the event-bus generates a temporary REPLY destination dedicated to comms between
    - request message producer expecting a REPLY
    - and the consumer that will eventually RECV and process the message
    
Common Use Case is for emulating asynchronous RPC

Another use case is "replying to a reply"
- further exchanges can happen between the requester and the message consumer via the
temp reply destination

The most critical aspect of this pattern is that THE CONSUMER REPLIES TO THE SENDER
- if you require this behavior, then this is the best pattern for you to use.

## PUBLISH/SUBSCRIBE MESSAGING
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/EventBus_3/src/main/java/Overview_1/Screen Shot 2019-11-04 at 3.29.36 PM.png)

Provides most decoupling between PRODUCERS and CONSUMERS of the three different types of EventBus
messaging

message is sent to a destination
- all subscribers of THAT destination receive the message. 
- there is no way to specify the reply-handler

USE CASE:
- this is especially useful when we don't know how many handlers will be interested in the
particular events. 

# EventBus v MessageBroker
EventBus is NOT a MessageBroker. 
The Event Bus does NOT
- support message acknowledgements
- support message priorities
- support message durability to recover from crashes
- provide routing rules
- provide transformation rules (schema adaptation, scatter/gather etc.)

The event bus DOES:
- carries VOLATILE events that are being processed asynchronously by verticles
- not all events are created equal
    - some might be lost. 
- we can use MBs like Apache Kafka to provide Message Broker functionality to the 
Reactive Applications we create using Vert.x