# Reactive Systems. 
Generally speaking, a reactive framework or programming paradigm is the intersection of
- Concurrency Management/ Thread reduction by using:
    - Asynchronous Programming
    - Non-Blocking I/O
- single threaded async event processing (EventLoop)

## REACTIVE MANIFESTO PROPERTIES
### ELASTICITY
- the ability for an application to work w/ a variable number of instances
    - horizontal scaling to handle increased load
        - load balancing traffic across instances
        - spin up new instances
    - relies on instrumentation (of metrics) to create a deterministic signal for
    an orchestrator to listen to for stop/start/recover
    
### RESILIENT
- The "flip side" of elasticity
- when one instance crashes in a group of "elastic instances", resiliency is
naturally achieved because traffic can be redirected to other instances (or a new oone
can be started.)

- DEGRADED MODES
    - if an instance can't fulfill a request due to some conditions, it may be able to 
    provide some answer 
        - eventual consistency allows returning of stale data
        - retries (latency)
        - redirect to another instance (latency)
    - worst case:
        - error in a timely manner. 
        
### RESPONSIVE
- this is the result of ELASTICIY + RESILIENCY
- providing consistency in response times lead to strong SLA guarantees. 

### MESSAGE-DRIVEN
Using Asynchronous Message Passing > Blocking (i.e. RPC)
- this delivers/enables ELASTICITY and RESILIENCY
- as we see, Elasticity + Resiliency = Responsivity

- This actually adds more elasticity
    - we can dispatch messages to more instances
- We can implement flow control (BACKPRESSURE) to solve the consumer-producer problem