# THREAD POOLS
CONTEXT:
- in concurrent applications, we create some Runnable objects, and then create 
corresponding THREAD objects to execute the Runnables.

THREAD POOLS
- the primary reason that you create thread pools is because creating threads is an 
EXPENSIVE operation in java. 
    - if we had to create a thread on demand for each task, app performance would go to crap. 
    
## THREAD POOLS IN JAVA
- A Thread Pool is a collection of pre-initialized threads. 
    - USUALLY the size is fixed, but this isn't required. 
    - if (# of tasks) > (# of threads), then the tasks will wait in a Queue (FIFO)  
        - NOTE: a BlockingQueue is the most common here. 
    
- on Thread Execution
    - it can pick up a NEW task from the queue and execute it. 
    
- when Queue is Empty
    - threads wait() for more tasks to arrive in the thread pool
    
## ThreadPoolExecutor
- The Executor framework
    - Executor Interface + ExecutorService SubInterface
    - ThreadPoolExecutor (implements Executor, ExecutorService)
    
- ThreadPoolExecutor is responsible for SEPARATING task CREATION from EXECUTION
    - responsible for (specifically)
        - execution, instantiation and running of Runnable objects w/ the necessary threads.
        
    - goes the extra mile, by boosting performance via a pool of threads. 
        - when tasks are sent to a ThreadPoolExecutor, it tries to use a pooled thread for
        execution of task. 
            - (avoid expensive spawning of threads.)

## CREATING ThreadPoolExecutors

### FIXED THREAD POOL EXECUTOR
Creates a thread pool that can reuse a FIXED NUMBER of threads to execute any number of tasks. 
- if tasks exceed number of threads, they'll wait in (blocking) queue until a thread
    becomes available
- MOST COMMONLY USED ThreadPoolExecutor

    
    EX:
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        
### CACHED THREAD POOL EXECUTOR
Creates a thread pool that creates new threads as needed, but will reuse previously 
built threads when available. 
- NOT RECOMMENDED FOR LONG RUNNING TASKS
    - this can cause system crashes because as threads are used up and executing the
    long running tasks, the TPE will start spawning new threads, resulting in a 
    resource bound system. 
    

    EX:
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
### SCHEDULED THREAD POOL EXECUTOR
"Cron" TPE. This creates a thread pool that schedules commands to run after an initial delay
or to execute periodically


    EX:
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        

### SINGLE Thread Pool Executor
Creates a single thread to execute ALL tasks. This is useful if you only have a single
task to execute, or you have a need to serialize. 

    EX:
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        
### WORK STEALING TPE
Creates a thread pool that maintains enough threads to support GIVEN LEVEL OF PARALLELISM.
- We are defining parallelism as:
    - The MAX # of THREADS which will be used to exec a given task, at a single point in 
    time in multi-proc systems. 
    
    
    EX: 
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newWorkStealingPool(4);


# BEST PRACTICES/CRITICAL POINTS
ThreadPoolExecutor constructors vs. Executors class. 
- it is recommended to use the Executors class, because it obfuscates some of the complexity
of the TPE constructors

Avoid Cached Thread Pool When Possible. 
- it is very easy to overload a system w/ this pool. 

Terminating Executors
- executors are designed to run forever. 
    - they are either executing tasks or waiting for new ones. 
- This means that we have to determine when DONE is DONE and explicitly tell the executor
to stop waiting for more work, it's time to turn out the lights and crack a beer. 

Shutdown()
- this is the most common (and easiest) way to tell executors that its time to quit. 
    - by default, shutdown () will complete any pending tasks before terminating.
    - any attempt to add a new task results in a RejectedExecutionException
    - (My analogy is the closure of a checkout line in the SuperMarket.)
    
Getting Status about the pool/executor
- getPoolSize() - max # of threads
- getActiveCount() - # of threads
- getCompletedTaskCount() - # of completed tasks.
- getLargestPoolSize() (gets max number of threads in use at a given time.)

Shutdown(and More)
- shutdownNow()
    - terminates w/o executing pending tasks
    - returns a list of the remaining tasks (so they can be run upon restart, or a
    rejection/failed notification can be sent.)
- isTerminated()
    - returns true if shutdown() or shutdownNow() has been called. 
- isShutdown()
    - returns true if shutdown() has been called.
- awaitTermination(long timeout, TimeUnit Unit.)
    - this blocks the calling thread until 
        - the task of the executor have ended
        - timeout occurs
    
    - this is basically "Last Call" for work before we shutdown 