# 6.2 The Executor Framework
TASKS: logical units of work <br>
THREADS: mechanisms by which tasks can run asynchronously

Executor Framework includes a thread pool implementation as
part of java.util.concurrent.

### Policies Introduced in 6.1

#### Sequential Execution (Single Thread)
- Poor responsiveness
- Poor Throughput

#### Thread-per-Task
- Poor Resource Management

### Bounded Queues and Thread Pools

#### Bounded Queues
- prevent overloaded applications from running out of memory

#### ThreadPools
- like a "bounded queue" for threads


### Executor 
- primary abstraction for task execution in Java class libs
- based on Producer-Consumer pattern
    - PRODUCERS
        - activities that submit tasks
        - produces units of work to be done
    - CONSUMERS
        - threads that execute tasks
        - CONSUMES work to be done    
    - "usually", an Executor, is the easiest path to implementing
    the producer-consumer design in an application

    public interface Executor {
        void execute(Runnable command);
    }
    
#### Features
- describes tasks w/ Runnable
    - provides a standard means for decoupling TASK SUBMISSION from
    TASK EXECUTION
- implementations provide 
    - lifecycle support
    - support for stats gathering
    - support for app management
    - support for monitoring
    

## 6.2.1 - Example - Web Server Using Executor
(see Examples.TaskExecutionWebServer)

### Analysis
- replaces the explicit thread creation (from previous examples) with
the Executor.

    
    Old:        new Thread(() -> handleRequest(connection));
    New:        exector.execute(() -> handleRequest(connection));
    
- This decouples the task submission from its execution
    - execution can be changed by swapping out the concrete 
    implementation of the Executor
- changing exec impl (or config) is much easier (less invasive) 
than changing the way tasks are submitted. 
    - execution config can usually be exposed for deploy time
    config
    
### Executors are also flexible enough to be used to implement the
previous use cases from 6.1

    Example
    
        // Thread-per-task
        public class ThreadPerTaskExecutor implements Executor {
            public void execute(Runnable runnable) {
                new Thread(runnable).start();
            }
        }
        
        // Sequential execution
        public class WithinThreadExecutor implements Executor {
            public void execute(Runnable runnable) {
                Runnable.run();
            }
        }

## 6.2.2 Execution Policies

EXECUTION POLICY: The "what, where, when and how" of task execution
- thread on which tasks are executed 
- the order that the tasks should be executed
    - FIFO, LIFO, priority
- no. of tasks allowed to execute concurrently
- no. of tasks allowed to be queued (pending execution)
- task rejection handling
    - if system is overloaded, which task is selected for rejection?
    - how is the application that submitted the task notified?
- what happens before/after a task is executed? 


### Execution Policy as a Resource Management Tool
- Policy optimization is determined by
    - available compute resources
    - quality-of-service requirements
- Examples
    - limiting concurrent tasks enusres that app doesn't
        - fail due to resource exhaustion
        - suffer perf problems due to contention for scarce resources
        
#### Transaction Monitor
Note: in enterprise applications, a Transaction Monitor is typically
created specifically to handle performance problems due to 
contention for scarce resources
- this involves rate limiting (or blocking)  certain txns to prevent
overtaxing the system. 

### Benefits of Decoupling Submission from Execution
- allows easy specification and alteration of the EXECUTION POLICY
for a given class of tasks without great difficulty

### Use Executor > New Thread
When you see:

    
    new Thread(runnable).start()
    
Consider replacing it w/ an Executor

## 6.2.3 Thread Pools

### Definition
- a data structure that manages a homogeneous pool of worker threads
- tightly bound to a WORK QUEUE holding tasks that are waiting to be
executed. 

### Worker Thread LifeCycle
- request next task from queue
- execute that task
- go back to waiting for tasks to execute

### Thread Pools > Thread-per-task
- Reusing existing threads amortizes thread lifecycle overhead
(i.e. creation/teardown) costs over multiple requests
    - less impact for expensive operations
- RESPONSIVENESS is improved because the thread already exists
when the request arrives
    - no latency induced due to thread creation. 
    
#### Optimization/Tuning
- finding a "sweetspot" in terms of the size of the thread pool
    - have enough threads to keep CPUs busy
    - not so many that app hits OOM or thrashes due to 
    competition among threads for resources. 
    
### Types of Thread Pools

#### newFixedThreadPool:
- fixed size
- creates threads as tasks are submitted up to max pool size
- once max pool size is reached, it will attempt to sustain that
pool size by spawning new threads if a thread dies due to an
unexpected Exception

#### newCachedThreadPool
- unbounded pool size
- added flexibility to 
    - REAP (kill) idle threads when "current pool size" > "processing demand"
    - SPAWN (create) new threads when "current pool size" < "processing demand"

#### newSingleThreadExecutor
- creates single worker to process tasks
    - worker is re-spawned if it dies unexpectedly
- guarantees task processing order determined by task queue
    - (FIFO, LIFO, priority) 

NOTE: provides sufficient internal synchronization to GUARANTEE that
any memory writes made by tasks are visible to subsequent tasks. 
- objects can be "SAFELY" confined to the task thread, even though
that thread might be replaced w/ another from time to time.

