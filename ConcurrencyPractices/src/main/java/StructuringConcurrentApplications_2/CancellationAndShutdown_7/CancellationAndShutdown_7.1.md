# 7.1 Task Cancellation

### Cancellable
- Activites are cancellable if external code can move it to completion
earlier than its normal run-to-completion

### Reasons for Cancellation

#### User-Requested
- User may have clicked on a "cancel" button
- Cancellation requested through some for of management interface
    - (Ex. JMX -> Java Management Extensions)

#### Time-Limited Activities
- Applications may search a problem space heuristically
    - (find best solution available in X amount of time)
    - incomplete searches are cancelled when the time boundary is reached
    
#### Application Events
- Some applications search a problem space by decomposing the high-level search into 
different regions of the problem space. 
    - (e.g. parallelized binary search )
    - When one task finds a solution, it will result in the cancellation of any other
    active searches
    
#### Errors
- When some tasks encounter an error, it may indicate a circumstance where further work by 
parallel tasks could result in inconsistent state
    - error in one task, may result in other tasks being cancelled. 
    - (Typically existing state of existing tasks is recorded so that the high-level 
    work may be resumed after resolving the error scenario)

#### Shutdown
- when app/service is shutdown, any remaining work must be handled. 
    - graceful/abrupt shutdowns present different behaviors for shutdown
    
### Cooperative Mechanism
- There is NO SAFE WAY to preemptively stop threads in Java. 
    - (therefore no safe way to stop a task)
- **Cooperation** means that the thread requesting the cancellation, and the task must follow 
a collectively agreed protocol for cancellation. 

#### Cancellation Requested Flags
- By setting a "cancellation requested" flag, running tasks can poll for the flag.
    - if flag is set, task terminates early
    - (See Examples.PrimeGenerator for a code example)
- a cancel() method is a setter for the cancellation flag. 
- main loop will poll the flag before searching for the next iteration


    EXAMPLE
    
        List<BigInteger> aSecondOfPrimes() throws Interrupted Exception {
            PrimeGenerator generator = new PrimeGenerator();
            new Thread(generator).start();
            
            /*
                Generator won't necessarily stop PRECISELY after 1 second
                Time delay between
                    - request for cancellation
                    - when the main thread checks for cancellation)
            */
            try {
                SECONDS.sleep(1);
            } finally {
                generator.cancel();
            }
            return generator.get();
        }
        
NOTES:
- Generator won't necessarily stop precisely after 1 second
    - there is a gap in time between when a cancellation is requested and when the main loop
    checks for the flag in the subsequent iteration
- cancel() is called in the finally block to guarantee that the generator's thread is cancelled
    - this solves the problem of the sleep() call being interrupted. 
    - if cancel() isn't called, then the generator thread would run forever. 
    - (This means the thread would run forever, consuming CPU cycles and preventing the JVM from 
    exiting until resources are exhausted)
    
### Cancellation Policy
- A cancellation policy is a set of constraints that provides the details around the cancellable
attributes of a task
    - **HOW** other code may request cancellation
    - **WHEN** tasks determine whether or not cancellation has been requested
    - **WHAT** actions are taken by the task in response to a cancellation request. 
    
    
    Example:
        
        THe cancellation policy for the PrimeGenerator code is
        
        - client requests cancellation via cancel() method
        - PrimeGenerator (the task) checks for cancellation once per each iteration 
        - PrimeGenerator (the task) exits when it detects that a cancellation has been requested
           
## 7.1.1 Interruption

### Limitation of Cancellation Request Flag
- One of the limitations of this approach is that it relies on the task being able to 
check the cancellation request flag. 
    - if task calls a blocking method (i.e. **BlockingQueue.put()**) its possible that the 
    task is never able to check the cancellation flag which leads to a downward spiral
        - task can't check flag ->
        - task runs forever -> 
        - JVM can't quit 
        - resources are used up. 
    
#### Example Code (Examples.BrokenPrimeProducer)
- generates prime nums and places them on a **BlockingQueue**
    - if the producer starts to overrun the consumer, the queue fills up and subsequent put calls
    will block. 
- **PROBLEM**
    - even if the consumer/client tries to cancel the producer while it is blocked, the task
    will be stuck
        - calls to cancel() will properly set the flag, but the task thread is stuck on the
        blocking put() call
        - this means the task thread will never be released to see that the flag has been 
        switched. 
            - (This is a visibility problem!)
            
