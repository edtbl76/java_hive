# 8.1. Implicit Couplings Between Tasks and Execution Policies

Executor framework offers SOME flexibility to specify/modify 
execution policies, not all tasks are compatible w/ 
execution policies. 

Examples:

### Dependent Tasks
- Well Behaved tasks are INDEPENDENT
    - freely vary pool size/configuration
    - only impact on thread pool is performance
- Dependent tasks create implicit constraints on execution 
policy
    - Examples of dependencies
        - timing, results, side effects of other tasks
        
    
    Dependent Tasks can lead to liveness problems

### Tasks that exploit thread confinement
- Single-threaded executors make stronger/safer guarantees about
concurrency than thread pools
    - single-threadedness guarantees that tasks aren't exec'd
    concurrently
    - thread safety of task code might be relaxed. 
        - i.e. objects accessed w/o synchronization even if 
        resources aren't thread-safe
- This creates an implicit coupling between task and execution policy
    - (the task MIGHT require the executor to be single-threaded)
    -  It is possible to do the following:
        - ensure that tasks execute don't exe cute concurrently
        - provide ENOUGH synchronization so that the memory effects of one
        tasks are guaranteed to be visible to the next task. 
        - (these are the guarantees of **newSingle-ThreadExecutor**)
        
        
    Sequential Execution solves most of these problems... at the cost of performance. 

### Response-time-sensitive tasks
Tasks that are front in center to the user experience will have a degree of allowable
responsiveness before users get bored, sad, tired and/or angry. 
- GUI Apps/UI Threads. 
- Click and Get Coffee syndrome 


    When "number of tasks" > "number of resources to process those tasks" responsiveness will
    likely suffer, because those tasks have to wait in a queue (more than likely a BlockingQueue)
    
### **ThreadLocal** tasks.
**ThreadLocal** allows each thread to have its own private version of a variable

PROBLEM:
- Executors are free to reuse threads as they want.
    - standard **Executor** impls can 
        - reap idle threads when demand is low
        - spawn new threads when demand is high
        - respawn/replace worker threads if unchecked exception is caught
    
DO:
- Use **ThreadLocal** in pool threads ONLY if the thread-local value has a lifetime
bound by the task.

DONT:
- use **ThreadLocal** in pool threads to communicate values between tasks.

### Thread Pool Best Practices
- work best when tasks are
    - HOMOGENOUS
    - INDEPENDENT
- avoid mixing long and short running tasks
    - unless the thread pool is significantly large, it's possible that long-running tasks
    will clog the pool for the shorter tasks.
- submitting dependent tasks risks deadlock unless the thread pool is unbounded.
    - In order to prevent deadlock, the tasks can't be queued or rejected.

    
    NOTE: 
    - most tasks that are running in network-based server applications meet the basic guidelines
    for using thread pools. 
    
    
## 8.1.1 Thread Starvation Deadlock

### Cause
- Deadlocks are caused when tasks that depend on other tasks are executed in a thread pool

### Examples
- in a single-threaded Executor
    - a task that submits another task to the SAME executor and then waits for that task will
    ALWAYS deadlock
        - Task 2 waits on queue for first task to complete
        - Task 1 can't complete, because it needs the result of task 2.
- Can happen in large thread cpools
    - if all executing tasks are waiting for other tasks that are on the work queue
    
    
    Example: 
    
        Single-Threaded Deadlock. Don't do this
        
        public class ThreadDeadlock {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            
            public class RenderPageTask implements Callable<String> {
                public String call() throws Exception {
                    Future<String> header;
                    Future<String> footer;
                    
                    header = executor.submit(new LoadFileTask("header.html"));
                    footer = executor.submit(new LoadFileTask("footer.html"));
                    
                    String page = renderBody();
                    
                    // This is gonna deadlock -- the task is waiting for the result of
                    // the subtask
                    return header.get() + page + footer.get();
                      
            
                }
            }
    }
####  Explanation
- RenderPageTask 
    - creates 2 subtasks (gets fooder and header)
    - render page body
    - waits for results of header + footer <-- FUCKIN BROKEN
    
- this will always deadlock when used w/ a single-threaded Executor
- tasks coordinating amongst themselves w/ a barrier will also cause a deadlock if the
pool isn't large enough.

### Implicit Resource Boundaries
- Outside of just the size of the thread pool in use, there may be implied limits based on
external constraints from other resources. 
    - EXAMPLE: 
        - apps that use connection pools (like JDBC connections)
        - Even if I have a thread pool with 20 threads, if my connection pool only has 5 
        connections, the thread pool will behave as though there are only 5 threads because any
        tasks will block waiting for connections
        
## 8.1.2 Long-running tasks
- if tasks block for long periods of time, this can lead to responsiveness problems
    - mixing long-running tasks and short-running tasks can cause "clogging"
    - if there are only enough threads to handle the current long-running tasks, then many small
    tasks will be stalled in the queue waiting to be completed
        - responsiveness isn't a measure of how long it takes to execute 
        - responsiveness is a measure of how long it takes to return the result 

#### Mitigating The Clog
- use timed resources waits (instead of unbounded)
    - many blocking methods come w/ timed and untimed versions
        - **Thread.join**
        - **BlockingQueue.put**
        - **CountDownLatch.await**
        - **Selector.select**
    - on timeout
        - mark task failed/abort
        - (or) requeue for later execution
    - BENEFIT
        - each task is always making progress towards successful/failed completion
            - less idling
            - threads are freed up/dislodged more quikcly
            
    
    If thread pool is frequently full of blocked tasks
    - this is a sign of a sizing problem.