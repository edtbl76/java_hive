# 1.4 Threads are Everywhere. 

## Pervasiveness of threads
Even if we never create a thread on our own:
- frameworks we use may create threads fo rus
- code called from those threads MUST be thread-safe

### Java Apps
- EVERY Java Application uses threads
- JVM 
    - creates threads for JVM housekeeping tasks
        - GC
        - finalization
        - main thread for running main()
- AWT (Abstract Window Toolkit)/ Swing
    - both create threads for managing UI events
_ Timer
    - creates threads for executing deferred tasks
- Component Frameworks (i.e. RMI - Remote Method Invocation)
    - creates pools of threads
    - invoke component methods in the threads. 
    
## The contagious need for thread safety
- Framework introduced concurrency not isolated to framework code
    - frameworks (by definition) make callbacks to app components to 
    access app state. 
- Thread-Safety must be observed by
    - the code that touches the framework
    - ALL CODE PATHS that access any program state that is accessed/modified
    by the framework's components. 