# 11.1 Thinking About Performance

### What does it mean to improve performance? 
- doing more work w/ fewer resources
    - resources: 
        - memory, CPU, network b/w, I/O b/w, database requests, disk space, et al.
        
#### GOALS
- use processing resources more effectively
- enable program to exploit additional processing resources if they become available

##### Performance Monitoring
- Keep CPUs as busy as possible w/ USEFUL work
    - i.e. the cycles should be doing things that move threads/tasks FORWARD
    
    
    Threading offers a means to keep "CPUS" busier
    
    - to exploit this, we must decompose applications in a manner that ensures that there is always
    work to be done by an available processor. 
    
    - if we can't keep even 1 CPU busy 
        - adding more CPUs isn't going to help
        
    - if a program is compute bound, 
        - adding more MIGHT increase it's capacity

### What does "Bound" mean:
- When the performance of an activity/task is limited by the availability of a particular resource.
    - i.e. a CPU-bound activity is one limited by the availability of CPU cycles.
    
### Tradeoffs 
- Multi-Threading always introduces performance costs compared to single-threaded models
    - Overhead associated w/ coordinating between threads
        - locking
        - signaling
        - memory synchronization
    - increased context switching
    - thread creation/teardown
    - scheduling overhead
    
### Result
- Well designed concurrent apps
    - effective threading mechanisms
    - tradeoffs are more than covered by advantages
        - greater througput
        - greater responsiveness
        - greater capacity
- Poorly designed concurrent apps
    - might perform even worse than a comparable sequential one.
    
    
## 11.1.1 Performance vs. Scalability

### Application Performance 
- Measurements
    - How fast a given unit of work can be processed/acknowledged
        - Service Time
        - Latency
        - Efficiency
    - How much work can be performed w/ a given quantity of compute resources
        - throughput
        - scalability
        - capacity
        
        
### Scalability

    Describes the ability to improve THROUGHPUT or CAPACITY when additional computing 
    resources are added (CPU, memory, storage, I/O bw) 
    
    
### Tuning

#### For performance
- Do SAME work with LESS effort
- EX:
    - add caching to reuse previously computed results
    - swap O(n^2) algorithm w/ O(nlogn)

#### For scalability
- Do MORE work with MORE resources
- find ways to parallelize a problem so that we can take advantage of additional compute.
    - many methods to increase scalability INCREASE amount of work done in order to process each
    individual task 
        - i.e. there is a cost to parallelize
        
#### How Fast vs. How Much
- completely separate
- sometimes these have an inverse relationship
    
    NOTE: 
    
    many tricks to improve performance in a single-threaded application are bad for
    scalability
        
#### Three-Tier App Model (Example)
##### Description
- Presentation, Business and Persistence logic are separated and (might be) 
handled by different systems. 


##### First Unit of Work
- a Monolithic application will have far better performance than a well designed multitier impl 
distributed over multiple systems
    - No Network Latency
    - no abstraction tax (the cost of decomposing computational process into distinct
      abstract layers)
        - queuing overhead
        - coordination overhead
        - data copying
        
##### At Monolith's Processing Capacity
- prohibitively difficult (if possible at all) to increase capacity

##### Tradeoff
- In order to scale past a monolith's processing capacity we break it up into the 3 distributed tiers
- COST: 
    - longer service time
    - increased usage of compute resources 
    
### Best Practices
- The "How Much" characteristics of performance are usually of FAR greater concern for server applications
than the "How Fast" characteristics
    - scalability
    - throughput
    - capacity
    
    
- For Interactive apps (those that users will interact w/ directly)
    - latency tends to be the most important, because it ties to UX
    - we NEVER want users to wait for indiciations of progress or to wonder "what's going on here?"
    
    
## 11.1.2 Evaluating Performance Tradeoffs

### Example
- QuickSort is highly efficient for LARGE data sets
- Bubble sort is actually more efficient for small data sets. 

### Know What You are Changing
- If you are going to implement an efficient sort routine you need to know something about the size of
the data set it has to process
- Also need metrics to define what/how we are trying to optimize
    - average case time? 
    - worst cast time? 
    - predictability?
    
#### Premature Optimization
- Most optimizations are often premature because they are often performed before a clear set of
requirements are available

    
    Avoid premature optimization
    
### Define Context of Tradeoffs
- What are the tradeoffs of the optimization you are making? 
    - cost for cost (but different types, i.e. service time vs. memory consumption)
    - cost for safety? 

#### Common Tradeoffs
- Maintainability Cost
    - because the optimization is often a "clever" non-obvious set of code
    - harder to understand/maintain
    

- Violating Best Practices 
    - compromising good OOP principles
        - (i.e. breaking encapsulation)
    - tweaking thread priority in order to de-prioritize background threads in UI/EDT models.

- Greater risk of error
    - faster algorithms are more complicated
    - (also a side effect of violating best practices, maintainability etc.)
    
    
### Checklist/Questionnaire for Making Performance Decisions
(These decisions usually have multiple variables, and are HIGHLY situational)

- Define FASTER
- What conditions will the new approach actually be faster?
    - light/heavy load? 
    - large/small data sets? 
    - (Support these answers w/ measurements)
- How OFTEN are these conditions likely to occur? 
    - i.e. is this more "day-job" or more "corner case"?
    - (Support the answer w/ measurements)
- Can the code be reused in other situations w/ different conditions? 
    - (Can we increase the juice for the squeeze?)
    - i.e. can we find other uses for our optimization that increase the
    value of actually implementing it.
- What are the hidden costs? 
    - Dev time?
    - maint support risk
- Is the juice worth the squeeze??

### The History of Gotchas

    The quest for performance is probably the single greatest source of
    concurrency bugs
    
#### Example: "Slow Synchronization"
- synchronization was believed to be "too slow"
    - led to methods like double-checked locking that reduce synchronization, but are
    very error-prone
    - bad reputation leads to poor observation of good synchronization practices
    
#### Example: "Trading Safety for Performance, and getting neither"
- This is a common result when devs GUESS (i.e act on assumptions!)


### Best Practices
- Measure, don't guess
    - any and all performance tuning exercise should come w/ CONCRETE performance
    requirements
        - know when to tune
        - know when to STOP tuning
- Measure w/ a realistic configuration and load profile
- Measure before tuning, so we know what it looks like before hand. 
- Measure after tuning, so we know how close (or far) from our requirements we are.



- Don't pay the costs, if you don't get the benefits

#### Profiling
- Always use a profiling tool for
    - measuring performance
    - tracking down perf bottlenecks



    
