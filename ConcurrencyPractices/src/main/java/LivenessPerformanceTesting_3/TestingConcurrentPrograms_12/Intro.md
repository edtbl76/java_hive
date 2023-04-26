# Chapter 12 - Testing Concurrent Programs

## Outliers To Consider When Testing Concurrent Programs
- Concurrency has a degree of nondeteriminism that sequential programs don't
    - increases no. of potential interactions
    - increases no. of failure modes 
    - more planning/analysis
    
## Categories

### Safety: "nothing bad ever happens"
- verify that a class's behavior conforms to its specification
    - test/validate invariants
    
    
        EXAMPLE: 
        
            Comparing Cached Size of Collection to actual number of elements
            
            - In a single-threaded program, this is easy because the list contents can
            change while properties are being tested
            
            - In concurrent program, there will be race conditions because one thread could
            be updating contents while we read the size/count field. 
            
                -  This requires some exclusive locking against the collection forcing other
                threads to block while we execute the tests in a single-threaded way.
    
### Liveness: "something good eventually happens"
- tests of "progress" and "non-progress"
    - these are hard
        - how do you verify blocking vs. running slowly? 
        - how do you test that a program does NOT deadlock? 
        - how long do you wait before we declare that something has failed? 
        
#### Performance (subcategory/relative of Liveness)
- Throughput
    - the rate at which a set of concurrent tasks is completed

    
- Responsiveness (Latency)
    - the delay between a request for and completion of some action 


- Scalability
    - the improvement in throughput (or lack thereof) as more resources (usually CPUs)
    are made available




---
### Heisenbugs 
- tongue-in-cheek term that describes bugs that disappear when you add debugging/test code. 
    - this tends to happen due to timing/synchronization artifacts introduced by test code