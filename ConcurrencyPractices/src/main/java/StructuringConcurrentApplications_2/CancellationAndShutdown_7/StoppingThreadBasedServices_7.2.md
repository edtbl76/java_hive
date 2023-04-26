# 7.2 Stopping a Thread Based Service.

## PROBLEMS
### PROBLEM 1 - No Good Way To Stop Threads Preemptively
- Applications create services that own threads
    - EX: thread pools
- Lifetime of services > Lifetime of method that creates them
- In order to support graceful shutdown of app
    - threads owned by services must be terminated.

    
    In Java, there is no pre-emptive way to stop a thread
    - they must be "persuaded"/requested to shut down on their own.
    
    
### PROBLEM 2 - Don't interrupt a thread if you don't own it...
- Threads should not be manipulated/modified by anything other than 
the thread owner
    - (Examples: interruption requests, changing the thread's priority)

### PROBLEM 3 - Thread API has no formal concept of thread ownership
- threads are represented by a **Thread** object
    - can be freely shared like any other object  

    
    We usually think of thread ownership in terms of the class that 
    instantiates the object that represents the actual  thread resource. 
    
    Therefore
        - a THREAD POOL owns its WORKER THREADS
        
    Based on these problem statements:
        - Since a Thread Pool owns its worker threads, and only the owner of a thread
        can cancel/interrupt that thread, the thread pool should be responsible for
        managing the state of the worker threads. 
        
### PROBLEM 4:
- Thread ownership isn't transitive
    - (same as any encapsulated object)
    
    
        Application MyApp owns Service MyService
        
        Service MyService owns List<WorkerThread> threads. 
        
        MyApp does NOT own threads. 
        
- Based on this rule, since the application doesn't own the threads, it shouldn't attempt
to stop them DIRECTLY. 


    HOW???


## Lifecycle Methods
The service that owns the threads needs to expose Lifecycle Methods so that an application
that owns the service can provide a logical cascade
    - When the application shuts down (or it needs to shutdown the service) it can shutdown the
    service in addition to request that the service shutdown/interrupt the threads it owns. 
    
    
BEST PRACTICE:
    
    Provide lifecycle methods whenever a THREAD-OWNING SERVICE has a lifetime that is longer
    than that of the method that created it. 
    

## 7.2.1 - Logging Service Example
    
### Example 1: "Producer-Consumer Logging Service w/o Shutdown"
(See Examples.LogWriter)
- logging activity is moved to a separate logger thread. 
    - (instead of having the thread that produces the message
    directly write it to output)
    - LogWriter hands log requests off to a logger thread
        - (via BlockingQueue)
    - logger thread writes out the log. 

#### Multiple Producer-Single Consumer 
- any actions that call the **log()** method acts as a producer
- logger thread is the consumer. 
    - if logger thread falls behind, then **BlockingQueue** backs up
    and blocks the producers until it catches up.
    
#### Problem
- There isn't a way to shutdown the logger thread
    - this means that the thread could possible shut down the
    JVM in a funky way
    
#### Solution 1 
- Stopping the Logger Thread
    - this is easy because it is calling an interruptible method, **take()**
        - modify logger thread to exit on catching **InterruptedException**
        - this will allow an interruption request to the logging thread to stop the service

#### Problem(s)
- There are downsides to the "just exit" shutdown mechanism
    - discard/drop IMPORTANT log messages
    - most importantly -> 
        - if the queue is full, there may be threads blocked in log() waiting to be processed
        - if the thread "just exists", these threads will NEVER BECOME UNBLOCKED
        
        
        NOTE:
        
            Cancelling any Consumer-Producer activity requires that both the
            consumers AND producers participating in the activity are cancelled. 
            
- Interrupting the logger thread causes it to exit, which means that we handle the CONSUMER
- the producers aren't dedicated threads, so thread cancellation is more challenging. 

### Example 2: How not to add shutdown support to the logging service


    public void log(String message) throws InterruptedException {
        if (!shutdownRequested) {
            queue.put(message);
        } else {
            throw new IllegalStateException("logger is shut down");
        }
    }
- this abomination of a solution (that we have repeatedly learned is ineffective) is 
to set a "shutdown requested flag".
    - the purpose of the flag would be to prevent further messages from being submitted after
    the "just exit" on **InterruptedException** occurs.
- STEPS:
    - shutdown requested flag would be set. 
        - further requests are prevented
    - existing requests would be drained from the queue
        - solves lingering problems from previous example
            - writes out remaining log messages (none are discarded)
            - the producers waiting in log() would be unblocked 

#### PROBLEM
- Unfortunately, this pattern is a CHECK-THEN-ACT sequence
    - (race condition)
        - producers could possibly observe that the service hasn't been shutdown.
        - enqueueing these messages after the shutdown yields the same result as before
            - producers might get blocked in log() and never become unblocked. 
            
#### Solution (see Examples.LogService)
This provides the solution to the race condition in the previous problem. 
- the intent of the previous solution is sound, but in order to make it viable
    - we have to resolve the race condition introduced by the CHECK-THEN-ACT sequence. 
    - fixing CHECK-THEN-ACT sequences means making the action atomic.

PROBLEM
- an internal problem to providing atomicity is that if we hold a lock while trying to
enqueue messages, put() can block.

APPROACH
- instead, we can atomically check for shutdown
    - follow this up w/ conditional incrementation/decrementation to reserve the right to
    submit messages. (Decrement is when we release)


## 7.2.2 ExecutorService Shutdown

### Shutdown Type Tradeoff (Safety vs. Responsiveness)
- Graceful shutdown waits for tasks to finish what they are doing and cleanup before updating
    - slower/less responsive, but much safer. 
- abrupt shutdowns (i.e. **shutdownNow**) returns a list of tasks that haven't started after
attempting to cancel all actively executing tasks.
    - very fast/more responsive, but less safe, as some tasks may be cancelled in the middle of
    execution
    - (Potentially leaves data in an inconsistent state)
    
    
### Where to execute shutdown
- Simple programs can start/shutdown a global ExecutorService from **main**
- sophisticated programs will encapsulate **ExecutorService** behind a high-level service w/ 
its own set of lifecycle methods. 

     
     Example (LogService that delegates thread management to an ExecutorService)
     
     
        public class LogService {
            private final ExecutorService executor = newSingleThreadExecutor();
            
            public void start() { }
            
            public void stop() throws InterruptedException {
                try {
                    executor.shutdown();
                    executor.awaitTermination(TIMEOUT, UNIT);
                } finally {
                    printWriter.close();
                }
            }
            
            public void log(String message) {
                try {
                    executor.execute(new WriteTask(message));
                } catch (RejectedExecutionException ignored) {
                
                }
            }
        }
        
- The purpose of a high-level service like this is to delegate thread management to the
compositionally encapsulated ExecutorService rather than managing threads yourself. 
    - this extends the "ownership chain" from application to service, by adding another link
        - each member of the chain manages the lifecycle fo the services/threads it owns.
        
        
        EXAMPLE: 
        
            Application -> High-Level Service -> ExecutorService (Thread Pool) -> Thread


## 7.2.3 Poison Pills
A recognizable object placed on the queue (i.e. in the critical path!) that means "when you
get this, stop"
- This provides a means to kill producers and consumers in a Producer-Consumer activity.

### FIFO Queue Advantage
- This technique is particularly useful because in FIFO queues, it ensures that consumers finish
all of the ASSIGNED work on the queue before shutting down. 
    
    
    EXAMPLE: The queue would look something like this: 
    
        PRODUCERS -> POISON_PILL -> assigned work -> CONSUMER
        
        NOTE: Once a Poison Pill has been placed on the queue, the queue should be closed for
        business. 
        - no more work should be submitted. 
        
### Limitations
- This approach only works when the number of producers + consumers is known
- they also only work reliably on unbounded queues. 


    NOTE: It is possible to extend to
        
        multiple producers:
            - each producer places a pill on the queue. 
            - the consumer will only stop when it receives N pills, where N is the number of
            producers. 
            
        multiple consumers:
            - each producer places N consumer pills on the queue. 
            
    
        In both cases it is hard to manage. 
            
### Code Example
(See Examples.PoisonPill.IndexingService)
- This demonstrates a single-producer/single-consumer service that
uses a poison pill to shut down the service.  


## 7.2.4 - Example - One Shot Execution Service

### USE CASE
- a method needs to process a batch of tasks
    - that method doesn't return until ALL tasks are finished. 
    
### APPROACH
- lifecycle management can be simplified w/ a private **Executor** whose lifetime is
BOUNDED by the method. 
    - **invokeAll** and **invokeAny** methods are useful in this approach. 
    
    
    Example: - Using a private Executor whose lifetime is bounded by a method call
    
        boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) 
                throws Interrupted Exception {
            
            ExecutorService executor = Executors.newCachedThreadPool();
            
            
            /*
                AtomicBoolean vs. volatile boolean.
                - We have to use the atomic boolean, because in order to have access to 
                hasNewMail from the internal Runnable, it would have to be "final"
                    - this prevents us from modifying it.
            */
            final AtomicBoolean hasNewMail = new AtomicBoolean(false);
            
            try {
                for (final String host : hosts) {
                    executor.execute(new Runnable() {
                        public void run() {
                            if (checkMail(host)) {
                                hasNewMail.set(true);
                            }                        
                        }
                    });
                }
            } finally {
                executor.shutdown();
                executor.awaitTermination(timeout, unit);
            }
            return hasNewMail.get();
        }
        
