# 12.3 Avoiding Performance Testing Pitfalls

## 12.3.1 Garbage Collection

### Problems
- Unpredictable timing
    - (it's possible a gc will happen during a test run)
    
    
#### Solution 1 - NO GC
- Prevent GC from running during a test
    - This isn't very realistic, as GC is actually going to 
    happen in production
    
#### Solution 2 - Real World
- run a test long enough that we ensure the GC runs multiple times
    - reflects real world behavior and performance
    - producer/consumer apps involve a fair amount of allocation and garbage collection
        - Producers -> allocate new objects
        - Consumers -> use and discard them
        
## 12.3.2 Dynamic Compilation

### Java
- Dynamically compiled language 
    - harder to write/interpret benchmarks than for statically compiled languages (C/C++ example)
    
### JVM 
- HotSpot and other modern JVMs use a combo of byte code interpretation and dynamic compilation
- at class load time:
    - JVM executes class by interpreting bytecode
    - If class is exec'd enough, dynamic compiler "kicks in"
        - converts bytecode to machine code
        - switches from interpretation to direct execution (which is faster)
        
### Problems w/ Dynamic Compilation
- unpredictable timing (sound familiar?)
- allowing the compiler during a MEASURED test run will create a biased test
    - 1.) compilation consumes CPU resources, which means we'll be testing throughput that 
    competes w/ resources used by the compiler
    - 2.) measuring run time of a combination of interpreted and compiled code isn't a
    very meaningful performance metric. 
        - ONE OR THE OTHER
        
        
- Code may be decompiled (reverting to interpreted execution)
    - loading some classes may invalidate assumptions made by prior compilations
    - profiling data may be gathered leading to a decision that certain code paths 
    should be recompiled w/ different compilation optimizations.
    
    
        
#### Example of Biased Test Results
![Biased Test Results](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/TestingConcurrentPrograms_12/Images/Screen Shot 2021-01-01 at 5.01.06 PM.png)
- The three timelines demonstrate comparisons between 
    - all interpreted execution
    - compilation at 50% point
    - compilation early in the run
   
    
- The point at which compilation executes has a considerable impact on the per-operation 
runtime. 
    - The extent of the variance presents a considerable bias on the test results
    
   
- The JVM unpredictably selects which thread to perform the compilation
    - (application thread vs. background thread)
    - naturally, the different threads have tasks scheduled for specific purposes, such that
    competing for resources w/ compilation will have different timing biases based on the
    nature of the work the thread was created for.
    

### Solutions/Best Practices
- Time-centric tests should be designed to run after all code is compiled
    - most apps run long enough that all frequently executed code paths are compiled
    - (Therefore there is little to no value measuring the speed of interpreted code)
     
- isolating measured trials via EXPLICIT pause
    - JVM uses various bg threads for housekeeping
    - by placing explicit pauses between "UNRELATED" computationally intensive tasks in 
    a single run, we ensure that the HVM can catch up w/ its bg tasks (housekeeping) w/o
    interference from measured tasks
    
    
    NOTE: It is important that the pauses are only performed when the tasks are UNRELATED. 
    
        - adding pauses to prevent bg interference when running the same test multiple times
        will create artificial/unrealistically optimistic results


#### Approach 1: Longer Tests
 - running a program for at least several minutes at a time decreases the bias of 
compilation in test results
    - the longer a test runs, the less percentage of total run time is 
    reflected by compilation and interpreted execution
    

#### Approach 2: Warm Up Pre-Test
- run a program for a sufficient amount of time (unmeasured) so that the code is executed enough to
be fully compiled
    - once all of the code is compiled, the tests can be measured/timed
    
    
        NOTE: 
            on HotSpot, running the app w/ -XX:+PrintCompilation
            
                prints out a message when dynamic compilation runs so we can verify
                the execution of compiled vs. interpreted code prior to starting a 
                measured test run.
                

- running the same test multiple times is a DevOps strategy that validates the test methodology
    - every time we run a test and the test completes successfully, we are increasing the confidence
    in our testing approach
    - discard warm-up runs because the perf will be inconsistent
    - compare the results of non-warmups
        - consistent results mean the tests are decent, and require little-to-no tuning
        - inconsistent results mean the tests might be fallable/non-repeatable, and we have to
        investigate.  
        
        
## 12.3.3 Unrealistic Sampling of Code Paths

### Runtime Compiler Optimization
- Runtime compilers gather profiling data about code path execution in order to optimize the code
paths being compiled. 
    - this means that compiling method foo() in one app may generate different code than compiling
    that same method in another app. 