### Thread Interruption
- Cooperative mechanism that allows one thread to submit a "stop" signal to another thread. 
    - By "stop", we mean:
        - "At your earliest convenience, if you feel so inclined, please stop what you are doing"
- interrupts are REQUESTS not ORDERS
    - this means that we can only ask that a thread stop what it is doing. 
    
    IMPORTANT NOTE: 
    
        There is nothing the API or language specification that ties interruption to any 
        specific cancellation semantics. 
        
        In practice, using interruption for anything but cancellation is incredibly fragile 
        and difficult to sustain in larger applications. 
        
- "interrupted status"
    - boolean flag set on each thread
    - this is set to TRUE when a thread is interrupted.
    
    
    EXAMPLE
    
        public class Thread {
        
            /*
                This method interrupts the TARGET thread
            */
            public void interrupt () {
                // impl 
            }
            
            /*
                Returns "interrupted status" of TARGET thread.
            */
            public boolean isInterrupted() {
                // impl
            }
            
            /*
                This is a SHITTY name. 
                - this actually CLEARS the "interrupted status" of the 
                CURRENT thread and returns it to its previous value
                - this is the only way to CLEAR the state of a thread's 
                interrupted status.
            */
            public static boolean interrupted() {
                // impl
            }
            
            // ... other shit.
        }
        
#### Blocking Calls + Interruption
Blocking methods attempt to detect when threads have been interrupted so that they 
can return early
- (EX: **Thread.sleep** and **Object.wait** )
- their response to interruption is to
    - clear "interruption status"
    - throw **InterruptedException** (which indicates that the blocking operation quit early
    due to an interruption)
- There are no guarantees from the JVM related to the performance of an interruption. 

#### Unblocked Interruption - "Stickiness"
- When unblocked threads are interrupted
    - "interrupted status" is set to true. 
- Interruption detection is handled by the activity being cancelled
    - (it has to poll the "interruption status")
- Stickiness
    - unblocked threads don't throw InterruptedException. 
    - however **interrupt()** calls result in setting the "interruption status", therefore 
    evidence of interruption persists until it is deliberately CLEARED. 
    
    
    NOTE: 
        - calling interrupt() does NOT necessarily stop the TARGET thread from performing 
            its work. 
        - it simply delivers the request for interruption.


### Cancellation Points
- A cancellation point is a "point of convenience" for a thread to stop what it is doing 
after having been requested to do so (via interrupt)
- some methods are sensitive to interruption status
    - (Ex: **wait**, **sleep**, and **join**)
    - these methods are coded to throw an exception if they receive an interruption request
    or encounter a thread with a "stuck" interruption status
    
#### Good Methods
- ignore interruption request
- LEAVE the request in place so that OTHER calling code can act upon it.

#### Bad Methods
- swallows interrupt request
- this prevents code further up the stack from acting on it. 

