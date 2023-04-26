# 7.4 Java Shutdown.

### Types of Shutdown
1. Orderly/Graceful [PREFERRED]
    - initiated when last "normal" (nondaemon) thread terminates
    - **System.exit**
    - platform-specific means (SIGINT, CTRL-C, et al.)
2. Abrupt
    - **Runtime.halt**
    - killing JVM via OS (i.e. SIGKILL, et al.)
    
    
## 7.4.1 Shutdown Hooks
These are unstarted threads that are registered with **Runtime.addShutdownHook**

### How It Works
#### Orderly Shutdown
- JVM starts all registered shutdown hooks
    - (order not guaranteed)
- any app threads that are still running will execute concurrently w/ the shutdown process
    - (daemon or non-daemon, doesn't matter)
- when shutdown hooks complete, JVM runs finalizers
    - (if **runFinalizersOnExit** is TRUE)
- there is NO attempt to stop/interrupt any threads that are still running
    - ABRUPTLY terminated
    - JVM halts. 
- NOTE: 
    - if shutdown hooks and/or finalizers don't complete
        - orderly process "hangs"
        - JVM must be shutdown abruptly
#### Abrupt Shutdown
- JVM does not pass go, it immediately halts
    - shutdown hooks are not run
    - finalizers aren't run
    - ALL threads stop abruptly
    
### Concurrency Attributes of Shutdown Hooks
#### Thread-Safe
- use synchronization when accessing shared data
- avoid deadlocks
#### Defensive Design
- make no assumptions about state of application
    - are other services up/down? 
    - are all normal threads terminated/running?
- make no assumptions about WHY JVM is shutting down.
#### High-Performance / Responsiveness to Shutdown Request
- exit ASAP
    - This is important because the shutdown hooks are a deliberate source of latency
    injected into the shutdown process. 
    - When we hit shutdown(), we want that non-zero amount of time for shutdown to occur to be
    as short as possible. 

### Use Cases
- service/app cleanup
    - (Specifically useful for cleaning up resources not handled by the OS)
    - (Very commonly used for closing resources like files)
    
    
    EXAMPLE:
    
        The start() method of our LogService registers a shutdown hook
            - the shutdown hook makes sure that the log file is closed on exit. 
        
        public void start() {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        LogService.this.stop();
                    } catch (InterruptedException ignored) { }
                }
            });
        }
#### Problem
- shutdown hooks run concurrently, and order is not guaranteed. 
- If we close the log file, and this is the first shutdown hook that runs it could 
cause issues for other shutdown hooks that need to use the logger

#### Solution (Best Practice)
- Shutdown hooks shouldn't rely on service that can be shut down by the application or
other shutdown hooks. 
- (APPROACH) Single Shutdown Hook for all services (instead of one per service)
    - the single shutdown hook can call an ordered series of actions that will be
    executed sequentially in a single thread. 
        - avoids race conditions/deadlock between shutdown actions 
        - (eliminates many sources of failure)
        - (useful in apps that have a lot of dependencies)
- DOWNSIDE
    - serializing shutdown operations will slow down the JVM shutdown process.
    
    
## 7.4.2 Daemon Threads
These are threads that perform helper/utility functions, however the existence of these threads
shouldn't prevent the JVM from shutting down. 
       
### Thread Types
1. normal threads
    - main thread, and (by default) any thread created from it.
1. daemon threads

#### On JVM Exit
- The only difference between the thread types is their behavior when the JVM exits
- JVM EXIT
    - performs inventory of running threads
    - if only threads remaining are DAEMON threads, it kicks off ORDERLY shutdown
    - Daemon threads are ABANDONED
        - finally blocks not executed
        - no stack unwind. 
        - JVM -> EXIT (do not pass go)

### Thread Spawning at JVM Start
- ALL threads created at start time other than the main thread are daemon threads
    - the GC and other housekeeping threads. 
- Main thread is a NORMAL thread
- When new threads are created, they inherit the "daemon/non-daemon" status of the thread
that created it.
    - (this is why threads spawned from main are normal threads by default.)
    
### Best Practices
- use daemon threads sparingly
    - abandoning resources w/ no cleanup is a scary prospect
- do NOT use daemon threads for any tasks that might perform I/O
- Daemon threads are useful for housekeeping tasks
    - i.e. bg threads that run jobs to remove expired entries from an in-mem cache.
- Daemon threads are ABSOLUTELY NOT a good substitute for properly managing lifecycle of
services within an app.

## 7.4.3 Finalizers

### Resource Management
- GC reclaims most memory resources when they are no longer needed by the JVM
- file/socket handles have to be explicitly returned to the OS when they are no
longer needed. 
    - these objects have a nontrivial **finalize** method that assists the GC
        - GC reclaims the resource
        - then calls **finalize** so that the resource can be released back to the OS
        
### Concurrency Traits
- Finalizers can run in a thread managed by the JVM, and their state can be accessed by 
more than one thread
    - state must be accessed using synchronization
    
### Downsides of Finalizers
- no guarantees on how/when they run
- significant perf hit on objects w/ nontrivial finalizers
- very fucking hard to write correctly
- explicit **close** methods typically do a better job at resource management than finalizers


    Avoid Finalizers 
    
        The one exception is when you need to manage objects that hold resources that are
        acquired by native methods.
 

