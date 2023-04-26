# HealthCheck and Failovers
CircuitBreakers allow consumers to handle failures on their side, but this
doesn't handle a CRASH

## What happens when a service crashes
ideally, an FO/HA (Failover, High Availability) strategy results in resarting
failed services. 
- however,in order to do this successfully, we need to be able to DETECT failure
properly

## HEALTHCHECK
an API provided by a microservice that indicates the state of that MS.
- used by caller() to determine if the MS it is calling is healthy.
    - if a MS returns an unhealthy state to its caller, the outcome is 
    going to fail, so it probably shouldn't be called anymore
        - you could mark it as CRAPPY, and then set a timeout that has
        to expire before you try again
- NOTE: 
    - health checks are used to determine if a service is UP. 
    - there are no guarantees that it is going to accurately
    handle your request
    - there are no guarantees that the network is going to 
    deliver your request, or the service's response. 
    
### HEALTHCHECK TYPES
- READINESS CHECK
    - used at deploy time to broadcast that a service is up, initialized and
    ready to serve requests
- LIVENESS CHECK
    - used to detect whether or not service is up or whether it is CAPABLE of
    handling requests (remember... just because it is able to, doesn't
    mean that it will)
    
    
## Vert.x HealthCheck Implementation
1. implement a ROUTE returning the state
1. use a real request instead of a route
1. use Vert.x health check module to implement SEVERAL HCs
    - allows different outcomes. 