# Java Thread Scheduling
- JVM required to implement a PREEMPTIVE, PRIORITY-BASE SCHEDULER 
    - each thread is assigned a certain priority
        - (a positive integer in a WELL-DEFINED RANGE)
    - PRIORITY CAN BE CHANGED BY DEVELOPER ONLY
    - JVM will NOT change the priority of a thread, even if the thread has been running for
    a very long time

- "CONTRACT" between JVM and underlying OS
    - OS must "generally" choose to execute the Java thread with the HIGHEST priority
    - (this is what it means when we say JVM implements a PRIORITY-BASED SCHEDULER)

- PRE-EMPTION
    - this means that when a thread that has a HIGHER PRIORITY than the currently executing
    thread starts, the executing thread can be PRE-EMPTED by the new higher priority
    thread. 
    
- NOW THAT YOU HAVE LEARNED THIS.... forget it. 
    - The worst part about all of this is that this CONTRACT isn't absolute. 
    - the OS might sometimes choose to run a lower-priority thread, or it might not
    pre-empt. 
    
    
## TIME-SLICING
While MOST Operating Systems will time-slice threads, Java doesn't necessarily slice them. 
NOTE: time-slicing isn't the same as pre-emption. 
- preemption is the selection of a higher priority thread in comparison to lower priority
threads. 
- time-slicing is when a process is allowed to run for X amount of time before it has to 
give up its lock. 
    - this is a way to solve livelock situations where high priority threads execute in a 
    "put baby in the corner" manner such that the low-priority threads never get a chance
    to execute. 