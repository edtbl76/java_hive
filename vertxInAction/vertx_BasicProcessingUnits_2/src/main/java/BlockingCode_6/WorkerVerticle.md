# WorkerVerticle 
These are a special type of Verticle that DO NOT EXECUTE ON THE EVENT LOOP
- these are more or less an Executor that executes on a WORKER THREADS, which 
are taken from a special Thread pool. 

- NOTE: while you CAN build your own worker thread pools and deploy worker verticles to them, 
the default vertx pool is usually good enough

## Worker vs. EventLoop. 
The following are the differences of WorkerVerticles from standard
Event Loop Verticles
- Worker Verticle is NOT tied to a single worker thread
    - this means that successive events may NOT be executed on the same thread 
    (as it is on an EventLoop)
    
    - Worker verticles are single-threaded (like an EventLoop), so they can only be accessed
    by a single worker thread at any given time. 
    
## AUTHOR'S NOTE ON MULTI-THREADING FOR WORKER VERTICLES
"While deploying a verticle, there is an option for enabling multi-threading for worker verticles, 
in which case multiple events can be processed concurrently by a verticle, breaking the single-
threaded processing assumption. This was always considered fairly advanced usage, and many users ended-up using it the wrong way
and catching concurrency bugs. The feature is now under-cover and may even disappear in future
vert.x releases. Users are encouraged to simply adjust worker pool sizes to match the workload
rather than enabling worker multi-threading."

