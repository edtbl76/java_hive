# yield()
"Let go, Give up, Surrender"
- a YIELDINIG THREAD tells the JVM that it is willing to let other threads be scheduled in
its place. 
    
## PROPERTIES of YIELD()
- static, native method
- tells CURRENT executing thread to "give a chance" to the waiting threads of EQUAL PRIORITY
  within the Thread Pool.
  - NOTE: there is no guarantee that yield() will make the currently executing thread to a
  "runnable" state immediately.

- yield can only take a thread from RUNNING to RUNNABLE. 
    - it cant move it to WAIT or BLOCKED state. 
    
    
    EX:
    
    /**
        A hint to the scheduler that the current thread is willing to yield its current use
        of a processor. The scheduler is free to ignore this hint. 
        
        Yield is a HEURISTIC attempt to improve relative progression between threads that
        would otherwise over-utilize a CPU
        
        Its use should be combined w/ detailed profiling and benchmarking to ensure that
        it actually has the desired effect.
    */
    public static native void yield();
    
    
## LOOK AT EXAMPLES
The NoYieldExample.java is unpredictable. 
you might get the producer running all of its tasks first, the consumer running all of 
its tasks etc. 

- If we execute the CONSUMER before the PRODUCER, then we may be trying to USE
data before it has been created (NPE?)
- potentially even worse, if we execute the CONSUMER before the PRODUCER, but the 
application THINKS they have been executed in the proper order, we will have
inconsistent data. 

NOTE: those are worst case scenarios. If we execute a synchronized method, then we'll just
waste cycles by executing the consumer early (i.e. it won't perform its task if the 
condition isn't met)

The YieldExample.java creates a more predictable "fair" multi-threading approach
NOTE: the disadvantage here is that we are going back and forth. If transactions/tasks
are being performed over a network, it is likely that we will have a large amount of
overhead to actually deliver this information. yield + some degree of control flow 
can result in batching of requests to optimize the amount of data being sent so we have a
higher data-to-overhead ratio. 
