# 11.6 Reducing Context Switch Overhead

### When Context Switches Attack...
- there are tasks that involve blocking operations
- the process of transitioning between running and blocked states
entails a CONTEXT SWITCH

### The Logging AntiPattern
- Generating Log messages while processing requests is often a
source of blocking

#### The "println" wrapper approach
- The logger is a thin wrapper that performs formatting and
other configuration, but ultimately just prints text via 
**system.out.println**

#### Multi-Thread Approach 
- Logging is performed in a background thread instead of in the
requesting thread (which is presumably where the work is 
being done.)

    
    Tradeoffs for the Performance Gains are design
    complications that include:
    
    - interruption
        
        (What happens if a thread blocked in a logging 
        operation is interrupted?)
        
    - service guarantees
    
        (Does the logger guarantee that a successfully queued
        log message will be logged prior to service 
        shutdown)
        
    - saturation policy
    
        (what happens when producers log messages faster than 
        the logger thread can handle them?) 
        
    - service lifecycle
    
        (How do we shut down the logger?)
        (How do we communicate service state to producers?)

---
#### Comparison of Approaches
- roughly the same from a devs perspective
- performance differences based on some factors:
    - volume of logging activity
    - how many threads are performing logging work
    - cost of context switching etc. 
    
##### Challenges of Single-Threaded ("inline") approach
- involves I/O and locking
    - increased context switching = increased service times.

##### Challenges of Multi-Threaded Approach 
- Service time for logging operations
    - the logging operation
    - any computation associated w/ the I/O stream classes
    - if an I/O operation blocks, the service time includes
    the duration that thread spends waiting.
        - OS deschedules thread until the I/O completes
        - when I/O completes, other threads are probably active, so they'll
        finish their scheduling quanta.
        - there might be threads ahead of us on the scheduling queue


- if multiple threads are logging simultaneously, and there is contention for the
output stream lock we have the same result as w/ blocking I/O:
    - thread blocks waiting for the lock and it will be context switched out.

---

#### Why Increased Service Time Is Bad 
- Kind of obvious, but the longer a service is running, the more time someone is waiting for the
result (i.e. the output) of that service. 
    - There is (usually) an inverse proportional relationship between service time and quality of service 


- Longer service times = more lock contention
    - "Get in and Get out! No Dilly Dally!"
        - the longer a lock is held, the more likely that lock will be contended. 
        - long service times = (technically) longer lock holds 
        
        
    Concurrent Systems Perform "The Best" when most lock acquisitions are uncontended
    
    - uncontended locks do not require context switches
    - contended locks do. 
    
    Obvious
    
### Solving Our Example Approach
- Theoretically, this is our goal:
    - remove context switches. 
        - to do that, remove lock contention
            - to do that, remove code that can block
                
                
- Remove the I/O out of the request-processing thread(s)
    - I/O is the code that is most likely to block
    - threads making calls to log() won't block waiting for the I/O to complete
    
    
- Threads just queue the message and then return to their task. 


#### Side Effects - Potential Contention for Message Queue 
- We introduce the possibility of contention for the message queue
    - **put()** is a lightweight operation compared to I/O 
        - (I/O also requires native/system calls)
    - as long as the message queue isn't full, there is very little 
    chance that put() operations will block. 
    
    
### End Result
- request thread is less likely to block
    - thread is less likely to be context switched out in th emiddle of a request


- By removing I/O, we've taken a complicated/uncertain code path and turned it into a 
    "straight-line" code path.
    

#### Moving Work Around
- moving I/O to a thread where the cost of blocking isn't perceived to the user is a win.
- moving ALL "logging" I/O to a single thread is even better
    - eliminates any possibility of contention for the output stream
    - if there is no contention, there is no blocking
    

    Generally speaking, throughput can be improved by reducing the amount of resources that are
    spent
        
        - scheduling
        - context switching
        - managing/contending for lock acquisition
        
