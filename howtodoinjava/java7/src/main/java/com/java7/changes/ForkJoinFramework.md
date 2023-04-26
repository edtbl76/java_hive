# Fork/Join Framework

Fork and Join Framework
- tries to solve the problem of providing effective use of parallel cores in a Java program.
- divide-and-conquer algorithm

![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/java7/src/main/java/com/java7/changes/fork-join-framework.png)

HOW IT WORKS
- breaks task at hand into mini-tasks until the mini-task is simple enough that it can be solved w/o
further division
- ideally no working thread is ever idle.
- work-stealing algorithm that where a worker that becomes idle will "steal" the work from the busy workers.

## ForkJoinPool

- specialized implementation of ExecutorService, implementing a WORK-STEALING ALGORITHM

    
    EX:
        ForkJoinPool pool = new ForkJoinPool(numOfProcs);
        numOfProcs = Runtime.getRunTime().availableProcessors();
        
    
- no matter how large of a pool you initialize, THE POOL ADJUSTS ITS SIZE DYNAMICALLY  IN 
AN ATTEMPT TO MAINTAIN ENOUGH ACTIVE THREADS
- this pool doesn't need to be shut down upon program exit
    - threads are in DAEMON MODE
    - (this is a departure from most other ExecutorServices) 
    
### METHODS

execute()
- (desired async exec)
- call its form method to split work between multiple threads

invoke()
- (await to obtain result, calls invoke method against the pool)

submit()
- returns a future object that can be used for checking status + obtaining result on completion.

## ForkJoinTask
- abstract class for creating tasks that run inside ForkJoinPool
- 2 subclasses
    - RecursiveAction
        - void
    - RecursiveTask
        - returns object of specified type
        - METHODS
            - isDone() 
                - (if task is completed at all)
            - isCompletedNormally()
                - (if task completes w/o failure or cancellation)
            - isCancelled() 
                - if task is cancelled
            - isCompletedAbnormally()
                - (if task completes w/ failure or cancellation)
                

## fork()
- fork sends tasks to the pool that will execute it if it has a free worker-thread or it can create 
a new one. 
- this method returns IMMEDIATELY so the task can continue processing the content of the folder. 
(it tries to be non-blocking, but it isnt)

## join()
- join, when called in a task, waits for the finalization of its execution and returns the result of
that execution. 

## Fork/Join vs. ExecutorService
- The biggest difference is the WORK STEALING ALGORITHM. 
    - when a task is waiting for finalization  of subtasks in Fork/Join, the worker thread (the one
    executing the tasks) looks for other tasks that haven't been executed and executes them
    - ExecutorService doesn't do that. 
