# 5.5 Synchronizers

### Blocking Queue review
unique type of collection
- act as containers for objects
- can coordinate control flow of producer/consumer threads
    - (because take() and put() block until queue enters desired state)
        - take() blocks until work is available to consume
        - put() blocks until there is space available to place work
        

### Synchronizer
- any object that coordinates the control flow of threads based on its state. 
- Blocking Queues can act as synchronizers
- Other examples
    - semaphores
    - barriers
    - latches

#### Structural Properties of a Synchronizer
- encapsulate state that determines whether threads arriving at the synchronizer should be
    - allows to pass
    - forced to wait
- provide methods to manipulate encapsulated state
- provide methods to wait efficiently for synchronizer to enter desired state. 

## 5.5.1 Latches
These are types of threads that delay progress of threads until it reaches its
"TERMINAL" state

### Gate-like Behavior
- gate is closed (no threads may pass) until latch reaches terminal state
- gate opens at terminal state, allowing threads to pass.
    - once gate is opened, it cannot change state again
    - i.e. it remains open "forever"
    
### Primary Use Cases
Latches are typically used to guarantee that certain tasks don't proceed until other
singleton/one-time activities complete: 

#### Ensure Compute doesn't proceed until required resources are initialized
- binary latch (2 state) is used to indicate whether or not a required resource has been
initialized
- tasks that require the resource to be initialized will wait 

#### Ensure that a service doesn't start until other services on which it depends have alse started
- this is essentially the concept of liveness-based dependencies. 
- if I'm starting a service, and I need resources from other services in order to start, then I need
to wait on them. 
    - (It's typically customary for READINESS to be favored to liveness)
    
#### Waiting on users to be ready to proceed
- This is readiness. Our service/thing will block will wait for all dependencies to be 
ready to respond to requests before releasing the latch

### CountDownLatch
flexible latch impl that covers the 3 primary use cases
- it allows 1+ threads to wait for a set of events to occur

#### Structure of CoundDownLatch
Latch State = counter initialized to COUNT
- COUNT =  positive number that represents the number of events to wait for
    
countDown()
- method that decrements the counter
- indicates that an EVENT has occurred. 
    
await()
- methods that wait for counter to reach zero
- only happens when all EVENTS have occurred
- if COUNT != 0 on entry
    - await() BLOCKS until COUNT == 0
    - waiting thread is interrupted
    - OR
    - wait times out.
        
    
    EXAMPLE:
    
        public class TestHarness {
            public long timeTasks(int nThreads, final Runnable task) 
                    throws InterruptedException {
                final CountDownLatch startGate = new CountDownLatch(1);
                final CoundDownLatch endGate = new CoundDownLatch(nThreads);
                
                for (int i = 0; i < nThreads; i++) {
                    Thread thread = new Thread() {
                        public void run() {
                            try {
                                startGate.await();
                                try {
                                    task.ruun();
                                } finally {
                                    endGate.countDown();
                                }
                            } catch (InterruptedException ignored) {}
                        }
                    };
                    thread.start();
                }
                
                long start = System.nanoTime();
                startGate.countDown();
                endGate.await();
                
                long end = System.nanoTime();
                return end-start;
            }
        }

#### TestHarness Example Explanation
Creates 2 latches
- startingGate has a COUNT of 1 (1 event)
- endingGate has a COUNT equal to no. of worker threads

WorkerThreads
- each thread first waits on starting gate
    - this ensures that no worker starts working until they are ALL ready to start
- last thing each thread does is count down on ending gate. 

MasterThread
- master thread (main) can wait for the endGate to finish. 
    - endGate's count is tied to number of workers
    - therefore, when count is 0, no more workers are left, (i.e. work is complete)
    - finally it calculates how long the entire Harness took to execute
    
WHY? 
- The test was to measure the elapsed time of N tasks CONCURRENTLY
    - all threads are kicked off at the same time to test concurrent load
    - main thread waits for LAST thread to finish, rather than EACH thread
- if threads are started one after another:
    - threads started earlier have a "head start"
    - degree of contention varies over time as number of active threads increases/decreases

## 5.5.2 - FutureTask
latch-like structure that implements Future
- a Future describes an abstract results-bearing computation
- Once a FutureTask is completed, it never changes state again.

### Callable
A Callable is the "results-bearing" equivalent to a Runnable with is implemented by a Future
- it can be in one of three states
    - waiting to run
    - running
    - completed

Completion does not mean "Successful" Completion. It can be ANY form of completion
- Normal Completion (finishing the work)
- Thread cancellation (presumably via interrupt)
- Exception (failure)

Tasks described by Callable can throw both CHECKED and UNCHECKED exceptions

### Future.get()
- The behavior of a get depends on the state of the task.
    - if the task is completed, then the results are returned immediately
    - if the task is waiting, then get() blocks until
        - the task completes (and returns the result)
        - throws an exception.
        
### FutureTask Responsibility
Conveying the result from a thread EXECUTING a computation to the thread RETRIEVING the result
- the specification of the FutureTask guarantees that this transfer is a safe publication of the
result.

### Use Cases
- MOSTLY Used by Executor Framework to represent asynchronous tasks
- ALSO used to represent any (potentially) long-running computation that can be started before
the results are needed. 

### Preloader Example

    

    Example:
    
        public class Preloader {
            private final FutureTask<ProductInfo> future = new FutureTask<>(
                    new Callable<ProductInfo> {
                public ProductInfo call() throws DataLoadException {
                    return loadProductInfo();
                }        
            });
            
            private final Thread thread = new Thread(future);
            
            public void start() {
                thread.start();
            }
            
            public ProductInfo get() throws DataLoadException, InterruptedException {
                try {
                    return future.get();
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof DataLoadException) {
                        throw (DataLoadException) cause;
                    } else {
                        throw launderThrowable(cause);
                    }
                }
            }
        }
        
        public static RuntimeException launderThrowable(Throwable throwable) {
            if (throwable instancof RuntimeException) {
                return (RuntimeException) throwable;
            } else if (throwable instanceof Error) {
                throw (Error) throwable;
            } else {
                throw new IllegalStateException("Unchecked", throwable);
            }
        }
        
        
