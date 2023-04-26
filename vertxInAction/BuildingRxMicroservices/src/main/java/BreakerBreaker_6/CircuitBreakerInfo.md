# CircuitBreakers
Make sure you've checked out ResilientMicroServices First. 
A Circuit Breaker is a pattern designed to handle repetitive failures (i.e. when retries have overstayed their welcomes, 
or more accurately, we have some failure that isn't recoverable, and its beating the shit out of our own services)
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/BuildingRxMicroservices/src/main/java/BreakerBreaker/Screen Shot 2020-01-03 at 3.29.58 PM.png)

- This is another FAILURE MODE ISOLATION pattern. 
- it protects a MS from calling a failing service over and over

## 3 State Machine
1. Closed
    - standard operation
    - tracks failures
1. Open
    - failure threshold is met. 
    - CB fails immediately w/o any attempt to exec underlying implementation
    - might execute a FALLBACK instead (i.e. some default result that is harmless to upstream services)
    - starts a timer/conditional algo that measures possibility of success
1. half-open
    - eventually, possibility of success reaches the "is it all clear?" state. 
    - next exec will work normally, and the result is tracked. 
        - if it works, CB closes and we're good to go
        - if it fails, CB opens back up, and a timeout/check begins running again. 

# VERTX CIRCUIT BREAKER IMPLS

## HYSTRIX (NETFLIX)
- most well known CB impl. 
- supported by Vert.x
    - it uses a separate thread pool, which requires EXPLICIT context switching to cert.x event loop in order to
    execute different callbacks. 
    
## Vert.X Circuit Breaker (Vert.x/Eclipse)
- explicitly designed to support asynchronous development model. 