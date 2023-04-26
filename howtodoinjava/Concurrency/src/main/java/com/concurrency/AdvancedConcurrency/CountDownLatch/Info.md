# CountDownLatch
- introduced in JDK 1.5 w/ BlockingQueue, ConcurrentHashMap, Semaphore

- allows a java Thread to wait until other set of threads complete their tasks. 

## HOW IT WORKS
- has a counter init'd with a number of threads.
- counter decrements each time a thread completes its execution
- when the thread hits 0, the thread waiting on the latch can execute.

## USECASES
Max Parallelism
- if we start x number of threads at the same time, init counter to 1, and then
make all threads wait() on the latch, then a single call to countdown() will
resume exec for all of the threads waiting on the latch

Wait N Threads to complete before start exec
- this is obvious, but we set a counter to N, and make our thread wait until it hits 0. 

Deadlock Detection <---- HUGE
- use N threads to access a shared resource
    - diff # of threads in each test phase. 
    - this tries to create a deadlock.
