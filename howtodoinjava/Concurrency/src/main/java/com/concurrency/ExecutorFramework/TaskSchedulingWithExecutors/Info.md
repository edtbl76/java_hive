# TheadPoolExecutor
- class provided by the Java Executor Framework to execute CALLABLE and RUNNABLE tasks
w/ a pool of threads. 
    - helps alleviate having to write lots of boiler plate complex code. 
    

- Executors work by executing tasks ASAP after the task is sent to the executor. 

## Scheduling
- sometimes we don't want executors to run immediately, we want them to execute after some
period of time
    - ScheduledThreadPoolExecutor class
    
# ScheduledThreadPoolExecutor
- this is a DERIVED class of ThreadPoolExecutor
    - inherits all of its non-private features
    - strongly recommended to use this ONLY for scheduled tasks. 
    
- instantiate w/ Executors class (newScheduledThreadPool() method)
    - this method takes the number of threads you want to prepopulate the pool with
    
- schedule() is the method used to schedule the thread pool executor
    - parameters:
        - task to be executed
            - can be of type Runnable
        - period of time 
        - time unit the previous parameter is measured in. 
        
- shutdown()
    - this turns out the lights on the executor
    - NOTE: default behavior is to EXECUTE any pending tasks still waiting. 
    - this can be overridden


