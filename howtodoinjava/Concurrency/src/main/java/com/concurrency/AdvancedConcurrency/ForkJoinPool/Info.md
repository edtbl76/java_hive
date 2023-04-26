# Fork/Join Framework
- JDK 7 feature that allows distribution across multiple cores (forking) and then merging it
back together (join) to return a unified result set.

- same approach as Recursion
    - break large task up into smaller tasks until smallest sized task is simple enough to be 
    resolved without further division.
    
- no worker thread is idle. (THIS IS PARALLELISM)
    - WORK-STEALING algorithm allows an "idle" worker (or more accurately a worker that has
    just completed a task) to steal work from the queues of other workers who are busy.
    
## ForkJoinPool
specialized implementation of ExecutorService
- adds the WorkStealing algorithm. 


    EX: 
        ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);
        
            numberOfProcessors = Runtime.getRunTime().availableProcessors();
            

NOTE: if you don't specify the number of processors it will use the call mentioned in the
example above.         

- threads in FJP don't need to be shutdown upon program exit, because all of the threads 
operate in Daemon Mode by default. 

### dispatching tasks to FJP
execute()
    - Asynchronous Exec
    - call its fork() method to split work between multiple threads
invoke()
    - waits to obtain results
submit()
    - returns a Future that can be used for checking status and obtaining result on completion.
    
## ForkJoinTask
Abstract class for creating tasks that run in an FJP

### Subclasses
RecursiveAction 
- (void, no return value)

RecursiveTask 
- (return value, returns an object of specified type)

### METHODS
isDone() 
- true if task completes in ANY WAY

isCompletedNormally()
- true if task completes without cancellation or exception

isCancelled()
- true if task is cancelled

isCompletedAbormally()
- true if task is cancelled or throws an exception

