# 11.3 Costs Introduced By Threads

### Single-Threaded Apps
- no scheduling overhead
- no synchronization overhead
- don't need to use locks to preservice data structure consistency

### Multi-Threaded Apps
- Scheduling + Interthread coordination have performance costs

    
    For threads to offer an overall performance improvement...
    
        .. the benefits of PARALLELIZATION must outweigh the costs introduced by 
        concurrency

## 11.3.1 Context Switching

### Description
- If "no. of runnable threads" > "no. of cores"
    - eventually the CPU will preempt one thread so that another can use the CPU
    - this is the condition that leads to a context switch
- Context Switch
    - execution context of running thread must be saved
    - execution context of newly scheduled thread must be restored
    
    
### Cost of Context Switching
- Thread Scheduling
    - manipulation of SHARED data structures in OS and JVM
    - Since OS/JVM use the same CPU, any time spent by the CPU in the OS/JVM means less time available
    to the application. 
- Data required by newly scheduled thread is typically NOT in the local CPU cache at that time. 
    - context switches typically cause an uptick in cache misses while the cache is hydrated with the new
    data. 
    - this means that newly scheduled threads tend to run more slowly when they are first scheduled. 
    - (The cost here is that context switching causes a thread to operate at reduced performance for 
    a short period of time, in aggregate this adds up and can lead to contention latency)

#### Throughput vs Responsiveness Tradeoff
- MOST schedulers give each runnable thread a certain minimum time (even when other threads are waiting)
    - this helps distribute/amortize the cost of the context switch over more uninterrupted exec time
    - this is an adjustable tuning that buys tradeoff at the expense of responsiveness

---

- How JVM handles a thread that blocks due to waiting for a contended lock
    - JVM suspends thread
    - Thread is allowed to be switched out
   
- An app that does more blocking (i.e. blocking I/O, waiting for contended locks, waiting on vars), 
incurs MORE context switches than one that is CPU-bound
    - increased scheduling overhead
    - reduced throughput
    - (more blocking also means that threads aren't using their scheduling "time quantum")
    
#### "Actual Cost" (Rule of Thumb)
- Roughly equal to 5-10k clock cycles
    - (several microseconds on most current processors)
- Typical time quantum for modern systems is between 10 - 100 ms, so the overhead of a SINGLE context
switch is small relative to the time quantum. 
    - however, in aggregate this can get expensive at high amounts of traffic.
    

#### Remediation Steps
1.) use Non-blocking algorithms
2.) avoid context switching 

    Easier Said Than Done

---

### Tools

#### vmstat
- Unix tool that:
    - reports number of context switches
    - percentage of time spent in kernel
    
    
- TIPS
    - High Kernel Usage (> 10%) usually indicates heavy scheduling activity due to 
    blocking caused by I/O or lock contention

---

## 11.3.2 Memory Synchronization

### Sources of Cost to Synchronization
- visibility guarantees used by **synchronized** and **volatile** entail using memory barriers
    - MEMORY BARRIER
        - special instructions that can 
            - flush/invalidate cashes
            - flush hardware write buffers
            - stall execution pipelines
        - indirect perf consequences because they inhibit some compiler optimizations
            - (operations that use memory barriers, can't be reordered, meaning that it is 
            "built" as written)


