# 11.5 Example: Comparing Map Performance

    This section provides an example of the previous section
    
 
### ConcurrentHashMap vs. synchronized HashMap
- in single-threaded apps, **ConcurrentHashMap** performs "slightly" better than
a synchronized **HashMap**


#### Concurrent Hash Map Characteristics
- in concurrent apps, it has MUCH better performance
    - **ConcurrentHashMap** assumes the most common operation is getting a value that already exists
    - it is optimized to provide highest performance + concurrency for "successful" **get()** operations
        - (no locking for MOST successful gets)
        - uses lock striping for ALL writes and the few read operations that do require locking
- MULTIPLE threads can access the Map Concurrently w/o blocking
    
    
#### synchronized HashMap Characteristics
- major scalability impediment
    - there is a single lock for the entire map
    - one thread per map at a time
        - as the contents of the map grows, the contention for the structure will likewise grow
        - this will lead to more threads blocking to access the structure. 

---
![Map Performance Comparison](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/PerformanceAndScalability_11/Images/Screen Shot 2020-12-21 at 3.44.46 PM.png)
### Explanation 
- **ConcurrentHashMap** and **ConcurrentSkipListMap** are thread-safe by design
- **HashMap** and **TreeMap** become thread-safe via **synchronized** wrapper.


#### Test Details:  
- N threads concurrently exec a tight loop that selects a random key from the collection and
attempts to retrieve the value corresponding to that key
    - if value is NOT present it is added to the collection w/ a probability of p = .6
    - if it is present, it is removed from the collection w/ a probability of p = .2
    
    
   
    This is a "Beat the shit out of the map" test, so it generates FAR more 
    contention per thread than a typical app. 
    
        - a real program does a bit more thread-local work in each iteration than 
        this test
    
#### Test Results:
- the concurrent collections scale well to large number of threads
    - throughput continues to improve as threads are added. 
    
    
- synchronized collections fall apart almost immediately
    - single-threaded performance is about the same as the concurrent versions
    - since synchronized collections have one lock per structure, the load becomes contended 
    immediately when we go from 1 to 2 threads. 
    
- This is a common example of a code whose scalability is limited by lock contention. 


#### Inferences
- When Contention is Low
    - time per operation is dominated by time spent doing work
        - throughput can IMPROVE as threads are added, because most of the time is "work time"


- When Contention is "Significant"
    - time per operation becomes dominated by CONTEXT SWITCH and SCHEDULING DELAYS
        - adding more threads doesn't improve throughput because we add more "interest" than we do
        "principle" (i.e. we add more overhead)
