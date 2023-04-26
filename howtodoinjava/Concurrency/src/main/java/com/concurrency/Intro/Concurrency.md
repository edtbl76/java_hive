# CONCURRENCY
- ability to run several programs (or parts of programs) in parallel. 

## THREAD
- a lightweight process which has its own call stack, but can access shared data of 
other threads in the same process. 

- by default, Java applications run in a single process. 
    - inside a java application, it is possible tto work w/ MANY threads to achieve
    "parallel processing" (aka concurrency)
    
### THREAD SAFETY
- CORRECTNESS
    - "a class that conforms to its specification"
    
- THREAD SAFE
    - a class is considered "THREAD-SAFE" when it continues to behave CORRECTLY when
    accessed from multiple threads.
        - regardless of the scheduling or interleaving of those threads by the
        runtime environment
        - AND w/ no additional synchronization or coordination on the part of the calling
        code.
        
    - if you don't like the term correctness... you can approach it from the 
    negative:
        - a thread that is no more broken in a multi-threaded state than it is in a
        single-threaded state. 
    
## JAVA CONCURRENCY
- java.lang.Thread
    - basis of ALL concurrency concepts in Java
- java.lang.Runnable
    - interface used to abstract the thread behavior out of the thread class.
    
## CONCURRENCY vs. PARALLELISM

### CONCURRENCY
- multiple tasks which start, run and complete in overlapping time periods. 
    - non-specific order. 
    - results in the APPEARANCE of all tasks running in parallel.
    
- applicable w/ 2 or more tasks. 
    - they take advantage of CPU TIME-SLICING
        - (OS feature where each task runs part of its instruction set, then goes into 
        a waiting state.)
        - when first task is waiting, second task begins executing. 
    - the purpose of CONCURRENCY is to take advantage of idle time while a single thread/task
    is waiting for response some from blocking operation (network, disk, or even
    higher priority tasks.)                                                              

- RESULT of CONCURRENCY: composition of independently executing processes
- PURPOSE of CONCURRENCY: dealing with lots of things at once.

### PARALLELISM
- multiple tasks OR several parts of a unique task run att the same time on a multi-core
processor. 

- Does NOT require 2 tasks.
    - PHYSICALLY runs parts of tasks (OR MULTIPLE TASKS) at the same time
    using a multi-core CPU infrastructure, by assigning one core to each task or sub-task.
    
- requires HARDWARE w/ multiple processing units. 

- RESULT OF PARALLELISM: simultaneous execution of (possibly related) computations
- PURPOSE OF CONCURRENCY: doing lots of things at once.

### APPLICATIONS w/ CONCURRENCY and PARALLELISM

CONCURRENT APPLICATION (but not PARALLEL)
- it processes more than one task at the same time
- no two tasks are EXECUTING at the same time.
    - one or more tasks must be waiting
    
PARALLEL APPLICATION (but not CONCURRENT)
- multiple tasks OR sub-tasks are executing at the same time
    - each on a single CPU core. 

APPLICATION (neither CONCURRENT not PARALLEL) 
- all tasks process one after another serially

CONCURRENT PARALLEL APPLICATION 
- processing multiple tasks concurrently in multi-core CPUs at the same time. 

    
