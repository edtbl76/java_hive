# 12.1 Testing for Correctness

### Basics
- (same as for sequential classes)
    - identify invariants (test cases)
    - identify postconditions that are amenable to mechanical checking
    

- well defined specifications include the invariants and postconditions that need to be checked
     - more often than not, this is an adventure in "iterative specification discovery"
     
     
     NOTE: It may not be cost-effective to create an exhaustive specification at design time or 
     requirements definition. 
     
        PMs are ultimately responsible for pulling the cord to get the engine started. 
        
        Designers and Implementers are going to need to crack this nut open as we take passes
        at coding and designing it. 
        
        The holistic view is NEVER apparent from the get go. 
        

### Example Code - Bounded Buffer
(See Examples.BoundedBuffer)

    NOTE: 
    
        This is for EXAMPLE only. 
        
        If you want to use a bounded buffer you should use:
        
            ArrayBlockingQueue or LinkedBlockingQueue
        
#### Characteristics
- fixed-length array-based buffer
- provides 2 blocking methods (**put** and **take**) for mutating the buffer
- the acquisition/release of permits from the two semaphores are performed in converse order
between put and take, such that exiting from either of these methods presents an invariant:


    The sum of the counts of both semaphores will always equal the bound/capacity. 
    

## 12.1.1 Basic Unit Tests
(Examples refer to Examples.BoundedBuffer Example Class)

### Similar to Sequential Contexts
- Create BoundedBuffer
- call methods
- assert postconditions and invariants

### BoundBuffer Invariants
- freshly created buffer should identify as empty
- freshly created buffer should identify as not full
- we should be able to insert N elements into a buffer w/ capacity N w/o blocking
    - buffer should ID as full and non-empty
    
    
    
        NOTE: 
        
        Examples of testing these invariants are provided in Examples.tests.BoundedBufferTest
        
        - used JUnit 5.4
        - look at the FIRST 2 tests
            - testIsEmptyWhenConstructed()
            - testIsFullAfterPuts()
        - these are ENTIRELY sequential. 
        
        
### Sequential Testing in Concurrent Classes
- This is actually very helpful, because it allows us to rule out low-hanging fruit types of 
problems that aren't related to concurrency. 

- Searching for race conditions, liveness issues, etc. are much harder to identify and resolve
than sequential problems. There is value in validating that a problem doesn't have a simple solution 
before investing considerable resources assuming from the start that it is hard. 

## 12.1.2 Testing Blocking Operations
Requirements for testing blocking operations
- it must be CLEAR whether or not the tests passed
- failure information is reported SOMEWHERE for debugging/diagnostic purposes.

### Obviously...
- In order to test concurrency, we have to introduce multiple threads. 

### Naturally...
- most test frameworks aren't concurrency friendly
    - can't create threads
    - can't monitor threads to ensure that death wasn't under suspicious circumstances (unexpectedly)
    
    
- the framework usually doesn't know which test the thread is associated with 
    - no test-to-thread affinity
    - this means we have to manually wire success/failure info back to the main test runner thread
    so that it can be reported and the test thread doesn't wait indefinitely. 
    
### JSR 166 Expert Group
- created a special base class
    - http://gee.cs.oswego.edu/cgi-bin/viewcvs.cgi/jsr166/src/test/tck/JSR166TestCase.java
    - provides methods to relay/report failures during **tearDown**
    - every test must wait until all the threads it created, terminate
    
### Doin' the testing
- JSR166 approach isn't always necessary
    - always keep the basic requirements of testing in mind (mentoined at the beginning of
    this section)



    Testing Blocking Methods is analagous to testing methods that throw exceptions
    
        If the method returns normally, when blocking/exceptions are expected, 
        
            the test has failed. 

- if a method is supposed to block under certain conditions, success is only established when the
thread under test does NOT proceed. 


#### Problem 1 - Unblocking
- If a blocking method successfully blocks, in order for us to complete the test, we have to 
unblock the thread. 


- SOLUTION 1:
    - start blocking method in a separate thread from the test runner. 
    - once the blocking method successfully blocks: we interrupt it and then assert that the blocking
    method has completed. 
    
    
    In order for this to work, the blocking methods have to respond to interruption
        - return early
        - throw InterruptedException
        

##### Challenges with Solution 1:
- waiting for a thread to block is challenging:
    - we have to "guess" how long the operations we are testing are going to take. 
    - we have to tell the test to "wait longer than that"
    

    Prepare to be wrong. This is hit or miss, and it means that you are going to have
    tests that may very well provide false positives (or negatives) 

### Example of Testing Blocking Code
- Third test in Examples.test.BounderBufferTest
    - testTakeBlocksWhenEmpty()
        - ensures that take() properly blocks
        - ensures that when interrupted, it properly throws **InterruptedException**