#### Best Practices for Clearing Interrupted Status
- If you call **interrupted()** and it returns **true**, you should:
    - swallow it (But that's BAD!!!!)
    - DO something with the flag
        - throw **InterruptedException**
        - restore the inerrupted status by calling **interrupt** again.
- Use interruption as your cancellation mechanism
    - this prevents poor custom cancellations 
    - allows you to take advantage of interruption support provided by libs. 
        
### Revisiting Code Example (See Examples.PrimeProducer)
- This demonstrates how we can improve (and simplify) the task from
**BrokenPrimeProducer**
    - shitty boolean interrupt status flag is replaced by built-in
    interruption support from **Thread**

#### Analysis
- 2 Entry points for interrupt detection
    - queue.put() (blocking call from **BlockingQueue**)
    - explicitly polling "interrupted status" in the while loop header
        - (This isn't a FUNCTIONAL requirement, because of the put() call)
        - BUT, it provides more RESPONSIVENESS by checking interruption BEFORE performing the work
        

    This is a basic approach in work/task optimization. 
    
        - Don't do work if you don't have to. 
        
    This is especially important in systems where calls to interruptible blocking methods are 
    infrequent
        - (This means that the interruption state won't be detected often, suggesting that
        more work is being done than is necessary). 
        - The NET result is that this is an optimization intended to increase the responsiveness
        of the systems. 
        
    I've always enjoyed the following analogy: 
        - Look both ways BEFORE crossing the street, rather than AFTER. :)

## 7.1.2 Interruption Policies
- Traits of an interruption policy
    - **HOW** a thread interprets interruption requests
    - **WHAT** thread does (if anything) when an interruption is detected
    - **WHAT** units of work are considered atomic, w/ respect to interruption
    - **WHEN** the thread reacts to interruption
        - (i.e. how quickly)
        
### Basics of Interruption Policies. 
- (Most Common) thread-level/service-level cancellation that
    - exits as quickly as possible
    - cleanup as necessary
    - notifying "an owning entity" that thread is exiting. 
- (Fancy Schmancy) i.e. "NonStandard"
    - pausing/resuming a service

    
    NOTE: Non-standard interruption polices will more than likely need to be restricted to
    tasks that understand how the policies work. 
    
### Tasks v. Threads
- single interrupt requests may have more than one desired TARGET
    - Common Example: 
        - interrupting a worker thread from a thread pool might mean 
            - "Cancel Current Task"
            - AND shut down thread. 
            
#### Tasks
- Borrow threads that are owned by a service (i.e. a Thread Pool)
    - Do NOT execute in a thread that they own
- Tasks don't have to respond immediately to interruption requests. 
    - Common Example:
        - record that an interruption was requested. 
        - finish task, cleanup. 
        - THEN act on the interruption.
    - This is important in terms of protecting data structures from corruption if the
    task is in the middle of a write/update. 
- Tasks should NEVER make assumptions about interruption policy of executing thread. 
    - Tasks should take "GREAT CARE" in preserving an executing thread's interruption status
        - either propogate **InterruptionException**
        - or catch it and then restore the status (**Thread.currentThread().interrupt()**)
- Cancellation Code should not make assumptions about interruption policy of arbitrary threads. 
    

#### Threads
- Guest/Non-Owning Code (i.e. code that doesn't own the thread)
    - (Defined as : "any code outside of the thread/thread pool impl")
    - must always preserve interrupted status of the target thread
        - this prevents us from hiding the thread from its owning code accidentally


    NOTE: 
        - This is why most Blocking libs throw InterruptedException
        - The method is never going to exec in a thread it owns
            - InterruptionException covers TWO requirements of a basic InterruptionPolicy
                - 1.) Exits as quickly as poss9ible
                - 2.) Notifies the caller (via exception) of the interruption so that
                upstream code can take action
                
- Threads should only be interrupted by its owner
    - The owner should encapsulate knowledge of a thread's interruption policy in a 
    an appropriate cancellation mechanism 
        - (Ex: a **shutdown()** method)


    NOTE: 
        interrupting thread should not be performed unless you have INTIMIATE and DEEP
        understanding of what interruption means to the thread being interrupted. 
        
        
## 7.1.3 Responding to Interruption
Responding to Interruption is a first-class requirement for distributed/concurrent applications
that have high responsiveness requirements. 
- long-running methods that do not respond to interruption will yield poor responsiveness with
regards to the work being performed by those methods.


There are two practical strategies for handling an  **InterruptedException** when an 
"interruptible blocking method" is called. 
1. Propagate the exception
    - usually after some cleanup/safety measures are taken
    - (This makes your method an "interruptible blocking method")
1. Restore interruption status so that code at higher levels of the stack can still deal with it
    - usually performed by calling **Thread.currentThread().interrupt()**
    - This is typically Plan B, when you can't/don't wanna propagate the exception. 
        - (A common case is if a task is defined by a **Runnable**)
    
    
    Example:
    
        BlockingQueue<Task> queue;
        
        public Task getNextTask() throws InterruptedException {
            return queue.take();
        }
        

### AntiPatterns
- Do NOT swallow an **InterruptedException** without doing something in the catch block
    - This covers ALL General-purpose task and library code.
    
    
    EXCEPTIONS (no pun intended) to this rule: 
        - if your code is implementing the interruption policy for the thread. 
        - The absolutely rare occasion that you 
            - know what thread your code will run in
            - and you know your code will be the absolute last call unwound from the stack
            so that there is no upstream call to handle the exception.
            
            
