# 8.4 Extending ThreadPoolExecutor
- **ThreadPoolExecutor** was designed for extension
    - hooks to be overridden by subclasses. 
        - **beforeExecute**
        - **afterExecute**
        - **terminated**
        
### Hooks
For **beforeExecute** and **afterExecute**:
- called in the thread that executes the task
- used for logging, timing, monitoring or statistics gathering

#### beforeExecute
- if this throws **RuntimeException**
    - task isn't excecuted
    - afterExecute isn't called

#### afterExecute
- this is called after the task completes
    - if it returns normally from **run()**
    - by throwing an **Exception**
- this is NOT called
    - if the task completes w/ an **Error**

#### terminated
- called when thread pool completes shutdown process
    - all tasks have finished
    - all worker threads have shut down 
- USE CASE
    - used to release resources that have been allocated by
    **Executor** during its lifecycle
    - used for notification/logging purposes
    - used to FINALIZE stats gathering.
        - this can help analytics systems by only triggering compute
        once all of the stats have been received. 
        - in some cases, you might not want to provide stats computation
        dynamically if the computations are particularly expensive. 
        
## 8.4.1 Example - adding statistics to a thread pool
(See ExampleCode.TimingThreadPool)
- Custom thread pool that uses the 3 types of hooks included from
**ThreadPoolExecutor**

### Handling Shared Values.
- In order to compute the time a task takes to run
    - 1.) Create a shared variable so that the necessary hooks can 
    access the values.
        - Ex. START_TIME
        - NOTE: Execution Hooks are executed in the same thread that
        executes the task, therefore the variable can be conveniently
        established as a **ThreadLocal** variable.
    - 2.) **beforeExecute** inits the variable w/ the current Time
    - 3.) **afterExecute** reads the stored value and processes it
        - in the case of the example we track and calculate individual task time and total 
            task time w/ the help of Atomic Longs.
    - 4.) **terminated** 
        - when the thread itself is killed, this will read the total time
        and task count variables updated by **afterExecute** in order to 
        calculate the average task time.