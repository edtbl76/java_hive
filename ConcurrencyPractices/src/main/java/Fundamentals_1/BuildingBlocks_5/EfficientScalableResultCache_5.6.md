# 5.6 Building an Efficient, Scalable Result Cache

### Caching
- reusing the results of previous compute
    - reduce latency
    - increase throughput
    - cost of additional memory
- naive impl
    - turns perf. bottleneck into scalability bottleneck.
    

### Memoization
(See CachingExamples.Memoization1)
- Memoization (as shown by Memoizer1)
    - provides a Wrapper around a Function that performs computation
    - remembers the results of previous computations
    - encapsulates the caching process (i.e. remembering the results)
    
#### Memoizer1
- uses HashMap to store results of previous computations
- compute()
    - checks if desired result is already cached
        - if yes
            - return precomputed value
        - else
            - result is computed
            - result is cached
            - result is returned
      
##### Thread-Safety Problem
- PROBLEM
    - HashMap isn't a thread-safe class. 
- SOLUTION
    - in order to prevent multiple threads accessing HashMap at the
    same time:
        - compute() is synchronized.
        
##### Scalability Problem
- PROBLEM:
    - synchronizing the entire method only allows a single thread to
    execute that method at a time
    - if another thread is busy computing results, the threads calling
    compute() may be blocked for a lon gitme. 
    - if MULTIPLE threads are queued up waiting to compute new results,
    this solution might be slower than it would have WITHOUT caching.
    
![](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/Fundamentals_1/BuildingBlocks_5/CachingExamples/Screen Shot 2020-11-02 at 2.39.10 PM.png)

#### Memoizer2
This attempts to solve the scalability side effect introduced by 
Memoizer1 by replacing HashMap w/ ConcurrentHashMap. 
- ConcurrentHashMap = thread-safe
- no need to synchronize when accessing backing map
    - eliminates serialization needed by synchronizing compute()
- multiple threads can use it concurrently


PROBLEM
- window of "vulnerability"
    - 2 threads calling compute at exactly the same time might 
    compute the same value
    - for memoization
        - this is inefficient because the purpose of the cache is to
        prevent the same data from being calculated multiple times
    - for general-purpose caching
        - this is very bad. 
        - many object caches are supposed to provide once (and only once)
        initialization
            - this is a safety risk
- if one thread starts a long-running task
    - other threads don't have visibility that this computation is
    in progress. 
    - the other threads might start the SAME task.
    
![]( /Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/Fundamentals_1/BuildingBlocks_5/CachingExamples/Screen Shot 2020-11-02 at 3.00.02 PM.png)

#### Memoizer3
(See Memoizer3)
This attempts to solve the potential race condition between the 
two threads by using a FutureTask
- FutureTask represents a result that may or may not have already been
completed. 
    - if the result is present, it returns it immediately
    - otherwise it blocks until the result is available and THEN
    returns it.
    
The output value of the ConcurrentHashMap is changed from V to Future<V>
- this essentially forces a pre-flight check to ensure that the 
calculation hasn't STARTED. 
    - (NOTE memoizer2, checks if the calculation is FINISHED)
    - if the task is unstarted
        - FutureTask is created
        - registered in the map
        - task is started
    - else blocks until existing comp is done
- calling Future.get() ABSTRACTS the above process. 
    - the caller will receive the result (or a timeout/error) w/o
    direct knowledge of whether or not the task was immedately
    available or it had to wait. 
    
    
##### GOOD
- good concurrency (mostly derived from ConcurrenthashMap)
- known results are returned immediately
- if task is in process, new threads wait for result

##### OUTSTANDING PROBLEMS
- There is STILL a small window of vulnerability
    - the "if" statement is a non-atomic "check-then-act" sequence
    - it is possible for 2 threads to call compute() w/ the same
    value at the same time
    - both threads will see that the cache doesn't contain the
    desired value
        - and both will start to perform the task.
- Compound Actions performed against backing map(s) can't be made
atomic using locking. 
![](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/Fundamentals_1/BuildingBlocks_5/CachingExamples/Screen Shot 2020-11-02 at 3.17.58 PM.png)

#### Final Memoizer
(see FinalMemoizer)
- uses the putIfAbsent() method from ConcurrentMap to solve the 
atomicity problem from Memoizer3
- avoids cache pollution by invalidating (evicting) a future from
the cache if it detects that the task was cancelled
    - (NOTE: in some cases it is desirable to remove the Future
    upon detecting a RuntimeException if the computation might
    succeed on a future attempt.)
   

##### Cache Pollution
Scenario where if  a computation is cancelled/fails, then future
attempts to compute the result will also indicate failure or 
cancellation.
- This is possible when caching a FUTURE rather than a VALUE. 

##### Cache Expiration
This isn't addressed by the Final Memoizer, but it can be impl'd
by using a subclass of FutureTask that associates an expiry time
w/ each result
- the class would then need to periodically scan for expired
entries

##### Cache Eviction
This is the process of aging out entries (based on some algo) in 
order to make room for new ones