#### Why do we use Thread.join()?
- This ensures that the test completes even if take() gets stuck.


    NOTE: 
        Testing proper termination w/ Thread.join() is one of the few cases
        it is considered appropriate to subclass Thread.
        
        This approach is an alternative to what we did in order to test that
        the taker Thread unblocks after an element i splaced in the queue by the main thread.
        

#### Why don't we use Thread.getState()
- This is an unreliable approach 
    - the JVM can choose to implement blocking via SPIN-WAITING
        - this means that there is NOTHING that requires a thread to EVER enter
        the WAITING or TIMED_WAITING states.
        - (See JVM Blocking Implementation in 11.3)


- Spurious(false/fake) wakeups from **Object.wait** or **Condition.await** are allowed. 
    - This allows threads that are currently in TIMED_WAITING/WAITING state to temporarily
    transition to RUNNABLE even if the condition for which it is waiting isn't actually true
    


    NOTE: 
    
        The result returned from Thread.getState() should NEVER EVER be used for concurrency 
        control
        
            - it was designed ONLY as a source for debugging info. 
            - it was not designed for production or test use cases. 
    
## 12.1.3 Testing Safety

### Recap
- The previous tests (The first 3 tests in Examples.tests.BoundedBufferTest)
    - PROS
        - exercise invariants and properties of the class
    - CONS
        - unlikely to validate the problems caused by race conditions.
        
### Testing Concurrent Class 
- In order to ensure that the class performs properly under unpredictable concurrent access
    - we need multiple threads
    - those threads must call our methods (**put** and **take** in this case) over some amount of time
    - we have to ensure that SHIT HAPPENS didn't happen during the test case. 
    
#### Chicken and Egg Nature of Concurrency Tests
- Tests constructed to validate concurrent classes are also concurrent programs
- The tests are sometimes harder to create than the apps themselves


### Best Practices

    The challenge to creating effective safety tests for concurrent classes:
    
        - identifying easily checked properties that will (w/ HIGH probability) fail if
        something goes wrong
        
            - at the same time, we must prevent the "failure-auditing" code from
            artifically limiting concurrency.
            
        
    Ideally, we want to ensure that checking test properties doesn't require any 
    synchronization


- A solid approach for classes that follow the producer/consumer design is to validate what comes out
of the data structure we have created:
    - (BoundedBuffer example is a Producer/Consumer design)
    - 1 - Ensure that everything we put into a queue/buffer comes out of it. 
    - 2 - Ensure that nothing else does. 
    
#### Naive Version - "Shadow List"
- insert element into a shadow list when it is put on the queue
- remove the element from the list when it is removed from the queue
- assert that shadow list is empty when the test has finished


- CONS
    - This approach distorts the scheduling of test threads by adding unwanted synchronization 
    (and blocking) that would be required to mutate the shadow list. 
    
#### Better - Compare Checksums
- We can use an order-sensitive checksum function that compares the checksums of elements as
they are enqueued and dequeued. 
    - if they match: PASS


- This approach tests both:
    - the correctness of the values of elements being enqueued/dequeued
    - the correctness of the ordering of the elements being enqueued/dequeued

    
- CONSTRAINTS (not really CONS)
    - This works best when there is a single consumer performing the puts, and a single consumer 
    performing the takes. 

##### Extending Checksum Approach to Multi-Producer/Multi-Consumer Design
- This approach is slightly more challenging than the previous as it requires that the
checksum function is INSENSITIVE to the order in which elements are combined. 
    - Multiple checksums must be combined after the test 
        - (any commutative operation such as addition or XOR)
        
    
    It is important for the checksums to be combined AFTER the test, because performing 
    this inside the test would require synchronizing access to the checksum field. 
    
        - This can distort the timing of the test or induce artificial concurrency 
        bottlenecks. 
    

##### Solving RNG Problem
- Make sure that smart compilers can't guess checksums
    - this guarantees that tests are actually testing what we expect
    - Test Data should be randomized. (i.e. non-consecutive!) 
  
    
- Don't use a shitty RNG (Random Number Generator)
    - If you are going to randomize to ensure veracity of tests, then use a good RNG
    - Random Number Generation can create couplings between classes and timing artifacts because most 
    RNG classes are threadsafe
        - They introduce additional synchronization
    
- It is VERY common for many reported benchmarks to be nothing more than 
validation of the concurrency bottleneck introduced by the RNG, rather than
the actual statistics of the concurrent class itself.
                
            
- BEST PRACTICES
    - providing each thread its OWN RNG allows a non-thread-safe to be used. 
    - prefer simple pseudorandom functions to general-purpose RNG
        - we don't need HIGH-QUALITY randomness
        - we only need to ensure that the numbers change from one run to the next 
        
        
        
        EXAMPLE:
        
            Medium Quality RNG that's good-enough for testing
            
            static int xorShift(int y) {
                y ^= (y << 6);
                y ^= (y >>> 21);
                y ^= (y << 7);
                return y;
            }
            
