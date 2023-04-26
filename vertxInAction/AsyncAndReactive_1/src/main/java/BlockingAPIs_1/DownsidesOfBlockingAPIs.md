## Blocking API Diagram
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/AsyncAndReactive/src/main/java/BlockingAPIs/Screen Shot 2019-11-01 at 3.50.02 PM.png)
 Modern OS can handle a few thousand concurrent threads. 


# Problems with Blocking APIs
(please see SynchronousEcho example)
- each incoming connection results in the allocation of a new thread
    - threads are EXPENSIVE to set up
    - threads are EXPENSIVE to use. 
- we usually need more threads than incoming network connections
- modern architectures are often in containerized/virtualized environments where resources
such as CPU time, Memory etc. are limited
    - most modern arch's solve this by spinning up more instances to handle increased loads, 
    but this translates DIRECTLY to increased operating costs. 


## Why are Threads Expensive
As the number of threads grow, we put more pressure on the OS
- each thread needs more memory (so there is less storage available for data, vars etc.)
- as the number of threads increase, there is more pressure on the CPU to allocate CPU
time to threads. 
    - if the "# of threads" > "CPU resources", then threads will have to wait for CPU time, 
    which is a CPU stall.
    
    
## Thread Pools
The benefit of a thread pool is that it allows us to reuse threads after a connection has
been closed, but this solution only goes so far. 
    - we still need N threads for N connections
    - rather than SOLVING the cost, we are just WARMING the resources. 
    
