# Lock And Monitor (Java Concurrency)

A preliminary note on concurrency protection:
- the concepts below are specific to SYNCHRONIZED methods only. an unsynchronized
method can be called by ANY thread at ANY time. This is why we refer to SYNCHRONIZED
methods as THREAD-SAFE.... they are protected from interference between the tasks performed
by threads that may/may not conflict.

## LOCKS
a kind of data which is logically part of an object's header on the heap memory. 
- each object in a JVM has a lock (MUTEX) that any program can use to coordinate multi-threaded
access to the object. 
    - in order to access instance vars of that object, a thread must OWN the object's lock
        - (This is done by setting some flag in a lock memory area)
    - any other attempt to access the object's vars/data have to wait until the owning 
    thread releases that lock (by unsetting the flag.)
    
- when a thread owns a lock, it can request the same lock multiple times
    - but it also must release the same lock again multiple times before it is made available
    to other threads. 
    - EX: if it requests it 3x, it must release it 3x. 
    (Probably implemented on a stack)
    
- locks are acquired by threads in Java w/
    - synchronized keyword
    - wait() and notify()
    
## MONITORS
synchronization construct that allows thread to have both MUTEX (mutual exclusion using locks)
AND COOPERATION (the ability to make threads wait for a certain condition to be true, using WAIT-SET)

- along w/ data that implements a LOCK, every Java object is logicially associated w/ data
that implements a WAIT-SET. 
    - LOCKS 
        - help threads work independently on shared data w/o interfering w/ one another
    - WAIT-SET 
        - helps threads COOPERATE w/ one another to work together towards a common goal
        - (i.e. all waiting threads will be moved to this WAIT-SET)
        - all threads are notified once lock is released
        - HELPS  building monitors w/ additional help of a MUTEX (Lock)
        
### MUTUAL EXCLUSION
This is the process by which threads are prevented from interfering with each other. I like to think
of this as the guts of a MUTEX

- a MONITOR is a container that holds an object instance that can be occupied by only
1 thread at a time. 
    - this instance contains data which needs "protection" from concurrent access
    
- ENTERING THE MONITOR
    - only a single thread is allowed access to the monitor at a given time. By providing
    exclusive access to the data within the held object instance, we provide concurrency
    protection.
    - ENTRY-SET
        - when a thread enters the monitor it is placed in the ENTRY-SET (a queue for threads
        that want/need to access the object instance held by the monitor)
    
- ACQUIRING THE MONITOR
    - if the monitor isn't OWNED, then the thread moves from the ENTRY-SET and acquires a lock
    - if the monitor is OWNED, then the thread remains int he ENTRY-SET until it is able
    to acquire the lock via notify()/notifyAll()
    
- OWNING THE MONITOR
    - this is the period of runtime when a thread holds a lock against the object instance
    held by the monitor
    
- RELEASING THE MONITOR
    - when the thread has completed its task(s), it releases the lock and exits the monitor.
    
NOTE: mutual exclusion is only important when MULTIPLE THREADS are SHARING data or
some other resource.
    - if 2 threads aren't working on the same resource/data, then there is no contention
    and a mutex is unnecessary. 
    
### COOPERATION
Cooperation helps multiple threads work together to complete some common goal. 

USE CASE: cooperation is important when one thread needs data to be in a particular state,
and another thread is responsible for putting that data in the needed state. 
    - this is the producer/consumer problem!!!
    
- Similar process as mutual exclusion but steps are as follows

(Aka as a WAIT and NOTIFY or SIGNAL and CONTINUE monitor)
1. thread ENTERS the monitor and is stored in the ENTRY-SET
2. if available, thread ACQUIRES lock/monitor and OWNS monitor
3. thread finishes (temporarily) and RELEASES monitor/lock, and is stored in the WAIT-SET
4. thread can reacquire lock from WAIT-SET
    - this is when they are in a wait() state
5. thread completes and RELEASES lock and EXITS

The challenge here is that there are different conditions for the collaborating threads 

    EX: 
        READ and WRITE
        - Write thread fills a buffer to a given capacity
        - read thread reads buffer until empty
        


