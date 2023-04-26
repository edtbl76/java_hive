# 1.1 A Brief History of Concurrency

## Processes
- isolated, independently executing programs
- OS allocates resources
    - memory
    - file handles
    - security credentials
- can communicate w/ other processes 
    - sockets
    - signal handlers
    - shared memory
    - semaphores
    - files
    
### Motivating Factors for Simultaneous Execution of Processes

1. Resource Utilization
    - while programs are waiting for external operations (i.e. I/O), it is 
    more efficient to use that wait time to allow another process/program to run
2. Fairness
    - users/processes have equal claims to system resources
    - more efficient to allow processes share resources via fine-grained time
    slices than to allow each process to run from start->finish
3. Convenience
    - easier/more desirable to write several programs that each perform single/few
    simple tasks and have them coordinate w/ each other. 

## Threads (a.k.a lightweight processes) 
- modern OS' treat THREADS (not processes) as basic units of scheduling
- allow multiple streams of program control flow to coexist within a process
    - via "natural decomposition for exploiting hardware parallelism on multiprocessor
    systems."
- share process-wide resources
    - memory
    - filehandles
- have thread-specific (ie.. unshared) resources
    - program counter
    - stack
    - local variables