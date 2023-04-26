# 1.2 Benefits Of Threads

## Benefits
- reduce development
- reduce maint. costs
- improve performance of complex apps
- simplify modelling of human work/interactions
    - turns asynchronous workflows into sequential ones
- turn "convoluted code" into "string of pearls"/"straight-line code"
    - easier to read
    - easier to write
    - easier to maintain
    
## Exploiting Multiple Processors
- Thread is basic unit of scheduling
    - Thread/CPU Math
        - program w/ 1 thread can only run on 1 proc at a time
            - on a 2 CPU system
                - single-threaded program loses 50% of available resources
            - on a 100 CPU system
                - single-threaded program loses 99% of available resources
        - program w/ MULTIPLE threads can exec CONCURRENTLY on multiple CPUS

### Multiple Threads on Single Proc Systems
- if a program is single-threaded
    - CPU is idle while it waits for synchronous I/O to complete
- if a program is multi-threaded
    - another thread can still exe while first thread is waiting for I/O to
    complete. 
    - this allows the application to make progress while waiting, rather than
    wasting time
    
## Model Simplicity
- time is easier to manage when you only have one TYPE of task to perform
    - if you have 10 instances of the same task to perform, 
        - you start at the top and keep working until the tasks are completed
    - if you have 10 instances of 10 different types of tasks to perform
        - we have to "switch" between "contexts"
        - this change between different "types of tasks" incurs some overhead.
       
### Threads and Tasks
- assigning threads to each type of task separates domain logic from
    - scheduling details
    - interleaving operations
    - asynchronous I/O
    - resource waits
- complex async workflow can be decomposed into a number of simpler, sync
workflows each running in a separate thread
    - interaction only occurs at "synchronization points"
    
## Asynchronous Event Handling

### Blocking/Non-Blocking I/O
- Synchronous I/O causes "blocking" operations
    - when a request has to WAIT for a response, the thread that initiated the
    request can't perform work until the response occurs
    - if this is the ONLY thread, then all other requests are blocked until
    the response occurs. 
    - if each requests has its own thread, then blocking doesn't matter. 
- Asynchronous (Non-Blocking) I/O
    - this is FAR more complicated/error-prone, but it allows more work to be
    done given a limited number of threads. 
    
## Responsive UI

### Single-Threaded (Older)
- Single Threaded GUI Apps had two solutions
    - freq. polling for input events
        - messy/intrusive
    _ use a "main event loop"
        - if code exec'd on event loop takes too long, UI will freeze until
        code responds. 
        
### Modern GUI Frameworks 
- Examples (AWT, Swing)
- EDT (Event Dispatch Thread)
    - replaces Event Loop
    - as a UI event occurs, app-defined event handlers are called in the
    event thread
    - still uses main event loop, but
        - runs in its own thread managed by the GUI toolkit rather than
        the app.
    - UI remain responsive as long as event thread handles short-lived tasks
    - long-running tasks can be dispatched to separate worker threads to 
    prevent the main event thread from being blocked
    
    