### Handling Non-Cancellable Activities

    Example
    
        public Task getNextTask(BlockingQueue<Task> queue) {
        
            boolean interrupted = false;
            try {
                while (true) {
                    try {
                        return queue.take();
                    } catch (InterruptedException e) {
                        interrupted = true;
                        // fall through the cracks and retry...
                    }
                }
            } finally {
                if (interrupted) {
                    /*
                        
                        This is a safety measure. 
                        - We put this in the finally block to ensure that we restore the
                        thread's interruption status BEFORE we return rather than at the point
                        we catch the InterruptionException.
                        
                        If we set the loop earlier, this might cause an infinite loop. 
                        
                        As we discussed earlier, it is considered best practices 
                        for "interruptible blocking methods" to check interruption status
                        on entry and throw InterruptedException immediately if set. 
                            
                    
                    */ 
                    Thread.currentThread().interrupt();
                }
            }
        }
NOTES:
- queue.take() in this example is an "interruptible blocking method" being called from our 
code example that doesn't support cancellation
- in order to support interruption, we wrap the call in a loop so that we keep taking elements
off or the queue until we hit the **InterruptionException**
- the interruption status is stored locally (boolean variable **interrupted**)
    - we disable the interruption status on entry to our method
    - we restore the interruption status by calling **interrupt** in the finally block of our
    try.
        
### Non-Interruptible Code
- If you are dealing w/ code that doesn't call interruptible blocking methods, it is POSSIBLE
to make it more responsive by periodically polling the "current thread"'s interruption status
throughout the code. 
    - While this is possible, it is going to create a terrible mess that is hard to manage. 
    
    
### Cancellation and State
- Cancellation can use state other than the interruption status
    - interruption can be used purely as signalling, such that information stored elsewhere by
    the interrupting thread is used to provide instructions to the interrupted thread. 
        - (Use SYNCHRONIZATION when accessing those instructions)
    - Common Example:
        - **ThreadPoolExecutor**  
        
## 7.1.4 Timed Run - Example
Timed work is essentially the most basic form of Heuristics. 
- spend up to X time looking for an answer. 
    - the absolute most basic impl is to return all answers and let the user figure out which
    one is best. 
    - the next most basic is to let the impl decide the best match from the result set. 
   
### Previous Examples - Interruption Call and Task on Separate Threads (Bad)
   
    Example
    
        List<BigInteger> aSecondOfPrimes() throws InterruptedException {
            PrimeGenerator generator = new PrimeGenerator();
            
            new Thread(generator).start();
            
            try {
                SECONDS.sleep(1);
            } finally {
                generator.cancel();
            }
            return generator.get();
            
        }
        
- We know that it might take longer than a second to stop, but eventually the generator will
notice the interrupt and quit. 
- PROBLEM:
    - if the PrimeGenerator throws an unchecked exception before SECONDS.sleep() expires, 
    this will most likely go unnoticed because it is running in a separate thread
    
### Scheduling Interruptions on a Borrowed Thread (Don't)

    Example 2:
    
        private static final ScheduledExecutorService cancelExec = ...'
        
        /*
            execute runnable for given amount of time.
        */
        public static void timedRun(Runnable runnable, long timeout, TimeUnit unit) {
        
            // place task on the calling thread. 
            final Thread taskThread = Thread.currentThread();
            
            // interrupt thread after given interval
            cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
            runnable.run();
        }
        
- This example solves the problem from the previous. 
    - we are placing this task on the same thread as the caller
    - any unchecked exceptions thrown from the task will be on the same thread as the caller, 
    and therefore caught by that caller. 
- PROBLEM:
    - this code doesn't know anything about the calling thread. 
    - "You should know a thread's interruption policy before interrupting it."
- POTENTIAL DRAWBACKS.
    - If the task completes before the time interval then the cancellation task is going
    to go off AFTER the method has returned. 
        - (Not good)
    - if task isn't responsive to interruption, timedRun isn't going to return until the 
    task finishes. 
        - (probably well after the timeout, if at all)
        
