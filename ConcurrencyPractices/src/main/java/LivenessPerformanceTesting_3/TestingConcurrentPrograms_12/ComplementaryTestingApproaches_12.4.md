# 12.4 Complementary Testing Approaches

### The Real Goal of Testing

    NASA devotes more reosurces to testing than they do
    to development...
    
        ... at a 20:1 ratio
        
    and their code is still not defect free. 
    
    
    In coplex programs, no amount of testing can find all 
    coding errors. 
    
    
- The goal of testing is to INCREASE CONFIDENCE that the
code works as expected. 

        
        Achieve the greatest possible confidence given
        the test resources available.
        

## 12.4.1 Code Review
- Unit Tests/Performance tests are NO substitute for the
rigorous code review of multiple sets of eyes. 


- Code review is no subsitute for testing either. 


- improves the quality of comments/documentation that
describe impl details. 
    - Typically we can make it more concise and easier
    to maintain.

### What gets caught in Code Review
- absent minded mistakes 
    - (even from the best of experts)
- RACE CONDITIONS
    - often found by a person better than test programs
- PLATFORM ISSUES
    - JVM impl details
    - processor memory models


## 12.4.2 Static Code Analysis Tools

### Static Code Analysis
- process of analyzing code w/o executing it. 
- the tools audit classes looking for
    - common "bug patterns"
- usually provide a list of warnings that must be 
investigated by hand to determine their validity
    
    
    EX:
    
        FindBugs http://findbugs.sourceforge.net
        
        
#### "Bug Pattern Detectors"
- more or less a static registry of common coding errors that
can easily be overlooked by testing or code review. 


#### Linting
- Linting is similar but typically specific to syntactical errors


    NOTE: 
        
        Linting has had a historically poor reputation for
        a high amount of false warnings. 
        

### FindBugs + Concurrency Related Bug Patterns    

#### Inconsistent Synchronization
- Many objects use a synchronization policy of guarding ALL vars
w/ the object's intrinsic lock
    - if a field is accessed frequently, but not always w/ the
    **this** lock held, then it may be an indication that the
    synchronization policy isn't being followed.
    
    
    NOTE:
        - Static Analysis tools have to "guess" what the
        synchronization policy as there is no formal
        standardization for concurrency specification.
        
#### Invoking Thread.run()
- **Thread** impls **Runnable**
    - (therefore it has a **run** method)
    

- COMMON ERROR:
    - most devs mean to use **Thread.start()**, but
    accidentally call **run()** directly
    
    
#### Unreleased Lock
- explicit locks aren't released like intrinsic locks
    - they aren't automatically released when control exits the 
    scope in which they are acquired. 
        
        
- BEST PRACTICES
    - explicit locks are usually released in a **finally** block
    - this prevents the lock from being "unreleased" when a method exits early
    due to exception
    

#### Empty -synchronized- block
- frequently used incorrectly
- usually better solutions to whatever problem the dev was attempting to solve



#### Double-checked Locking
- Considered a "broken" idiom for reducing synchronization overhead in lazy init (Sect.16.2.4)
that involves reading a mutable shared field w/o proper synchronization


#### Starting a thread from a constructor
- introduces the risk of subclassing programs
- allows **this** reference to escape the constructor


#### Notification Errors
- **notify** and **notifyAll** methods are used to indicate that an object's state
may have changed in a manner that would unblock threads waiting on the 
associated condition queue. 
    - They should ONLY be called when the state associated w/ the queue has CHANGED
    

- COMMON ERROR
    - **synchronized** blocks that call **notify(All)** where no state has been modified
    
    
#### Condition wait errors
- the proper usage of **Object.wait** or **Condition.await** when waiting on a condition queue
is 
    - to be called in a loop
    - WITH the appropriate lock held
    - after testing some state predicate. (Chap 14)
    

- COMMON ERROR    
    - Calling these methods without performing the three items above 


#### Misuse of Lock and Condition
- COMMON ERROR
    - typos:
        - using a **Lock** as the lock argument for a **synchronized** block 
        - calling **Condition.wait** instead of **Condition.await**
    
    
    NOTE: 
        The second case is almost always caught during testing because 
        Condition.wait will usually call an IllegalMonitorStateException.
        
        
#### Sleeping/Waiting while holding a lock
- Calling **Thread.sleep** w/ a lock held is a major liveness hazard
    - it prevents other Threads from making forward progress
    
    
- Calling **Condition.wait** or **Condition.await** w/ TWO locks held 
    - same as above -> Liveness hazard
    

#### Spin Loops
- Code that spins (busy wait) checking a field for an expected value
    - wastes CPU time
    - if the field is non-volatile, it isn't guaranteed to terminate. 
    
    
- BEST PRACTICES
    - Latches/Condition Waits are better techniques when waiting for state
    transitions to occur
    
    
## 12.4.3 Aspect Oriented Testing Techniques
Benefits to using AOP is that it requires no code changes
- easy technique to apply 
- can disclose subtle publication and thread-confinement errors

### AOP (Aspect Oriented Programming)
- increases modularity via separation of cross-cutting concerns
    - adds additional behavior to existing code ("an ADVICE") w/o modifying the code
    - separately specifies which code is modified via a "POINTCUT" specification
    - allows behaviors not central to biz logic to be added to a program w/o cluttering the code
        - (can be useful for platform capabilities like logging and telemetry)

### See AspectJ in Action Chapter 13
(Manning Download)


## 12.4.4. Profilers and Monitoring Tools
- most commercial profilers have thread support
    - PRO: provide useful insight into what a program is doing
    - CON: very intrusive, and can affect program timing and behavior
- most offer a display showing a timeline for each thread
    - color-coded states
    
    
- built-in JMX agent
    - limited thread monitoring
    - **ThreadInfo**
        - thread's current state
        - if thread is blocked
        - lock/condition queue on which a thread is blocked
        - no. of times thread has blocked waiting for a lock/notification
        - cumulative time spent waiting