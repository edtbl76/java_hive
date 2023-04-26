# 11.2 Amdahl's Law
Describes how much a program can theoretically be sped up by scaling out/horizontally (adding compute!)
based on the proportion of parallelizable and serial components of that program.

### Problem Comparisons
- Some problems can be solved faster w/ more resources
    - (i.e. more workers available to harvest a crop, will decrease the amount of time it takes to
    harvest said crop, assuming the workers aren't lazy)
- Some problems are "fundamentally serial"
    - no increase in the number of workers will make the crops GROW faster. 
    
#### Is Our Problem Parallelizable?
- if a problem can't be decomposed into parallel components, then we can't effectively exploit
the power of multiple processors or threads. 


### The Law, and the Mix.
- Most concurrent programs are similar to the example above
    - they consist of a mix of parallelizable and serial pieces


Amdahl's Law    
![Amdahl's Law](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/PerformanceAndScalability_11/Images/Screen Shot 2020-12-13 at 12.55.31 AM.png)

    F = fraction of the calculation executed serially
    N = no. of Processors on machine that program is being run on
  
    Characteristics
    
    1.)  As N approaches infinity, max speedup converges to 1/F
    
        EX: 
            - a program where 50% of the execution is serial, 
            can only be sped up by a factor of 2
            
                (regardless of the number of CPUs/cores)
                
            - a program where 10% of the execution is serial, 
            can, at most,  be sped up by a factor of 10
            
                (also, regardless of the no. of cores/CPUS)
                
     2.) Quantification of the efficiency cost of serialization
     
        EX:
            - With 10 CPUs, a program w/ 10% serialization,
            can only be sped up by a factor of 5.3x (53% utilization)
            
            - w/ 100 CPUs, it only increases up to 9.2x (at 9% utilization)
        
            
![Utilization x CPU Count](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/PerformanceAndScalability_11/Images/Screen Shot 2020-12-13 at 1.02.39 AM.png)


### Identify Sources of Serialization
(See Examples.WorkerThread for code example)

#### Example 1 - Shared Work Queue Problem
- N threads execute some independent tasks on the WorkerThread in the
cited example
    - tasks are fetched from a shared work queue
    - task is processed INDEPENDENTLY
        - This means the tasks don't depend on side effects/results of other tasks.    

##### How Does It Scale
- Initial Characteristics Appear Parallelizable
    - Tasks don't wait for each other
    - an increase in CPU resources available suggests that more tasks can be
processed concurrently


- WRONG: Serial Components Exist
    - The work queue is shared by ALL worker threads
    - Fetching a task from the work queue happens serially
    - requires synchronization
        - if locking is used to guard the state of the queue:
            - while a thread dequeues its task, other threads have to wait to dequeue theirs.

- That is the processing time of a single task? 
    - time to execute the task **Runnable**
    - "+" the time to dequeue the task from the shared workqueue
        - This includes the amount of time blocked waiting to acquire locks
        
     
    NOTE: 
        
        BestPractices: 
        
        Use LinkedBlockingQueue rather than a synchronized LinkedList
        
        - the dequeue operation above will be faster with the former data structure,
        because it was impl'd w/ a more scalable algorithm.
        
        Unfortunately, this is a "nickel and dime" optimization:
        
            Using ANY SHARED DATA STRUCTURE in a concurrent application introduces an 
            element of serialization into the app.
 
#### Example 2 : Result Handling
- This is a very common source of serialization
- Most computations create some sort of result or side effect
    - If it isn't, is it dead code?


- Runnables don't provide an explicit way to handle results, so tasks must 
induce some side effect
    - write to a log file, put results into a data structure etc. 
    - Log Files/Result Containers are usually shared across multiple threads (<= SERIAL)
    - even if each thread maintains its OWN data structure for results that are merged
    after all tasks are performed:
        - the final merge is a source of SERIALIZATION
        

    All Concurrent Applications have some sources of serialization. 
    
        If you think yours is special. Look Again.
        
## 11.2.1 - Serialization Hidden in Frameworks (Example) 

### Isolating Hidden Serialization
- Compare throughput as threads are added
    - infer differences in serialization based on OBSERVED differences in scalability

![Isolating Hidden Serialization](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/PerformanceAndScalability_11/Images/Screen Shot 2020-12-13 at 9.48.04 PM.png)
DIAGRAM DESCRIPTION
- simple app: multiple threads repeatedly remove an element from a shared Queue and process it
    - (See Examples.WorkerThread)
    - processing is thread-local computation only
    - if thread finds an empty queue, it puts a batch of new work on the queue so
    that other threads have something to do on their next iteration
    
    
    Accessing Shared Queue = SERIALIZATION
    
    Processing = 100% PARALLELIZABLE (No Shared Data) 
    
#### Curve Comparison
- 2 Thread-Safe Queue implementations
    - **LinkedList** wrapped w/ **synchronizedList**
    - **ConcurrentLinkedQueue**
- Each "run" represents the same amount of work
    - different queue impls = BIG impact on scalability.
    - **ConcurrentLinkedQueue**
        - throughput increases until it is equal to the number of CPUs, then levels out
    - **LinkedList** (synchronized)
        - throughput increases until it reaches ~3 threads 
            - this is the point at which synchronization overhead starts to impact performance
        - by 4-5, it doesn't just level off, it starts to dip
            - contention is so high, that every access to the queue lock is 
            contended. 
            - throughput is DOMINATED by context switching

##### WHY
- differing degrees of serialization between the two queue impls
    - synchronized **LinkedList** 
        - guards ENTIRE queue state w/ a single lock that is held for the duration of 
        the **offer** or **remove** call
    - **ConcurrentLinkedQueue**
        - uses a "sophisticated" non-blocking queue algorithm 
            - uses atomic references to update individual link pointers
        - (See 15.4.2)

    
    The difference has to do with the granularity of our concurrency. 
    
        The synchronized LinkedList serializes the entire insertion/removal 
        to the queue
        
        The ConcurrentLinkedQueue only serializes updates to individual pointers. 
        - smaller scope
        - fewer operations acquire locks. 
        - less time is spent switching between threads!
        
## 11.2.2. Applying Amdhal's Law Qualitatively

### Apparent Limitation

    Amdahl's law QUANTIFIES the possible performance increase (in terms of throughput) associated with 
    an increase in computing resources...
    
        ... if we can accurately estimate the faction of execution that is serialized. 
        

### Applying the law w/o measuring serialization
- Mental Models are often based around the number of cores that are "currently affordable" 
    - EX: whatever the average number of procs are on an AWS EC2 instance. (2? 4? 8? 16?) etc.
    - it is LIKELY that many current concurrent-friendly algorithms were optimized based on these 
    commonly available CPU configurations
  
    
- Qualitative evaluation of concurrent implementations involves "thinking ahead". 
    - Thinking about a system designed for 8 CPUS, in terms of 10x or 100x CPUs can provide some
    insight in terms of scalability problems. 
    - algorithms that seem scalable on TODAY's "standard" CPU config may have hidden scalability
    bottlenecks that we simply haven't found yet.