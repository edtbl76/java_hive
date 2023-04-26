# Reactive

## Reactive Programming
development model focusing on
- OBSERVATION of data streams
- reacting on changes
- propagating changes


1. Data exists in an observed flow, called a STREAM
    - in Rx model, streams are called OBSERVABLES 
1. consumers SUBSCRIBE to observables and REACT to the data
1. consumers may transform data into a new "produced" stream (that is just another
OBSERVABLE)


## Reactive System
architectural style to build responsive and robust distributed systems based on async message-driven communication
- components send messages to VIRTUAL ADDRESSES
    - decouples senders from receivers
    
ADDRESS
- destination identifier (i.e. URL, opaque string)
- components register (or subscribe!) to a virtual address in order to recv messages

### CRITICAL PROPERTIES of Rx Systems
ELASTICITY
- ability to scale horizontally (out/in)
- enabled by decoupling provided by message interaction
    - message consumption can be load balanced

RESILIENCY
- ability to handle failover/recover
    - non-blocking failure (i.e. other services don't wait) because messages
    are asynchronous. 
    - replication, redundancy
    
    
SIDE-EFFECT
- as a result of these two characteristics, systems become more responsive and
can adapt to higher/lower workloads. 


## Reactive Microservice
"building blocks" of Rx Systems. 

#### FOUR CHARACTERISTICS
AUTONOMY

ASYNCHRONOUS

RESILIENCE

ELASTICITY


- RxMS can adapt to availability(or lack thereof) of services around them
- ISOLATION. 
- failure handled locally
- acts independently
- uses async messaging to coordinate/interact w/ peers
- recv message, can PRODUCE resp to messages

# ASYNCHRONOUS DEVELOPMENT MODEL
Writing Event Handlers!
- Note Event Handlers don't RETURN a result. (this implies that the caller is 
waiting for it.)
- Event Handlers may PROVIDE/PRODUCE a result, and if you want it you can have it. If not, piss off

SCALE!
- non-blocking models allow for HIGHLY CONCURRENT WORKLOAD using a minimal number of threads


# VERT.X EVENT LOOP
- consumes a queue of events and dispatches each event to the interested handlers
- works almost like a broker. 

## THREADING MODEL = REACTOR PATTERN
- It simplifies concurrency because there is only ONE THREAD. 
    - Since the messages are asynchronous, the events spend a very short time on the
    loop, because it doesn't have to wait for responses. 
    - This means MANY MANY events in a short amount of time.
    - 1 thread requires only a single CPU core
- you are always called by the same thread and NEVER CONCURRENTLY

## VERT.X THREADING MODEL = MULTI-REACTOR
- Vert.x maintains multiple event loops 
    - event producers are dispatched to an available "reactor" event loop. 
    - Once a Handler has been executed by an event loop it is ALWAYS invoked by THAT
    event loop
- this takes advantage of high concurrency + multicore systems
    - vert.x can create N # of event loops, where N = the # of CPU cores on the system
    in which it is running, each of which is a highly concurrent reactor. 
    

## GOLDEN RULE
NEVER BLOCK THE EVENT LOOP.
- POTENTIAL BLOCKERS
    - DB access
    - long computations
    
## VERTICLES
"chunks of code that get deployed and exec'd by Vert.x". Applications/Microservices may 
consist of many verticles instances running in a single Vert.x instance at a time.

VERTICLE LIFECYCLE (Typical)
- creates servers/clients
- registers a set of handlers
- encapsulates bounded context of business logic within the overall system


### REGULAR VERTICLES
- execute on the event loop. 
- NEVER BLOCK
- each verticle is always exec'd by same thread (and NEVER concurrently)
    - avoids having to use synchronization code
    
### WORKER VERTICLES
- NOT executed on vert.x event loop
- these are provided to execute blocking code
    - NOTE: this limits scalability, but sometimes shit happens.
    
### DEPLOYMENT VERTICLE
- a verticle used to deploy, configure and instantiate other verticles
- setting a number of instances greater than 1 will implement the MultiReactor Pattern

