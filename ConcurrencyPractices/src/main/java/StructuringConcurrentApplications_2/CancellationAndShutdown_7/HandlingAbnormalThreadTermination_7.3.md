# 7.3 Handling Abnormal Thread Termination

### Failure in Single-Threaded Apps vs. Multi-Threaded Apps

#### Single-Threaded App Failure
- obvious outcome
- program stops running due to uncaught exception
- dumps a stack trace.

#### Multi-Threaded (Concurrent) App Failure
- thread failure isn't as obvious
- stack trace MAY be printed on console
    - no one is necessarily watchin git.
- when a thread fails
    - app may not quit
    - app might appear to be working normally
    - FAILURE.MIGHT.GO.UNNOTICED
    
### Premature Thread Death
- LEADING CAUSE => **RuntimeException**
    - usually are uncaught because they indicate programming 
    errors or some unrecoverable porblem
    - default handling of RuntimeExceptions
        - propagate up the stack 
        - print stack trace to console
        - thread terminates
        
        
        BEST PRACTICES:
            
            The less familiar you are with the code being called, the greater your
            skepticism should be about its behavior. 
            
                "I don't know what I don't know"
        
#### Consequences of premature thread death
- entirely depends on what that thread is doing in the application
    - might be completely benign
    - might be completely disastrous.
    - EXAMPLES:
        - losing a thread in a 10-thread pool might be a 10% drop in performance
        - losing a thread in a 100-thread pool might be a  1% drop in performance
        - However, losing the dispatch/UI thread is a game stopper. 

#### Worker Threads (Task-processing threads)
- EXAMPLES
    - thread pool threads
    - **Swing** event dispatcher
- These threads spend their entire lifecycle calling unknown code through abstraction
barriers like **Runnable**, **Callable**
    - They need to be designed/developed with skepticism about the code they will call.
    - **try-catch** to catch unchecked exceptions
    - **try-finally** to ensure that corrective actions are taken for threads that exit
    abnormally
- catching **RuntimeException**
    - This is normally considered an anti-pattern
        - When a thread throws an unchecked exception, there is a potential safety concern. 
            - the entire application may be compromised
    - There is a TRADEOFF
        - if the thread is a mission-critical thread (dispatcher, etc.), failing to catch 
        (and most likely swallow) the **RuntimeException** will likely fuck the application into
        oblivion. 
            
### ThreadPool/Worker Thread structure



    EXAMPLE
    
        public void run() {
            try {
                while (!isInterrupted()) {
                    runTask(getTaskFromWorkQueue());
                }
            } catch (Throwable e) {
                thrown = e;
            } finally {
                threadExited(this, thrown);
            }
        }
        
#### Explanation
- The code above demonstrates how to structure a worker thread inside a thread pool
    - the **finally** block allows the thread to bail out
    - however, the catch statement ensures that the owning framework (thread pool) is
    notified that the thread has died. 
- The benefit of this approach is that we are letting the framework (thread pool) know 
about the dead thread so that the framework can decide what to do about it. 


    REMEMBER:
        The owner of a thread is essentially responsible for managing the threads. 
        - It makes sense then that the framework will be responsible for determining how
        to handle respawning/replacing threads.
    
#### How Framework Handles Thread Death Notifications
- Some frameworks might just automatically respawn a thread
- Other frameworks are more intelligent. 
    - They might look at the current demand for resources, and determine whether or not
    it makes sense to allocate another thread. 
        - Maybe the thread pool is being shutdown? 
        - Maybe the current number of threads can handle the load. 
        - (**ThreadPoolExecutor** and **Swing** do this)
    - these approaches prevent poorly written task/plugin from taking down the 
    thread that happens to call it.
    
## 7.3.1 Uncaught Exception Handlers
- The previous section uses a proactive approach to handle unchecked exceptions

### UncaughtExceptionHandler

    Example:
        
        public interface UncaughtExceptionHandler {
            void uncaughtException(Thread thread, Throwable throwable);
        }

- provided by **Thread** API
- used to detect when a thread dies due to uncaught exceptions


    BEST PRACTICE:
        The use of proactive checks AND UncaughtExceptionHandler together is considered 
        an "in-depth" defense against thread leakage. 
        
#### How it works
- thread dies due to uncaught exception
- JVM reports event to the app provided **UncaughtExceptionHandler**
- What the handler does depends on your own quality of service requirements
    - COMMON EXAMPLE:
        - write error message + stack trace to an application log
        - (in long running applications, this is considered a required minimum)
        
        
    Example: Logging Exception with a UncaughtExceptionHandler
    
        public class UEHLogger implements Thread.UnchaughtExceptionHandler {
            public void uncaughtException(Thread thread, Throwable throwable) {
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, "Thread fucking died! Error: " + thread.getName(), throwable);
            }
        }
        
    
More direct actions than logging
- attempt to restart thread
- shut down application
- paging an operator
- (other corrective/diagnostic actions)
    

### Setting UEH for Pool Threads
- provide a **ThreadFactory** to the **ThreadPoolExecutor** constructor
    - REMEMBER: only thread's owner should change it's UEH
- standard thread pools allow UEH to terminate a pool thread
    - UEH typically only STOPS threads
    - use the **try-finally** proactive check when UEH terminates a thread so that
    the thread can be replaced (or at the very least evaluated for replacement)
    
    
#### what happens if there is no handler?
- JVM dumps stack trace to **System.err**
- w/o UEH and/or another failure notification mechanism
    - tasks can appear to fail silently (PAINFUL and CONFUSING)
- SOLUTION:
    - In order to make tasks fail LOUDER
        - wrap the task w/ **Runnable/Callable** that catches exception
        - (or) override **ThreadPoolExecutor.afterExecute** hook
        
### PROBLEM: execute vs. submit
- UEH only handles exceptions that are invoked via **Executor.execute()**
- when a task is invoked via **Executor.submit()**
    - ANY thrown exception (checked or unchecked) is included as part of the task's
    return status:
        - task invoked w/ **submit** terminates e/ exception
        - it is rethrown/propagated by **Future.get()**
        - wrapped in an **ExecutionException**