- Preloader creates a FutureTask
    - describes the loading of product info from a database and a thread in which computation
    is performed
    - independent start method is provided
        - antipattern to call start from 
            - constructor
            - static initializer
    - Task code defines the exceptions it can throw 
        - DataLoadException and InterruptedException in this case
        - these are wrapped in an ExecutionException and rethrown from Future.get()
            - wrapped exceptions are NOT desirable
            - we aren't handling unchecked CancellationException
            - ExecutionException is returned as a Throwable
                - (Harder/more inconvenient to deal with)
        - cause of ExceptionException will be one of 3 failures:
            - exception thrown by Callable
            - RuntimeException
            - Error
        - check for known exceptions first
        - call "launderThrowable()"
            - provided as a utility method to encapsulate the handling of unchecked exceptions
        
## 5.5.2 Semaphores
Semaphores (a.k.a counting semaphores) are used to control the number of tasks that can
- access a certain resource
- perform a given action at the same time

### Use Cases
- resource pool implementation
    - db connection pools
- impose collection boundaries    

### Mechanics
semaphores manage a set of virtual PERMITS
- initial # of permits is passed to semaphore constructor 
    - activities can acquire available permits
        - acquire() blocks if no permits are available. 
    - activities release permits to be returned to pool when they are done
        - release() returns permit to semaphore   
        
### Binary Semaphore
- special use case semaphore that has an initial count of one. 
- typical use case is as a MUTEX w/ nonreentrant locking semantics. 
    - whoever has the permit, has the mutex
    
### Resource Pools
- goal is for requests to BLOCK if that request is against an empty pool
    - unblock once the pool becomes nonempty
    
    
    EXAMPLE:
    
        - initialize semaphore to pool size SIZE
        - acquire() permit before fetching resource from pool.
        - release() permit after putting putting resource back into pool
        - acquire() blocks until pool becomes nonempty.    
        
        NOTE:
            - an easier method for creating blocking object pools:
                - use a BlockingQueue to hold pooled resources.
                

### Bounded Blocking Collection
- init semaphore to desired max size of collection
- add() 
    - acquires a permit before adding elements to underlying collection
    - if add doesn't actually add an element, permit released immediately
- remove()
    - releases a permit, allowing more elements to be added.


    Example:
    
        public class BoundedHashSet<T> {
            private final Set<T> set;
            private final Semaphore semaphore;
            
            public BoundedHashSet(int bound) {
                this.set = Collections.synchronizedSet(new HashSet<>());
                semaphore = new Semaphore(bound);
            }
            
            public boolean add(T o) throws InterruptedException {
                semaphore.acquire();
                boolean wasAdded = false;
                
                try {
                    wasAdded = set.add(o);
                    return wasAdded;
                } finally {
                    if (!wasAdded) {
                        semaphore.release();
                    }
                }
            }
            
            public boolean remove(Object o) {
                boolean wasRemoved = set.remove(o);
                if (wasRemoved) {
                    semaphore.release();
                }
                return wasRemoved;
            }
        }
        
        
## 5.5.4 Barriers

### Latch Review
- Latches are single-use (Disposable) objects. 
    - once a latch enters the terminal state, it can't be reset.
- they are used for
    - starting a group of related activities
    - waiting for a group of related activities to complete
    
### Barriers (Latch's Cousin)
- SAME AS LATCH
    - block a group of threads until some event has occurred. 