- Marsaglia's xorShift
    - providing initial values based on **hashCode** and **nanoTime** makes 
    checksums unguessable and almost ALWAYS different for each run.   
    
    
### Example - Testing Blocking Code (Examples.test.PutTakeTest) 
- By using and comparing Checksums to validate concurrent
datastructures we aren't using additional data structures or
methods that require additional synchronization or contention
to test. 

- As with any concurrency test, we have to provide a DETERMINISTIC TERMINATION CRITERIA
    - This is usually in the form of unblocking the blocked threads as well as knowing when the
    damn things are going to terminate

#### Test Thread/Interleaving Problem
- The "weight" of thread creation/execution is platform dependent. 


- Test code tends to create a number of short-running threads in a loop. 
    - WORST CASE: the threads run sequentially (zero interleaving)
    - CRAPPY CASE: Since the threads are short running, and the overhead for creating/starting the
        threads could be "large", it's possible that the temporal offset of each thread execution is
        far enough apart that we get very little interleaving (if any -> worst case)
        - there is a bell curve, where interleaving is only occurring during the middle of the test.
        
        

    SOLUTION
    
        We could use a CountDownLatch as a starting gate and a finish gate. The limitiation here is that
        we'd have to reinitialize the latch for each stage of the test. 
        
        A more effective way to accomplish this :
        
        CyclicBarrier:
            - initialize this to the number of worker threads + 1
            - The threads wait at the barriers at the beginning and end of their runs
                - "readiness" and "completeness"
                
            This solves the interleaving problem by ensuring that all of the threads are 
            ready before they start performing any work. 
            
            This hedges our bets in terms of providing the best chance for interleaving. 

        NOTE: 
            - We still can't GUARANTEE that the scheduler won't run each thread to completion
            sequentially, but if we increase the length of the runs, we reduce the extent to 
            which scheduling overhead distorts our results. 
            
#### Use Cases
- These types of testing tend to be good at finding violations


    EXAMPLE:
        - Common Errors when impl'ing semaphore-controlled buffers is to forget that 
        code doing insertion and extraction requries mutual exclusion
            - synchronized keyword or
            - ReentrantLock

- Running **PutTakeTest** w/ a few dozen threads iterating a few million times on buffers of
various capacities on various systems increases the confidence about the lack of data corruption in
**put** and **take**    

####  Best Practices
- Tests should be run on multiprocessor systems
    - increases diversity of possible interleavings
    - This does NOT make tests more effective
    

- To maximize the probability of detecting time-sensitive data races
    - "no of active threads" > "no of CPUs"
    - this ensures that at any given moment there are some threads running and some being switched out
    - this reduces the predictability of interactinos between threads.     
    
    
- Create a timeout for tests that don't terminate within a certain amount of time. 
    - prevents the case that a test never finishes due to a bug. 
    - NOTE: These types of failures are important to analyze
        - the problem is sometimes that we don't wait long enough for the test to finish. 
        - (Not unique to concurrent apps, even sequential tests have to distingush between
        "long-running" and "infinite")
        
## 12.1.4 Testing Resource Management 

### Recap
- Previous sections have been focused on testing how well a class adheres to its spec
- This section is about ensuring that a class doesn't do things that it SHOULD NOT do
    - i.e. leak resources.
       
### Resource Problems
- when an object that holds/manages other objects maintains refs to those objects longer than it
should
    - "storage leak"
    - prevents GCs form reclaiming those resources
        - memory
        - threads
        - file handles
        - sockets
        - db connections
 
 
- resource leaks can lead to resource exhaustion, which is typically a cause for app failure


### Relationship to BoundedBuffer Example
- BounderBuffer is a class specifically created for the purpose of preventing resource exhaustion
when producers get too far ahead of consumers. 
    - BoundedBuffer is designed to force Producers to block when Consumers are backed up
    - this prevents us from creating more work and using up resources that we can't act on.
    

### Testing It (With Tools)
- Memory retention can be investigated w/ "heap-inspection tools"
    - measures app memory usage
    
    
    EXAMPLE
    
        class Big {
            double[] data = new double[100000];
        }
        
        public static final CAPACITY = 100000;
        
        void testLeak() throws InterruptedException {
            BoundedBuffer<Big> boundedBuffer = new BoundedBuffer(CAPACITY);
            
            int heapSize1 = /* snapshot heap */;
            
            for (int i = 0; i < CAPACITY; i++) {
                boundedBuffer.put(new Big());
            }
            
            for (int i = 0; i < CAPACITY; i++) {
                boundedBuffer.take();
            }
            
            int heapSize2 = /* snapshot heap */;
            
            assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
        }
        
