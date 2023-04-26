# 12.2 Testing For Performance

### Definition/Characteristics
- usually extended versions of functionality tests
    - performing basic unit/functional testing w/ perf testing ensures we aren't testing the perf of
    broken code. 
        - (True to a point, but if you've validated functionality and generated an artifact based on
        that validated code, you shouldn't have to repeat the same tests again.)
        

### Goals
- measure E2E perf  metrics for representative use cases
- tests should reflect how the objects are ACTUALLY used in the app

    
    EXAMPLES:
    
        in our BoundedBuffer case, BoundedBuffers are almost ALWAYS used in Producer-Consumer
        scenarios
        
            - a sensible measurement is the throughput of Producer -> Consumer data flow.
            
- select sizings "empirically" for various bounds
    - no. of threads
    - buffer capacities
    -etc. 
    
    
    
- in order for the previous bounds to support dynamic configuration, they should work well across
a wide range of systems and platform characteristics
    
        
        Empirically means "be means of observation/experience" 
    rather than theory or logic
    
    

### Challenges
- selecting reasonable use cases


## 12.1.1 - Extending PutTakeTest to Add Timing
(See Examples.tests.TimedPutTakeTest and Examples.BarrierTimer)
- this test exercises throughput/performance by 
    - adding code that will time the entire test run
    - divide total runtime by the no. of operations. 


- Since we are using CyclicBarrier to start/stop threads, this is a good convention to
extend a barrier action to measure the time as well

### What Can We Learn
- throughput of producer-consumer handoff operation for various combinations/configurations of parameters
- how the BoundedBuffer structure scales w/ different nos of threads. 
- how we might select the bound size

#### Results
![Results](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/TestingConcurrentPrograms_12/Images/Screen Shot 2020-12-30 at 3.08.48 PM.png)
- The results show
    - buffer size of 1 is obviously terrible
    - buffer size of 10 increases performance dramatically
    - buffer size > 10 offers diminishing returns
    
##### Warnings
- Our example is very ARTIFICIAL in how it simulates the app
    - producers do little work to generate item placed on queue
    - consumers do little work to retrieve the item or with the
    item to process it after the fact. 
    

- In real apps, the work done by producer and consumer is NONTRIVIAL
    - the effects of having too many threads can become very
    evident, because of the context switch costs

## 12.2.2 Comparing Multiple Algorithms

### BoundedBuffer vs. the Real Deal
- our lil' algo is no match for the big boys.
    - **ArrayBlockingQueue**
    - **LinkedBlockingQueue**


    NOTE: 
    
        java.util.concurrent algos have been selected and tuned to be as efficient as the
        creators of Java know how to make them while still offering a wide range of
        functionality. 
        
            - They CAN be outperformed if
            
                A.) You are a concurrency expert
                B.) You are willing to sacrifice some of the provided functionality
                



#### Why
- **put** and **take** methods in our impl each have multiple operations that
may encounter contention
    - acquire a semaphore
    - acquire a lock
    - release a semaphore
    
    
- Most other impl approaches have fewer (if any) points at which they might contend
w/ another thread.
    
    
### Comparison Results
![BoundedBuffer Comparisons](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/TestingConcurrentPrograms_12/Images/Screen Shot 2020-12-30 at 3.28.09 PM.png)


#### Explanation
- Interesting result is how much better the Linked queue performs
over the Array-based queue
    - Linked Queue
        - allocate a link node object for each insertion
        - (appears to do more work)
        - it DOES have more allocation and GC overhead
    - The Linked Queue allows more concurrent access by
    put() and take() than array-based queue 
        - because the best linked queue impls are doubly linked
        (i.e. they allow head and tail to be updated independently) 
    - since allocation is usually Thread Local
        - algos that can reduce contention by doing more 
        allocation usually scale better. 
        
        
        NOTE: This is another instance in which INTUITION
        based on traditional perf tuning runs counter to 
        what is needed for scalability.
        

## 12.2.3 Measuring Responsiveness

### Responsiveness
- how "long" it takes an individual action to complete. 
- an important trait of responsiveness is 'variance', which 
ultimately describes the min/max range of responsiveness
    - a smaller variance means a more predictable responsiveness
    and ultimately a greater UX. 
   
#### Quality of Service 
- Quality of Service is a set of traits/characteristics
    - Predictability
    - Reliability
    - variance
    - et al.
- PREDICTABILITY
    - "What percentage of operations will succeed in less than 100ms? "
    - is there a bar at which performance becomes unacceptable? 
- mathematical plausibility vs. engineering practicality


    EXAMPLE: 
    
        We may impl an algo that can potentially yield the fastest performance, but
        the variance is very broad. 
        
        It may be more VIABLE to provide a longer average service time if it decreases
        the variance. 
        
### Variance
- the best way to visualize variance in service time:
    - Histogram of task completion times
  
    
- slightly more involved than measuring averages
    - averages require measuring aggregate completion time and the no. of participants/tasks
    - variance requires us to keep track of per-task completion times. 
        - (Average doesn't demonstrate the range)
        
        
#### Timer Granularity
- an individual task might execute between timer ticks
    - distorts measurements, because they execute over a non-zero span of time that can't be 
    measured by the finest interval of the timer, making it appear as "no time"
    
    
    WORKAROUND:
    
        - measure runtime based on small batches of "put" and "take" operations
        
#### Variance Histogram 1: NonFair vs. Fair Semaphores
![Histogram](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/TestingConcurrentPrograms_12/Images/Screen Shot 2020-12-31 at 4.07.43 PM.png)
- This shows per-task completion times of a **TimedPutTakeTest** variant
    - buffer size = 10000
    - 256 concurrent tasks iterates only 1k times for nonfair (shaded) and fair (unshaded)
    semaphores
        - (Fair/NonFair queuing for locks and semaphores is shown in 13.3)
        
        
- The variance/range is much broader for nonfair (Default) semaphores. 
    - This can be reduced by forcing more "fairness" in concurrency control
    - (NOTE: this is very simple to do in our **BoundedBuffer** by init'ing our semaphores
    to fair mode)
    

- the fair semaphores have a reduced variance, but they also
have a GREATLY reduced throughput
    - (A realistic histogram w/ realistic tasks would
    show a more realistic, i.e. larger, reduction in 
    throughput)
    
    
    Remember: 
    
        small buffer sizes ==>  heavy context switching
                                poor throughput
                                
                                (even in nonfair mode)
                                
                - This happens because nearly ever operation
                requires a context switch.
             
    
#### Variance Histogram: Single-Threaded
![Histogram2](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/TestingConcurrentPrograms_12/Images/Screen Shot 2020-12-31 at 4.14.48 PM.png)
- We can measure the cost of "fairness" by rerunning the test w/ a buffer size of 1
- nonfair and fair semaphores have comparable performance


### Variance and Fairness Assumptions
- unless threads are constantly blocking due to tight synchronization 
    - nonfair semaphores == MUCH better throughput
    - fair semaphores == MUCH better variance
    
    
    NOTE: 
        
        Under most circumstances, the differences between the results of the two 
        scenarios are so vast, clients of Semaphore must decide which of the two
        factors to optimize for.
        
    
