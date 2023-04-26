# 5.4 Blocking and Interruptible Methods

### Why Threads Block
- waiting for I/O
- waiting to acquire a lock
- waiting to wake up from sleep (i.e. Thread.sleep)
- waiting for work to be completed from another Thread.
    - i.e. some computation or calculation
   
### How Threads Block
- the thread is suspended and it is placed in one of the blocked
thread states
    - BLOCKED
    - WAITING
    - TIMED_WAITING
    
### Blocking vs. Non-Blocking
The obvious difference (in most cases) is that a blocking operation
usually takes longer to complete that non-blocking operation. 
- in a non-blocking operation, all of the work to be done is
contained on the thread performing the work. 
    - it accepts the work
    - it does the work
    - it is done (and marked RUNNABLE)
- in blocking operations, some of the work is being done outside of
the thread. This means that the thread must WAIT for that external
task/work to be performed before it can proceed. 
    - it accepts work
    - it does some work and/or waits for work to be done (blocks)
    - it is done (and marked RUNNABLE)
- once a thread is marked as RUNNABLE it is eligible for scheduling.

### InterruptedException
This is a type of exception that is exclusive to blocking code (methods).
- interruption is a way for a blocking method to stop blocking early
- by early, we mean "before the external event has completed"


    BlockingQueue Examples
        - put()
        - take()
        
    Thread.sleep() also throws InterruptedException
    
#### Thread.interrupt()
This is a method provided by a Thread for
- interrupting a thread
- querying if a thread has been interrupted. 

Every thread has a bool representing interrupted state. 
- interrupt() sets that state

### Interruption
Cooperative Mechanism
- one thread can't force another thread to stop what it is doing and do s
something else


    EXAMPLE:
        
        When ThreadA interrupts ThreadB
            - it is a request to ThreadB to stop doing what its doing when it gets 
            to a convenient point to stop. 
            - (Only if ThreadB feels like it) 
            
Cancellation
- The most sensible use for interruption is for it to signal the request for
cancellation of an activity
    - This is NOT a guarantee or a demand.

#### Blocking Methods + Interruption
- Methods that throw InterruptedException are blocking calls. 
    - they need to have a plan for responding to interruption
    
##### Plan 1: Propagating InterruptedException
- pass InterruptedException to the caller
    - don't catch it at all
    - catch it, and then do some cleanup/parsing/etc.

##### Plan 2: Restore the interrupt
- circumstances when InterruptedException can't be thrown
    - (i.e. code is part of a Runnable)
- STEPS:
    - catch InterruptedException
    - restore interrupted status
        - (Call interrupt() on current thread)
    - code higher up call stack can see that an interrupt was issued.
    
    
    EXAMPLE
    
        public class TaskRunnable implements Runnable {
            BlockingQueue<Task> queue;
            ...
            
            public void run() {
                try {
                    processTask(queue.take());
                } catch (InterruptedException e) {
                    // restore interrupted status
                    Thread.currentThread().interrupt();
                }
            }
        }
        
    
##### Plan 3: Other Sophisticated Things
(Not mentioned here)

##### What NOT to DO
Do NOT catch InterruptedException and then do nothing
- prevents code higher up the call stack to act/respond to the interruption
    - (There is no indication that the thread was interrupted remaining)

There is only ONE scenario where it is acceptable to swallow an interrupt
- when extending Thread
- the reason this is allowed, is because extending Thread allows you full control of
all code higher up the call stack. 
    

   