#### ANALYSIS
- method checks for new mail in parallel among a set of hosts. 
- (for loop)
    - creates a private executor and submits a task on each host. 
- (finally block) shuts down executor + waits for termination
    - this only occurs when all of the mail checking tasks are completed. 
    
## 7.2.5 Limitations of shutdownNow

### How It Works
- caller invokes **ExecutorService.shutdownNow()**
    - **ExecutorService** attempts to cancel any tasks in progress.
    - returns a list of tasks that were submitted, but not started. 
        - For convenience (logged/saved for later processing)
        
LIMITATION
- The list of **Runnable** objects returned by shutdownNow may NOT be the same objects that
were submitted to the **ExecutorService** for processing
    - (It's possible that they are "wrapped" instances of the submitted tasks)
    - This means that what we saved for later processing isn't what we wanted to process...

#### Getting List of Started-but-uncompleted Tasks
- There is no facility in the **ExecutorService** that knows the state of 
tasks in progress at shutdown time. 
- in order to know which tasks aren't done:
    - whether or not a task was started
    - whether or not a task was "in progress" at the time the **Executor** was shutdown.
    
LIMITATION:
- no shutdown options exists that both
    - returns a list of unstarted tasks
    - allows tasks in progress to complete 
    
    
### Code Example (See Examples.TrackingExecutor and Examples.WebCrawler)
- note the comment in the code pertaining to the race condition.
    - there is a time gap between when the list of "cancelled" tasks
    is returned to the caller and when the thread pool is shutdown.
    - during this gap, it is possible for one of the tasks in 
    the returned collection to complete. 
- This is an unavoidable race condition. 
    - if the tasks aren't idempotent, then the app getting the
    collection of Runnables has to be prepared for false
    positives. 
    
#### Idempotent Tasks
- Idempotent tasks are those that have the same outcome when
performed multiple times as they would if they were only performed 
once. 
    - This is a GREAT reason for striving to have idempotent work in
distributed systems, because it decreases the risk of something
very bad happening during this little corner case time gaps. 





    