- the JVM can make assumptions about conditions that are only true for a short amount of time 
    - those optimizations might be "backed out" or decompiled if the conditions later become
    untrue.
    
    
    EX: 
        Monomorphic Call Transformation:
        
            used by JVM to convert virtual method call to a direct method call
            if no classes currently loaded override that method. 
            
            it will invalidate the compiled code if a class is subsequently loaded
            that overrides the virtual method.
            
### Solutions/Best Practices
- test programs must approximate the usage patterns of a typical app
- test programs must approximate the set of code paths used by the typical app.
- tests of multi-threaded performance should be normally mixed w/ single-threaded perf
    - even if you only require single-threaded usage.
    
    
- failure to design test programs as above could result in special optimizations that are
only relevant to a single-threaded app that can't be applied in real apps that even use just 
occasional parallelism


## 12.3.4 Unrealistic Degrees of Contention

### Interleaving in Concurrent Apps
- Concurrent apps interleave 2 (Very different) types of work
    - 1.) accessing shared data
        - fetching tasks from shared work queue
    - 2.) thread-local computation
        - executing the task (assuming that the task doesn't access shared data)
        
        
- the level of contention experience by an app is dependent on the ratio of these two
types of work
    - this leads to different PERFORMANCE and SCALING characteristics
    

#### Long-Running Tasks
- There is NO contention 
    - if N threads are fetching tasks from shared work queue AND
        - tasks are compute-intensive
        - tasks are long running
        - tasks do NOT access shared data (much or preferably at all)
    - throughput is "dominated" by availability of resources
    
    
#### Short-Lived Tasks
- a lot of contention for work queue
    - throughput is dominated by cost/synchronization overhead
    
    
### Obtaining Realistic results for Concurrency Performance Tests
- tests should try to 
    - approximate the thread-local computation done by a typical application
    - approximate the concurrent coordination being tested
    
    
#### How Results Become Easily Skewed
- When the per-task execution of an app is considerably different in nature or scope
from that of the test app, it is possible that the results aren't going to be a good
reflection of where bottlenecks or performance results sit.

##### Lock-Based Classes (Section 11.5)
- examples are the synchronized Map impls
- throughput is heavily influenced by the degree of contention for the locks
    - if an app performs all thread-local execution for accessing shared data structures, its
    possible that the lock contention will be low enough to perform well. 
    - if not, there will be a HIGH degree of contention for locks, and we might be bogged down
    w/ synchronization overhead. 
    
    
    HOW the app works MATTERS!
    
    
    Workers that don't do much are typically short-lived. 
    - short lived tasks mean that we end up spending more time coordinating/synchronizing per
    execution, resulting in a tsunami of coordination overhead. 
    
    Not all Producer-Consumer environments work in this manner.  
    
    
## 12.3.5 Dead Code Elimination

### Stop Helping Me! (Compilers)
- compilers are very good at removing "dead code"
    - (Code that has no effect on the outcome)
    
    
- benchmarks in tests often don't compute anything, therefore it is very common for the
compiler to remove it. 
    - this means we are measuring less execution than we intended to
    - our test results may be unrealistically optimistic
    
    
- BEST CASE
    - the compiler optimizer will prune the entire test program, resulting in very obvious 
    failures. 
- WORST CASE
    - compiler results in a small (but meaningful) improvement that could be explained by other
    means
    
    
#### Dynamically vs. Statically Compiled Languages
- It is easier to ID dead code elimination in statically compiled languages, because we can look at the
machine code and see that a chunk of the program is missing. 
    - (This is harder to achieve in dynamically compiled languages)
    

### HotSpot Compiler Nuances (Server vs. Client)
- "-server" compiler 
    - produces more efficient code
    - better at optimizing dead code elimination
        - (bad for benchmarking!)
    - preferred for PRODUCTION and testing in multiprocessor systems.


### Preventing (or Reducing) Dead Code Elimination


Writing effective perf tests requires
    - tricking the optimizer into NOT optimizing away benchmarks as dead code
    - That requires that 
        - every computed result to be somehow USED by the program in a way
    that doesn't introduce concurrency artifacts
            - (i.e. no synchronization or substantial computation) 
        - every computed result should be UNGUESSABLE
            

#### APPROACH 1: Print it out!
    - NO!!!!
    - printing is considered I/O which will distort runtime measurements
    
#### APPROACH 2: Bad Hash Code
- compute hashCode of the field of some derived object
- compare that hashCode to some arbitrary value (e.g. **System.nanoTime**)
- print a useless (and easily ignored) message if they happen to match. 
    - (NOTE: don't use println)


    EX:
        
        if (foo.x.hashCode() == System.nanoTime()) {
            System.out.print(" ");
        }

- comparison will rarely succeed (like astronomical chances)
    - if it does, the space output is harmless
    - **print** buffers output until println is called, so even if there is 
    a match, no I/O is actually performed. 

