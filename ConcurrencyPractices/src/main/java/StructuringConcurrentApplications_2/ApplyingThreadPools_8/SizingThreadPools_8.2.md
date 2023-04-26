# 8.2 Sizing Thread Pools 

### Sizing Approach 
- depends on 
    - types of tasks submitted
    - characteristics of deployment system
- pool sizes should be provided by configuration or dynamically
determined from **Runtime.availableProcessors**
    - RARELY hard-coded
- Perfect Sizing not necessary
    - avoid TOO BIG scenarios
        - threads compute for scarce CPU/memory resources
            - high mem usage
            - possible resource exhaustion
    - avoid TOO SMALL
        - throughput is poor
        - cores go unused, despite available work.
        
### Sizing Requirements
- Need Understanding Of
    - computing environment
        - how many cpus? 
        - how much memory? 
    - resource budget
        - scarce resource requirement? 
            - (i.e. JDBC connections)
    - nature of your tasks
        - computation vs. IO workload? 
            - one or the other (or a mix)
   
#### Task Categories
If tasks are heterogeneous (i.e. different behaviors), it makes sense
to create different thread pools so that the pools can be tuned to 
those varied behaviors. 

    Remember that parallelization works best for INDEPENDENT, 
    HOMOGENEOUS workloads
    
#### Compute-Intensive Tasks
- N-proc systems (where N = number of CPUs) work best
- Thread Pool = N + 1
    - The extra runnable thread is an "oh shit" thread that ensures
    optimum utilization in the event that "shit happens" on a computation
    thread
        - i.e. page fault or some other pause
        
#### I/O (Blocking) Tasks
- A larger thread pool is required, because not all threads are schedulable at all times. 
- This is challenging because we have to ESTIMATE ratio of wait time to comp;ute time

#### Thread Pool Size Math

    N = number of CPUS
        
        int N = Runtime.getRuntime().availableProcessors();
        
    U = target utilization (0 <= U <= 1)
    W = wait (idle time) 
    C = compute time
    T = number of threads
    
        T = N * U * (1 + [ W / C ])
        
### Non-CPU Resource Considerations
- NON-CPU resources that might be managed by a thread pool
    - memory
    - file handles
    - socket handles
    - database connections
- Calculating Non-CPU Resources
    - add up how much of X resource each task requires
    - divide the result into the total quantity available
    - RESULT = "upper bound" of pool size.
    
#### Size of Thread Pools and Resource Pools
- When a task requires pooled resources (i.e. db connections, network connections)
    - The "effective size" of the thread pool is limited by the connection pool size
- (Reverse) When the only consumers of a connection are pool tasks, 
    - The "effective size" of the resource/connection pool is limited by the thread pool size.

       