- DIFFERENT FROM LATCH
    - all threads must come together at "the barrier point" at the same time in order to 
proceed.

Latches wait for events <br>
Barriers wait for other threads


    EXAMPLE: 
    
        Shopping Rendezvous. 
        
            Everyone meet in the foot court at 5 to determine what the next plan is.
            

#### CyclicBarrier
- allows fixed number of parties to "rendezvous" repeatedly at BARRIER POINT
- useful in parallel iterative algorithms that decompose large problem into fixed no. of 
independent subproblems.

##### Mechanics
- threads call await() at barrier point
- await() blocks until all threads are at barrier point. 
- if all threads are at barrier point, it has been PASSED
    - all threads are released
    - barrier is reset to be used again
- if thread times out or thread blocking in await() is interrupted, barrier is BROKEN
    - all outstanding calls to await() terminate w/ BrokenBarrierException

If barrier is passed
- await() returns unique arrival index for each thread
    - used to "elect" a leader that takes some special action in the next iteration
- CyclicBarrier supports a Runnable argument passed to the constructor called a "Barrier Action"
    - this is the Runnable that is executed in one of the subtask threads 
        - AFTER barrier is successfully passed
        - BEFORE blocked threads are released 
        
##### Common Use Case - Simulations
Simulations
- work to calculate one step done in parallel
- all work associated w/ a given step must complete before advancing to the next step. 


    EXAMPLE:
    
        n-body particle simulations
            - each step calculated an update to the position of each particle
                (based on the locations and other attributes of the other particles) 
            - waiting on all updates for step k (by waiting on a barrier) guarantees that
                all of that work is completed before moving on to step k + 1
                

##### Problem Partitioning - Simulations Continued
- When trying to parallelize a simulation, it isn't feasible to assign a separate thread
to each element
    - i.e. if we "processed every strand of DNA in the body"
    - the overhead of COORDINATING the elements would be larger than the COMPUTATION itself
        - this is a common problem in distributed systems and one worth considering. 
        - coordination is overhead. It's not part of the business logic, so we want
        to limit how much time and effort is spent allocating and managing our work. 
- SOLUTION:
    - partition problem into subparts
    - each thread solves a "subpart" and merges the results 





    EXAMPLE
    
        public class CellularAutomata {
            private final Board mainBoard;
            private final CyclicBarrier barrier;
            private final Worker[] workers;
            
            public CellularAutomata(Board board) {
                this.mainBoard = board;
                int count = Runtime.getRuntime().availableProcessors();
                this.barrer = new CyclicBarrier(count, 
                    new Runnable() {
                        public void run() {
                            mainBoard.commitNewValues();
                        }
                    });
                this.workers = new Worker[count];
                for (int i = 0; i < count; i++) {
                    workers[i] = new Worker(mainBoard.getSubBoard(count, i));
                }
            }
        
            private class Worker implements Runnable {
                private final Board board;
                
                public Worker(Board board) {
                    this.board = board;
                }
                
                public void run() {
                    while (!board.hasConverged()) {
                        for (int x = 0; x < board.getMaxX(); x++) {
                            for (int y = 0; y < board.getMaxY(); y++) {
                                board.setNewValue(x, y, computeValue(x,y));
                            }
                        }
                        
                        try {
                            barrier.await():
                        } catch (InterruptedException e) {
                            return;
                        } catch (BrokenBarrierException e) {
                            return;
                        }
                    }
                }
            }
            
            public void start() {
                for (int i = 0; i < workers.length; i++) {
                    new Thread(workers[i]).start();
                }
                mainBoard.waitForConvergence();
            }
            
        }
        
Example Code Explanation
- class partitions the board into N parts
    - N = # of CPUs available
    - assigns each part to a thread. 
- at each step:
    - worker threads calc. new values for all cells in their part of the board. 
    - when worker is done, it "reaches the barrier"
- once barrier is reached for all threads
    - Barrier Action commits new values to the resultant data model. 
    - worker threads are released
    - barrier is reset to compute next step of calculation
        - (includes an isDone marker that is used to signal the end of work such that
        no further iterations are necessary)
        
#### Exchanger
- 2 party barrier in which parties exchange data at the barrier point
- USES
    - when parties perform asymmetric activities
        - one thread fills buffer w/ data
        - one thread consumes data from buffer. 
    - this exchange should constitute a SAFE publication of both objects to the other
    party. 
- EXCHANGE TIMING
    - depends on responsiveness requirements
    - easiest method provides minimum no. of exchanges, but considerable latency
        - filling task exchanges once buffer is full
        - emptying task exchanges once buffer is empty
    - another mechanism is 2-pronged
        - exchange when full/empty
        - AND exchange after elapsed period of time.
        
