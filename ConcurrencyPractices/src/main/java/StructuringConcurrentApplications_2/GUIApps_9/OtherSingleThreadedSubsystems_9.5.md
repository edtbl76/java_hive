# 9.5 Other Forms of Single-Threaded Subsystems

### Shit Happens
- there are circumstances when thread confinement is forced on developer for reasons OTHER than avoiding 
synchronization or deadlock


    EXAMPLE: 
    
        Some Native Libs require that all access to the lib (even loading the lib w/ System.loadLibrary) are
        made from the same thread. 
        

### Solution
- create dedicated thread or single-threaded executor
- provide proxy object that intercepts calls to the thread-confined object(s)
- submit intercepted calls as tasks to the dedicated thread.

#### Impl?
- **Future** and **newSingleThreadExecutor** work well here. 
- proxy method can **submit** task, and then immediately call **Future.get** to wait for the result