#### newScheduledThreadPool
- fixed size thread pool
- supports delayed/periodic task execution 
    - (Similar to Timer)

### ThreadPoolExecutor (General-Purpose)
- returned by
    - newFixedThreadPool
    - newCachedThreadPool
- a general purpose impl that can be used as a stepping off point for 
constructing more specialized executors.

### execute()
- The execute method of an Executor is synonymous to the 
run() method of a Runnable.
- What it does:
    - enqueues task to work queue
    - worker threads from the pool dequeue tasks from work queue 
    - worker threads execute the task. 

### Benefits of Pool-Based Policy over Thread-per-task policy
- service no longer fails under heavy load. 
- bounded pools have graceful degradation because the threads
are less likely to compete for CPU/Memory resources
- more robust management capabilities
    - error reporting
    - logging
    - tuning
    
#### Note on OOM
- While Executors OOM unlikely, it is still possible to
occur if the "Arrival Rate" exceeds the "Service Rate" for
long enough. 
    - the work queue will eventually be overloaded by Runnables
    waiting on execution. 
    - (This requires the use of a bounded work queue)
    
## 6.2.4. Executor LifeCycle

### Executor Cancellation
The JVM can't exist until all non-daemon threads have terminated
- failure to shut down an Executor could prevent JVM from exiting.

Executors process tasks asynchronously
- state of previously submitted task isn't immediately obvious
    - could be running? 
    - completed? 
    - waiting for exec? 

Shutdown Spectrum
- graceful shutdown
    - "finish what you've started, but don't accept new work"
- abrupt shutdown
    - "turn off power to the machine)"
- various degrees of grey


### Executor Lifecycle Methods

    public interface ExecutorService extends Executor {
        
        /*
            Initiates a graceful shutdown
            - no new tasks are accepted
            - previously submitted (queued) tasks are allowed to 
            complete
                
        */
        void shutdown();
        
        /*
            Attempts abrupt shutdown
            - no new tasks are accepted
            - queued tasks won't be started
            - attempts to cancel outstanding tasks. 
        */
        List<Runnable> shutdownNow();
        
        boolean isShutdown();
        
        boolean isTerminated();
        
        boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;
            
        // additional convenience methods for task submission
        
    }
   
- Rejected Execution Handler
    - responsible for handling tasks submitted to an ExecutorService that has been shutdown
        - silently discard task? 
        - throw unchecked RejectedExecutionException
- THREE STATES
    - RUNNING
        - initial state of ExecutorService
    - SHUTTING DOWN
        - transitional state where ExecutorService abruptly/gracefully manages 
        acceptance of new work and how the existing work should be handled. 
    - TERMINATED
        - ExecutorService that has navigated shutdown()/shutdownNow(). 
        - testing for this state can be done by
            - polling w/ isTerminated()
            - or a more reactive approach using awaitTermination() 
            
            
NOTE: Common Pattern
- typically one of the "shutdown" commands is immediately followed by "awaitTermination"
- this has the effect of synchronous shutdown of the ExecutorService

#### Example Code
(See Example.LifecycleWebServer)
- extends previous example w/ ExecutorService lifecycle support
    - provides 2 methods for shutdown 
        1. stop() (programmatic)
        2. sending web server specially formatted HttpRequest. 
       

## 6.2.5 Delayed and Periodic Tasks

### Timer 
can managed execution of
- deferred tasks ("run me in 10 minutes")
- periodic tasks ("run me every Tuesday at 5 PM EDT")

#### Drawbacks
- single threaded for timer tasks
    - if task takes too long to run, following tasks may be less accurate. 
        - if set at fixed-time, it could be missed entirely (BAD!)
        - if set at fixed delay, it could be executed rapidly after long-running task quits. 
- Timer doesn't catch unchecked exceptions
    - unchecked ex in TimerTask will terminate Timer Thread. 
    - Timer doesn't resurrect the thread, it assumes it was cancelled. 
    - END RESULT = **"Thread Leakage"**
        - TimerTasks that are already waiting in the queue will never be run 
        - new tasks can't be scheduled. 
        
- See Examples.OutOfTime for an example of this phenomenon in code.        
        

### ScheduledThreadPoolExecutor
- "replacement" for Timer class
- created through
    1. ScheduledThreadPoolExecutor constructor
    2. newScheduledThreadPool factory <-- preferred per Effective Java!
- only supports relative time
    - (This is the ONE advantage that Timer provides: it supports absolute time.)
    
#### Benefits
- allows deferred/periodic tasks to be configured to run on their own threads, or to 
work from a pool of workers. 
- properly handles unchecked exceptions to avoid Thread Leakage


#### DIY Scheduling Service
- You want to avoid this as much as possible. 
- However if you are fucking stupid, insane, (or you are a poor sap who just has one of those corner case
user stories that wasn't covered by the generic implementation)

#### DelayQueue
- Type of BlockingQueue impl that provides the scheduling capabilities of ScheduledThreadPoolExecutor
- manages a collection of **Delayed** objects. 
    - Delayed - has a delay time associated with it
    - DelayQueue is only allowed to take() an element if the delay has expired. 
    - Objects returned from DQ ordered by the time associated w/ their delay. 




