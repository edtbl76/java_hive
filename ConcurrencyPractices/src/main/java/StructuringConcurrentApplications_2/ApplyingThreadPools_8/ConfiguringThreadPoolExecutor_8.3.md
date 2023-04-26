# 8.3 Configuring **ThreadPoolExecutor**

### ThreadPoolExecutor
Provides base impl for executors returned by the following **Executor** factories
- **newCachedThreadPool**
- **newFixedThreadPool**
- **newScheduledThreadExecutor**

It is a customizable/flexible thread pool implementation
- default execution policy can be customized by instantiating a TPE through its constructor
- (Consult source for execution policies for each of the default configurations)


    EXAMPLE: General Constructor for ThreadPoolExecutor
    
        public ThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler
        ) { ... }
            

## 8.3.1 Thread Creation/Teardown
Thread creation/teardown is governed by the following 3 traits

### 1. Core Pool Size 
This is the "target" pool size. 
- The thread pool impl attempts to maintain this size regardless of whether or not 
there are any tasks enqueued. 

    
    NOTE: The impl will NOT exceed this size unless the work queue is full and tasks are
    blocking. 
    
    It can be tempting to set this to 0. 
    - this would force worker threads to be torn down so that the thread pool maintains 
    "0" as a size when the workload steady state might be 0. 
    
        The downside here is in the note above:
            - tasks are going to sit on the queue until it fills up. If the steady state is low, 
            it is possible that work will sit untouched until there is enough work for the 
            thread pool to kick in. 
         
  

#### At ThreadPoolExecutor Startup
The initial hydration of the thread pool is done lazily by default. 
- i.e. the pool is empty, and core threads are created as tasks are submitted. 
- (threads can be created eagerly by setting **prestartAllCoreThreads** )

### 2. Maximum Pool Size
This is the upper bound for the pool.
- This sets a ceiling on the number of threads to prevent the side effects associated with
thread pools that get too big. 
- threads whose "idle time" > "keep alive time" are candidates for REAPING, and CAN be
terminated if "current pool size" > "pool size"

### 3. Keep Alive Time
- The keep alive time is just a temporal weighting mechanism. 

    
    Thread Pool Cycle
    
        - ThreadPoolExecutor starts
        - (assuming prestartAllCoreThreads is unset), threads are created and allocated as
        tasks are submitted until the CORE SIZE is reached. 
        - new threads aren't allocated unless the work queue is full. 
        - Once the current pool size is larger than core size, ThreadPoolExecutor needs a mechanism
        to help maintain the initial CORE SIZE
        
        The Keep Alive time is used as a weighting mechanism to decide which threads should be
        reaped to reduce the size back down to the CORE.

    

### Benefits/Tradeoffs
- Tuning these values can make reaping/creation more aggressive based on the characteristics
and steady state load of your tasks.

### Default Impls

#### newFixedThreadPool
- Has an effective "infinite" timeout
    - Requested Pool Size = Core Pool Size = Max Pool Size

#### newCachedThreadPool
- "infinitely expandable thread pool that contracts when demand increases"
    - Max Pool Size = **Integer.MAX_VALUE**
    - Core Pool Size = 0
    

- only limitation is available system resources
    - (NOTE: cached thread pools remove any threads that are idle for 1 minute.)
    