### CALLBACKS to OBSERVABLES

#### CALLBACKS
used judiciously in vert.x dev model. 
- orchestration of many async actions results in complicated code when using callbacks.


    EX:
        Retrieving Data from a Database
        
        1. - set up db connection
        2. - send query to db
        3. - process result set from db
        4. - close connection
        
        THIS IS UGLY AS SHIT!!!!
        
        client.getConnection(conn -> {
            if (conn.failed()) {
                // handle failure
            } else {
                SQLConnection connection = conn.result();
                connection.query("SELECT * from STUFF", rs -> {
                    if (rs.failed()) {
                        // more failure handling.
                    } else {
                        List<JsonArray> lines = rs.result().getResults();
                        lines.forEach(aLine -> {
                            System.out.println(new Stuff(aLine));
                        }
                        connection.close(done -> {
                            if (done.failed())
                                // more fucking failure handling....
                        });
                    }   
                }
            }
        });
        
#### VERT.X FUTURES
Vert.x Futures are objects that can be used to handle asynchronous actions, preventing
a descent into callback hell. 

NOTE: UNLIKE Java Futures, Vert.x Futures ARE NON-BLOCKING!!!!!!!


    EX:
        Futures makes the code look more declarative, it still shows how we are
        getting all of the rows in a single batch and then processing them. 
    
        Future<SQLConnection> future = getConnection();
        future
            .compose(conn -> {
                connection.set(conn);
                // return a future of the ResultSet
                return selectProduct(conn);
            })
            // Return a collection of products/stuffs, by mapping each
            // row to a product
            .map(result -> toProducts(result.getResults()));
            .setHandler(ar -> {
                if(ar.failed())
                    // failure handling. 
                else {
                    ar.result().forEach(System.out::println);
                }
                connection.get().close(done -> {
                    if(done.failed())
                        // blah
                });
            })
    
#### BETTER OPTION --> RxJava APIs
- combine + coordinate asynchronous tasks
- react to incoming messages as a stream of input.

  
    EX 3: 
    
        ExJava
        
        Single<SQLConnection> connection = client.rxGetConnection();
        connection
            .flatMapObseervable(conn -> 
                conn 
                    // exec query
                    .rxQueryStream("SELECT * from STUFFS")
                    // publish rows one-by-one into a new Observable.
                    .flatMapObservable(SqlRowStream::toObservable)
                    // shut 'er down
                    .doAfterTerminate(conn::close)
                    // map every row to a new product
                    .map(Product::new);
                    // Display (via subscription)
                    .subscribe(System.out::println)
           
           
## Vert.x Event Bus
Vert.x feature that allows different components of a distributed application to 
interact w/ each other using MESSAGES. 
- each message is sent to an ADDRESS
    - an ADDRESS is an opaque string that represents a destination.
- messages have a set of HEADERS and a BODY(payload)

Event bus is clustered
- messages can be dispatched over network between distributed senders + consumers
- starting vert.x in CLUSTERED MODE
    - nodes are connected to enable
        - SHARED-DATA STRUCTURE (GRID)
        - HARD-STOP FAILURE DETECTION
        - LOAD-BALANCING GROUP COMMUNICATION
- event bus can dispatch messages among ALL nodes in the cluster. 

Cluster Manager
- manages NODE DISCOVERY and inventory (i.e. registered nodes)
- EVENT BUS communication can use peer-to-peer TCP connections

### DELIVERY SEMANTICS
send()
- allows a component to send a message to an ADDRESS
- consumers
    - single consumer recvs a message
    - multiple consumers registered to an address are selected via round robin
    
publish()
- delivers messages to all consumers registered on an address. 


reply() (can be used w/ send)
- creates a REQUEST/RESPONSE mechanism that allows message-driven async interactions
between multiple components.

rx_ified Sends returns a Single. 
- Singles are reactive! so they recv a value when the reply is received. 