### Interrupting Tasks in a Dedicated Thread

        Example 3: 
        
        
            public static void timedRun(final Runnable runnable, long timeout, TimeUnit unit) 
                        throws InterruptedException {
                
                class RethrowableTask implements Runnable {
                
                    /*
                        This throwable is shared by more than one thread, so we 
                        mark it volatile.
                    */
                    private volatile Throwable throwable;
                    
                    public void run() {
                        try {
                            runnable.run();
                        } catch (Throwable t) {
                            this.throwable = t;
                        }
                    }
                    
                    void rethrow() {
                        if (throwable != null) {
                            throw cleanThrowable(throwable);
                        }
                    }
                }            
                
                RethrowableTask task = new RethrowableTask();
                
                // Thread specifically created to run the task 
                final Thread taskThread = new Thread(task);
                taskThread.start();
                
                cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
                
                /*
                   when join is called on a thread
                    - the CALLING thread goes into a waiting state
                    - it waits until the CALLED thread terminates.  
                    
                */
                taskThread.join(unit.toMillis(timeout));
                
                /*
                    After the join stops, we call rethrow
                    - this checks to see if an exception was thrown from the task
                    - If so, we rethrow (i.e. propagate) the exception.
                */
                task.rethrow();
            }
        
This is the preferred way to handle exceptions when interrupting tasks. 
- a thread is created/allocated to run the task. 
    - even if the task itself doesn't respond to the interrupt the method can return to the 
    caller. 
- NOTE: the saved **Throwable** is shared between the two threads. 
    - This means that it must be marked **volatile** to allow it to be safely published
    from the task thread to the timedRun thread. 
    
PROBLEM:
- This solution inherits a limitation of **Thread.join()**
    - we don't know why control was returned to the calling thread (the task thread) 
        - did it exit normally? 
        - did the **join** time out? 
        
        
## 7.1.5 Cancellation via Future

### Review (Future)
Future is a useful abstraction
- manages task lifecycle
- deals w/ exceptions
- facilitates cancellation

        
        Example - Task Cancellation Using Future
        
            public static void timedRun(Runnable runnable, long timeout, TimeUnit unit) 
                        throws InterruptedException {
                        
                /*
                    ExecutorService.submit() takes a Runnable/Callable and returns a
                    Future. 
                */
                Future<?> task = taskExector.submit(runnable);
                
                try {
                    task.get(timeout, unit);
                } catch (TimeoutException e) {
                    // task is cancelled below
                } catch (ExecutionException e) {
                    // propagate/rethrow exception
                    throw cleanThrowable(e.getCause());
                } finally {
                    /*
                        This interrupts the thread if running.
                        - If the task has already completed, then this is harmless because
                        there is nothing to interrupt.
                        
                        - This is a BEST PRACTICES/OPTIMIZATION
                        - by placing the cancellation in the finally block I ensure that 
                        cancellation is the "last" activity regardless of the scenario.
                            
                    
                    */
                    task.cancel(true);
                }
            }
            
#### Example Code
- The timedRun method takes a **Runnable**, so we need to use **ExecutorService.submit()** to
get a **Future**


#### Future.cancel()
- takes a boolean (**mayInterruptIfRunning**)
    - if TRUE and task is NOT running
        - nothing happens (i.e. there is no runnable to interrupt)
    - if TRUE and task is RUNNING
        - cancel requests interruption of the thread in which the task is running
    - if FALSE
        - "don't run this task if it hasn't started"
        - used for tasks that aren't designed to handle interruption.
- returns a boolean that indicates whether or not cancellation attempt was successful
    - this only confirms delivery of interrupt request
    - it does NOT
        - indicate that task DETECTED the interruption
        - that a task ACTED on interruption that was detected.


    NOTE:
        
        We placed the cancel() call in the finally block. This is helpful because it ensures that
        cancellation is the final action that occurs in each of the scenarios .
        
        1.) task.get() returns the result of the work done, followed by the cancellation of the
        task (because we don't need it anymore)
        
        2.) task.get() throws a TimeoutException, because the allotted time expires. This is 
        followed by the (early) cancellation of the task because it is past the point we 
        deem valuable
        
        3.) The underlying computation throws an ExecutionException, which we will rethrow so
        that the caller can deal with the exception. However, following the propagation of the
        errors, we cancel the task so that the thread can be returned to the pool for future
        use.
        
            The underlying computation is represented by the Runnable that is passed into our
            method. 
            
            Our Method encapsulates the coordination/management by using ExecutorService.submit
            to get a Future from the Runnable. 
            
            
        CANCEL YOUR TASKS WHEN THEY ARE NO LONGER NEEDED!
        - any time that Future.get() throws 
            - InterruptedException
            - TimeoutException
            
            ... it is no longer needed.
        
        