#### Explanation
- the test code inserts several large objects into a bounded buffer and then removes them
- the snapshots SHOULD be about the same. 
    - however if doExtract() forgot to null out the reference to the returned element, then
    the mem usage would NOT be the same.      
     
        
##### Placeholders
- There are placeholders in the example for snapshots created by the heap-inspection tool
- these snapshots request a GC and then records info about the heap size and mem usage


    NOTE:
    
        It isn't possible to FORCE a GC
        
            System.gc() "suggests" to the JVM that this moment in time might be a good
                time to perform a GC. 
                
            HotSpot can actually be instructed to IGNORE those calls altogether w/ 
            
                -XX:+DisableExplicitGC


## 12.1.5 Using Callbacks

### Use Case/Benefits
- it's less work to construct test cases by providing a callback to a client-provided code 
    - (i.e. less than rewriting the code.)
    
### Usage Methods
- callbacks are usually made at known points in an object's lifecycle that are good chances to 
assert invariants of that object. 


    EXAMPLE:
    
        ThreadPoolExecutor makes calls to
            - the task Runnables
            - to the ThreadFactory
            

### Testing a Thread Pool
- must test one or more elements of execution policy
    - addt'l threads are created when they are supposed to
    - addt'l threads are NOT created when they aren't supposed to
    - idle threads get reaped when they are supposed to
    - idle threads are NOT reaped when they aren't supposed to
    - etc.
    
- callbacks can make it easier to build a comprehensive test suite 
(code reuse)


#### Example 1
- instrument thread creation
    - maintain a running count of the threads created by the **ThreadFactory**
    - this allows a test to verify the no. of threads created during the test run.
    - (can be extended to return a custom Thread that records when it terminates)
        - allows test code to validate that threads are reaped in accordance to the
        execution policy
    
        
        EXAMPLE
        
            class TestingThreadFactory implements ThreadFactory {
                
                final Atomic Integer numberCreated = new AtomicInteger();
                private final ThreadFactory factory = Executors.defaultThreadFactory();
                
                public Thread newThread(Runnable runnable) {
                    numberCreated.incrementAndGet();
                    return factory.newThread(runnable);
                }
            }
            
#### Example 2
- if "core pool size" > "max pool size"
    - thread pool will grow as demand for execution increases. 
    - if the tasks submitted to the pool run long enough, it is possible to test that the
    pool expands 
    
    
        EXAMPLE:
        
            public void testPoolExpansion() throws InterruptedException {
            
                int MAX_SIZE = 10;
                ExecutorService executor = Executors.newFixedThreadPool(MAX_SIZE);
                
                for (int i = 0; i < 10 * MAX_SIZE;i++) {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(Long.MAX_VALUE);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
                
                for (   int i = 0;
                        i < 20 && threadFactory.numberCreated.get() < MAX_SIZE;
                        i++) {
                    Thread.sleep(100);        
                }
                
                assertEquals(threadFactory.numberCreated.get(), MAX_SIZE);
                executor.shutdownNow();
                
            }

## 12.1.6 Generating More Interleavings

### Problem To Solve
- most potential failures in concurrent code are low-probability events
- this means that testing for concurrency errors is largely about increasing the size of the test
space (w/o artificially changing the probability of those events) to increase our chances of encountering
a "low probability event"

### Hacks To Improve Our Chances
- (Previously Mentioned)
    - Running on multiproc systems w/ fewer procs than active threads can generate more interleavings
    than a single-proc system OR one w/ many procs
    - testing on a variety of systems w/ 
        - different CPU counts
        - different OS'
        - different CPU architectures
        
        
#### Thread.yield
- encourages more context switches that access shared state
    - (thus, we increase the number of interleavings)
        - therefore we "more effectively explore the state space of our apps"
    - NOTE:
        - The effectiveness of this is platform specific, because the JVM is allowed
        to tread Thread.yield() as a no-op. 
        
        
     EXAMPLE:
        
        public synchronized void transferCredits(
                Account fromAccount, Account toAccount, int amount) {
            
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            
            if (random.nextInt(1000) > THRESHOLD) {
                Thread.yield();
            }
            
            toAccount.setBalance(toAccount.getBalance() + amount);
        }
##### Explanation 
- This example demonstrates transferring credits from one account to another
- at the "yield" point, it is possible for the state to be a bit racy, as invariants such as "sum of
all accounts = 0" can't hold up, because the yield bisects a transaction that should be atomic. 


    WARNING: 
    
        - Introducing tests at these points causes time-sensitive bugs in code that doesn't
        use proper synchronization to access state. 
        
        - Even worse -- THIS IS TEST CODE. 
        
        
    SOLUTION:
    
        - adding these calls for testing, but removing them for production can be reduced by 
        adding them using AOP tools
        
        AOP = Aspect Oriented Programming

#### More Reliable Alternative
- use a short, but non-0 sleep
    - slower, but more reliable
     
            