- synchronization by one thread can impact the perf. of other threads.
    - sync creates traffic on the shared memory bus
        - (Shared memory bus has limited b/w and is shared across ALL cpus
    - if threads compete for sync b/w, then all threads using sync suffer. 
    
    
    NOTE:
        This is typically an argument AGAINST the use of Non-Blocking Agorithms unless a 
        backoff/backpressure algorithm is provided. 
        
        under HEAVY contention, Non-blocking Algorithms generate FAR more synchronization traffic
        than lock-based algoirthms. 

### Contended vs. Uncontended Synchronization
- **synchronized** is optimized for uncontended use case
    - fastest path for this use case ranges from 20 - 250 cycles for most systems.
    - non-zero, BUT side-effect of needed/, uncontended sync is rarely a significant factor in overall
    app performance
        - TRADEOFF
            - the cost of this performance comes with
                - sacrificing safety
                - hard to find bugs
        - FUCKING DO IT
- **volatile** is ONLY uncontended


    NOTE:
    
        The mechanism for handling uncontended synchronization is typically already very fast
        - Most modern JVMs automatically automatically provide optimizations that reduce/remove those
        costs. 
        
        Development optimizations should be focused primarily on circumstances where lock 
        contention actually occurs. 

### Remediation/Reduction

#### Older JVMS
- reduce cost of incidental sync
    - optimizing away locking that can be proven NEVER TO CONTEND
    - if a lock object is accessible only to the current thread, the JVM can optimize AWAY that
    lock acquisition because there is no way another thread can sync on the same lock
        - (See Example below)
        
        
        EXAMPLE: 
        
            synchronized(new Object()) {
                // do something. 
            }
            
---

#### "Sophisticated JVMs" can use Lock Elision 
- (HotSpot has had this since JDK 7)
- "Escape Analysis"
    - used to ID when a local object ref is never published to the heap and is therefore thread local. 
    

    EXAMPLE
    
        public String getStoogeNames() {
            
            List<String> stooges = new ArrayList<>();
            stooges.add("Moe");
            stooges.add("Larry");
            stooges.add("Curly");
            return stooges.toString();
        }

EXAMPLE Description
- The only reference to the List is the local variable "stooges"
    - stack-confined variables are automatically Thread Local. 
    

- Naive Execution
    - A lock would be acquired and released FOUR times, once for each add call, and then for toString.
    
    
- Smarter Compiler :) (Lock Elision)
    - the calls would be inlined.
    - since the internal state never escapes the inlined calls, the compiler ELIDES (or removes)
    the lock acquisitions
    
---
#### Lock Coarsening
- (Alternative to Escape Analysis)
- This is also a secondary optimization to Lock Elision, where the locks may not be removed for one or
another reason, but they can still be optimized. 


- adjacent **synchronized** blocks can be merged. 
    - citing the above example, the compiler performs a heuristic analysis of "relative cost of 
    synchronization" vs. "instructions inside **synchronized block**"
        - replaces 4 adjacent locks w/ 1 lock that wraps all 4 statements. 
        
       
       NOTE: 
        
       Smart dynamic compilers can determine that the example method above always returns the
       same string. 
       
       - after the first execution, the method can be recompiled to simply return the value 
       provided by that first execution. 
    
##### Several Benefits
- reduces synchronization overhead (often by a large factor)
- creates larger blocks of code/synchronization to work with, which typically enables further 
optimization.

---

## 11.3.3 Blocking
- According to David Bacon (and others) in 1998
    - "Uncontended synchronization can be handled ENTIRELY within the JVM"


- Contended Synchronization might require OS activity, which incurs an "add insult to injury" cost
above and beyond the cost of the contention.
    - When there is contention for a thread, the losing thread(s) must block. Period. 
    
### JVM Implementations of Blocking

#### Spin Waiting
- the JVM makes repeated attempts to acquire the lock until it succeeds
- preferable for SHORT waits

#### Suspending 
- the JVM suspends blocked threads via native calls made to the OS.
- preferable for LONG waits.


- Suspending threads happens because
    - it couldn't acquire a lock
    - it was blocked on a conditional wait
    - blocking I/O

##### Additional Costs
- 2 additional context switches
- OS and cache activity

    
    Blocked threads are 
    - switched out BEFORE the time quantum has expired
    - switched back in after lock/resource is available
    
- There is an added additional (haha) cost for blocking due to lock contention
    - when the thread that is holding the lock releases that lock, it has to ask the OS
    to resume the blocked thread. 


#### Which Is Better?
- depends on relationship between 
    - context switch overhead
    - (and) time until lock becomes available
    
#### How Does JVM Choose? 
- Most JVMs err on the side of caution and just suspend the threads. 
- SOME JVMs will adaptively choose between the two mechanisms based on profiling data of
past wait times.