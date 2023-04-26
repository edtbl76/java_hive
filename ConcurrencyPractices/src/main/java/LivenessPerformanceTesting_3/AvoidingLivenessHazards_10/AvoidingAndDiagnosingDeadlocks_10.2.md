# 10.2 Avoiding and Diagnosing Deadlocks

A program that never acquires more than one lock at a time can't experience lock-ordering deadlocks.
- That is the EASIEST way to avoid deadlocks.
- it's also probably the least practical. 

A program that HAS to acquire multiple locks should do the following
- design lock ordering properly
    - minimize the number of locking operations 
        - "a program that never acquires..."
    - document and CONSISTENTLY follow a lock-ordering protocol for locks that
    can be acquired together.
    
### Best Practices for Auditing Code For Deadlock Freedom
1. Identify Where Multiple Locks Can Be Acquired
    - try to ensure this is as small a set as possible
2. Perform a global analysis of all multiple lock scenarios
    - ensure that lock-ordering is consistent
    - use open calls
        - makes analysis easier
        - (Some IDEs can actually detect this)
        
## 10.2.1 Timed Lock Attempts
Technique for detecting and recovering from deadlocks.


Use the timed "tryLock" feature of explicit Lock classes INSTEAD of intrinsic locking
- intrinsic locks wait forever to acquire a lock
- timed tryLock will return a failure once the specified timeout expires

### Benefits
- If the timeout period is set to a value much greater than it should take to acquire the lock, 
we'll be able to regain control of the system rather than have to bounce it due to infinite wait
    - if the lock times out, we can take steps to allow the program to recover
        - release lock  =>  back off 'n' wait  =>  try again
    
    
    NOTE: 
        The mentioned recovery process ONLY works when two locks are acquired together
        - if multiple locks are acquired due to nesting of method calls, we can't just
        release the outer lock. 

- can be effective even if it isn't used consistently 
    - (more flexible than strict lock ordering)


### Drawbacks
- when a timed lock attempt fails we don't know why
    - it might be a deadlock
    - it might be that a thread got stuck in an infinite loop while holding the lock
    - shit is slow. 
    
## 10.2.2 Deadlock Analysis with Thread Dumps
Thread dumps include
- stack trace for each running thread (similar to stack trace that comes with an exception)
- locking info
    - which locks are held by each thread
    - the stack frame the lock was acquired
    - which lock a blocked thread is waiting to acquire.
- deadlock info
    - id's which locks and threads are involved
    - id's where in the program the offending lock acquisitions were made. 
    
### How JVM Finds DeadLocks
- before generating the thread dump, JVM searches the "is-waiting-for" graph for cycles
    - cycles are where potential deadlocks exist
    - if it finds them it provides the aforementioned deadlock information in the thread dump.
    
### Example of Deadlock ID'd in Thread Dump
![Thread Dump Deadlock](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/AvoidingLivenessHazards_10/Images/Screen Shot 2020-12-11 at 11.48.08 PM.png)
- One thread holds lock on **MumbleDBConnection** 
    - waiting for **MumbleDBCallableStatement**
- The other holds/waits in reverse order. 