# BlockingQueue
- pre JDK, the producer-consumer problem required that a consumer wait until the 
producer puts something in the resource queue before it could do something.

BQ is a specialized implementation of a Queue
- consumers/requestors/getters of data in the queue will automatically wait until the BQ is 
filled up before consuming the data. 
    - NOTE: they'll still attempt to consume, but they will be BLOCKED until the thread has
    something for them to chew on
    
### Guarantees of BQ
- if number of threads are < "corePoolSize"
    - Executor prefers ADDING a new thread vs. queuing
    
- if number of threads >= "corePoolSize"
    - Executor prefers QUEUEING requests vs. adding a new thread.
    
- if a request can't be queued, a new thread is created

- if creating a new thread results in number of threads > maxPoolSize
    - task is rejected. 