##### Synchronous Handoff
- technique used by **newCachedThreadPool** to queue new tasks
- an item may be queued if and only if another thread takes that item at the same time. 
    - (The SynchronousQueue can't hold any tasks)
    - If an idle thread is available, the task producer schedules the task to that thread.
    - else, the executor CREATES A NEW THREAD. 

##### Use Cases
- best use case for newCachedThreadPool is when we're dealing with a "reasonable" no. of
"short-lived tasks."


- AVOID using **newCachedThreadPool** when execution time is unpredictable
    - i.e. I/O
 
## 8.3.2 Managing Queued Tasks

### Bounded Thread Pools
- limit the number of tasks that can be executed concurrently

#### Single-Threaded Executors
- Special Case Bounded Thread Pool
- Guarantees that no task executes concurrently
    - This means that thread safety is achieved through THREAD CONFINEMENT

### Unbounded Thread Creation
- leads to instability

#### Fixed-Size ThreadPool (Bounded)
- provides a "partial solution" to the instability issues associated w/ unbounded thread creation
- OUTSTANDING PROBLEMS IT DOESN'T FIX
    - if "arrival rate" of new requests exceeds the rate at which they can be handled, requests
    will still backup in the queue
    - WITHOUT THREADPOOL
       - requests back up as threads waiting for CPU resources
    - WITH THREAD POOL
       - wait in a queue of **Runnables** managed by the **Executor**
       - (Representing waiting tasks like this is MUCH cheaper than with a thread)
- regardless of the representation or unbounded/bounded thread management
    - Resource Exhaustion can occur if the rate of request exceeds the rate of completion. 
    
### Task Queuing
Example: 
- **ThreadPoolExecutor** allows the use of **BlockingQueue** to buffer tasks waiting for execution

#### Flow Control/ BackPressure
- Buffering/Queueing data as it comes in is a VERY short term solution.
    - by offering a buffer/queue as an intermediary, the rate of consumption depends more on
    how fast the consumers can take work from the queue  
- If the rate of request is too high, there needs to be a mechanism to throttle or stop
incoming requests until we can scale up to handle the load or until we catch up. 

#### Task Queueing Types

    Queuing Choice interacts w/ other Thread Pool configuration parameters (like pool size) 
  
##### Unbounded Queue
- unbounded version of **LinkedBlockingQueue**
    - default queue for **newFixedThreadPool** and **newSingleThreadExecutor**
    - tasks queue up only if all workers are busy
    - if tasks arrive faster than they can be exec'd, the queue can grow w/o bound
    
##### Bounded Queue
- Examples
    - **ArrayBlockingQueue**
    - bounded version of **LinkedBlockingQueue**
    - **PriorityBlockingQueue**
- more stable, by some prevention of resource exhaustion
- "Saturation Policies"
    - these address the question of what to do w/ new tasks when the queue is full
- Tuning:
    - the queue and pool sizes must be tuned together. 
    
    
    A large work queue w/ a small thread pool can:
        - reduce memory usage
        - reduce CPU usage
        - reduce context switching....
        
        w/ the cost of contstraining throughput

##### Synchronous Handoff
- This strategy is a way to bypass queueing
    - producers submit tasks directly to the consumer threads. 
- **SynchronousQueue** 
    - NOT ACTUALLY A QUEUE!
    - default backing queue used by **newCachedThreadPool** factory
    - this is a mechanism for managing handoffs between threads. 
- How It Works
    - in order to "enqueue" a task on a **SynchronousQueue** there must already be a thread
    waiting to accept a new task
        - if no thread is waiting AND pool size < max size
            - **ThreadPoolExecutor** spawns a new thread to accept the task
        - else task is rejected based on the details of the "saturation policy"


    BENEFITS of direct handoff
    - more efficient than the indirection of producer -> queue -> consumer 
    
    PRACTICALITY
    - use SynchronousQueue only in unbounded pools, or if rejecting a lot of tasks is
    acceptable.
    
    
### Task Ordering
#### Preserving Ordering
- Use FIFO queues
    - starts tasks in the order they arrive
    - Examples
        - **LinkedBlockingQueue** or **ArrayBlockingQueue**
- For more control than arrival order:
    - use **PriorityBlockingQueue**
    - tasks can by prioritized by
        - natural order if tasks implement **Comparable**
        - or by **Comparator**
  

### Table  
        
| Pool | Default Queue | Type | Order |
| --- | --- | --- | --- |
| newFixedThreadPool | LinkedBlockingQueue (Unbounded) | Unbounded | FIFO |
| newSingleThreadExecutor | LinkedBlockingQueue (Unbounded) | Unbounded | FIFO |
| | ArrayBlockingQueue | Bounded | FIFO |
| | LinkedBlockingQueue (Bounded) | Bounded | FIFO |
| | PriorityBlockingQueue | Bounded | Comparator/Comparable | 
| newCachedThreadPool | SynchronousQueue | Synchronous Handoff | |

### Best Practices
- **newCachedThreadPool** factory is a good "default" choice for an **Executor**
    - better queue performance than a fixed thread pool 
    - (**SynchronousQueue** uses a non-blocking algorithm that performs much better than 
    **LinkedBlockingQueue**)
- Bounding thread pool or work queue is only suitable for INDEPENDENT tasks
    - (bounded thread pools OR work queues that submit dependent tasks lead to 
    thread starvation deadlock)]
- DEPENDENT tasks should use unbounded pool configuration
    - EX: **newCachedThreadPool**
    
    
    Alternative Solution for DEPENDENT TASKS
    
        - bounded thread pool
        - SynchronousQueue
        - caller-runs saturation policy
        
        
## 8.3.3 Saturation Policies
A saturation policy is a set of remediation steps to be executed when
- a bounded queue has become full
- a task has been submitted to an **Executor** that has been shut down. 

Saturation policies are a part of the **ThreadPoolExecutor** and can be accessed through
**setRejectedExecutionHandler**
- there are several impls of **RejectedExecutionhandler**, each w/ a different saturation policy
    - **AbortPolicy**
        - default saturation policy
        - causes **execute** to throw unchecked **RejectedExecutionException**
        - caller's responsibility to catch the exception and manage queue overflow handling
        on their own
    - **CallerRunsPolicy**
        - see below
    - **DiscardPolicy**
        - silently discards NEW tasks if the queue is full. 
        - sometimes called DROP, because the task can't be enqueued.
    - **DiscardOldestPolicy**
        - discards the task at the tail of queue (i.e. the one to be next executed)
        - retries to submit new task again 
            - assumes that discarding the old task freed up space on the queue for the
            submit to succeed. 
        - NOTE: DO NOT USE THIS WITH PRIORITY QUEUES
            - the oldest task in a priority queue is the HIGHEST PRIORITY task. 
    
### Caller Runs Policy
- attempts to slow down flow of tasks by pushing some of the work back to the caller. 
    - tasks aren't executed in a pool thread, but rather in the caller thread that
    calls **execute**