REMEMBER: We shouldn't interrupt a thread w/o an intimate understanding of
the thread's interruption policy. <br> 

So when is it OK to call **Future.cancel(true)** ? 
- any task execution threads created by STANDARD **Executor** impls use an interruption 
policy that lets tasks be cancelled using interruption
- this means that it is SAFE to set **mayInterruptIfRunning** = true, if:
    - tasks will be cancelled through their **Future**s
    - and those tasks are running in a standard **Executor** 
    
BEST PRACTICES:
- it's never a good idea to interrupt a pool thread directly
    - we dont know what (if any) task(s) are running when the request is delivered. 
    - if we deliver this through a task's **Future**, we handle both in a coordinated fashion

## 7.1.6 Non-interruptible Blocking
- Not all blocking methods are interruptible (i.e. responsive to interruption)
    - Common Examples
        - synchronous socket I/O
        - waiting to acquire intrinsic lock
        
### Synchronous socket I/O (java.io)
- Reading/Writing To a Socket
    - (common form of Blocking I/O in server apps)
- PROBLEMS:
    - **read()/write()** methods in **InputStream/OutputStream** aren't interruptible
- BEHAVIOR:
    - closing underlying socket causes any threads blocking in read/write operations to 
    throw **SocketException**
    
### Synchronous I/O (java.nio)
- most standard **Channel**s implement **InterruptibleChannel**
- BEHAVIORS
    - interrupting a thread waiting on **InterruptibleChannel** 
        - causes thread to throw **ClosedByInterruptException**
        - closes the channel
        - causes all other threads blocked on channel to throw **ClosedByInterruptException**
    - CLOSING an **InterruptibleChannel**
        - causes threads blocked on channel operations to throw **AsynchronousCloseException**

### Asynchronous I/O with Selector
- BEHAVIORS
    - if thread is blocked in **Selector.select** (java.io.channels)
        - calling **close()/wakeup()** causes thread to return prematurely

### Lock Acquisition
- BEHAVIORS
    - if thread is blocked waiting for an intrinsic lock
        - there is NOTHING YOU CAN DO TO STOP IT
        - if it eventually acquires the lock, and makes enough progress, it MAY be possible to 
        get its attention some other way.
- ALTERNATIVE
    - explicit **Lock** classes offer **lockInterruptibly** methods
        - these methods allow to wait for a lock while still being interruptible.
        


### Example - Code
(See Examples.ReaderThread) for an example of encapsulating a
nonstandard cancellation in a **Thread** by overriding **Interrupt**
- The ReaderThread manages a single socket connection
    - reads bytes synchronously from the socket's **InputStream**
- The solution overrides **interrupt** to provide a nonstandard
cancellation option
    - it closes down the underlying socket.
    - it delivers the standard interrupt in the finally block by
    calling **super.interrupt()**
            
## 7.1.7 Encapsulating nonstandard cancellation with newTaskFor

### newTaskFor 
- a factory method hook added to **ThreadPoolExecutor**
    - creates a **Future** that represents the task. 
    - returns **RunnableFuture**
        - (interface that extends both **Future** and **Runnable**)
        - implemented by **FutureTask**

#### Benefits
- Customizing a task's **Future** allows you to override its
**cancel()** method.
    - Allows:
        - insertion of logging code to capture log events
        - insertion of statistics gathering code to build 
        cancellation analytics
        - wrapping/insertion of code to cancel non-interruptible
        activities.

### Code - Example 
(See Examples.CancellableTask)
- CancellableTask is an interface that extends **Callable**
    - cancel() method (for cancelling)
    - newTask() factory for returning a **RunnableFuture**
- CancellingExecutor is a class that extends **ThreadPoolExecutor**
    - overrides **newTaskFor** so that we can let an instance
    of the CancellableTask create its own **Future**
    
    
    The benefit of the newTask() implementatino in "SocketUsingTask"
    is that when the task is cancelled through its future
        - the socket is closed
        - AND the executing thread is interrupted. 
        
        This is what we want. 
        - By performing both operations via that one call, 
        we increase the task's responsiveness to cancellation
        
    The task is flexible as well:
    - it can safely call interruptible blocking methods while 
    remaining cancellable.
    - it can also call blocking socket IO methods