- SIDE EFFECT:
    - once the work queue fills up, the caller-runs policy will assign the next task to be
    executed on the calling thread itself. 
    - the calling thread is usually main/event-loop/dispatch, which means that if it is
    tied up doing work
        - tasks can't submitted while the task is being computed, creating a "breather" for the
        consumers/workers
    - accept() can also not be called during this time, so incoming requests will queue up 
    in the TCP layer instead of at the application layer. 
        - if overload continues, TCP layer will start dropping connections
    - once connections are dropped, the client will start to see the effect. 
    
    
    GRACEFUL DEGRADATION
        - the side effects of congestion/overloading are gradually pushed outward
        
        Pool Threads are fucked? - push it back to the caller.
        Caller fucked? - push it back to TCP stack
        TCP Stack? - push it back to client
        Client annoyed? - stop sending me shit to do!
        
        
### Selecting Saturation Policy

    Example
    
        Creating Fixed Thread pool w/ Bounded Queue and CallerRuns Sat Policy
        
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                N_THREADS,
                N_THREADS,
                OL, 
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(CAPACITY)
            );
            
            executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());

- Setting Sat policy is done after the **Executor** is instantiated by invoking 
**setRejectedExecutionHandler**

#### Blocking **execute**
- There is no default Saturation Policy that will make **execute** block when the work queue is full.
- see ExampleCode.BoundedExecutor for a workaround
    - uses a **Semaphore** to bound the task injection rate
    - uses an unbounded queue
        - (No reason to bound both queue size AND injection rate)
    - sets semaphore bound to be equal to 
        - pool size + "number of queued task you want to allow"
        
        
## 8.3.4 Thread Factory
Design pattern extended to thread pool concept when the pool needs to 
create new threads. 
- **ThreadFactory** has a single method (**newThread**)
    - creates a single thread when the thread pool needs to spawn new 
    threads
    
    
    public interface ThreadFactory {
        Thread newThread(Runnable runnable);
    }

### Default vs. Customizations
- Default 
    - creates new non-daemon thread w/ no special configuration
- Specifying a Thread Factory
    - allows customization of pool threads
    
### Why Customize
- specify **UncaughtExceptionHandler** for pool threads
- instantiate a customized instance of **Thread**
    - (i.e. debug logging thread)
- modifying thread priority
    - (BAD IDEA)
- set daemon status
    - (BAD IDEA) 
- give threads more meaningful names
    - makes interpretation of thread-dumps and error logs much easier
    when the threads are named. 

#### Example
- (See ExampleCode.MyThreadFactory)
    - creates a new MyAppThread
        - provides a pool-specific name in the constructor so that
        threads from each pool can be distinguished in thread/error dumps.
    - Allows MyAppThread to be used in any area of the application to 
    take advantage of the debug logging features.
- (See ExampleCode.MyAppThread) 
    - all of the debug features happen on the thread object itself. 
    - constructor is overloaded so that it can support both the
    standard thread factory and the custom thread factory. 
    - (This allows any other code to take advantage of the debug
    logging w/o being forced to use the custom ThreadFactory)
    
### PrivilegedThreadFactory
- The Default permissions for threads created by a thread pool
    - inherited from whatever client happens to be calling
    **execute()/submit()** at the time the new thread is needed. 
    - PROBLEM: 
        - can create some weird/bad security related exceptions
- **Executors.privilegedThreadFactory** is a special factory for
creating pool threads that have the same following attributes as
the thread that creates the **privilegedThreadFactory**
    - permissions
    - **AccessControlContext**
    - **contextClassLoader** 

#### Use Case
- For applications that use "security policies" to grant permissions
to given codebases. 

##  8.3.5 Customizing ThreadPoolExecutor after Construction
- most of the constructor/factory parameters for instantiation of
**ThreadPoolExecutor**s are available via setters
    - (The Fab Five)
    - core thread pool size
    - maximum thread pool size
    - keep-alive type
    - Thread Factory
    - rejected execution handler. 
- if **Executor** is created through one of the **Executors** factory methods
you can cast the result to **ThreadPoolExecutor** to access the setters
    - (this works for all of the factories but **newSingleThreadExecutor**)
    
    
    Example of Modifying an Executor created w/ the standard factories
    
    
        ExecutorService executor = Executors.newCachedThreadPool();
        if (executor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executor).setCorePoolSize(10);
        } else {
            throw new AssertionError("Whatevers");
        }
        
### Executors.unconfigurableExecutorService()
- This is a special factory method that takes an existing **ExecutorService** and wraps it w/ a new
one that only exposes the methods of **ExecutorService**
    - prevents the executor from being configured further
    - (Kind of like a read-only impl) 
    
#### newSingleThreadExecutor
- returns an **ExecutorService** wrapped in the same manner 
- its actually implemented as a thread pool with only one permanent thread
- (also promises never to execute tasks concurrently)

#### Other Use Cases
- you can use this technique w/ custom executors to prevent the execution policy from being 
modified by other code.
- NOTE: This is also worth considering as part of the security design 

