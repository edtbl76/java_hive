# 6.1 Executing Tasks In Threads

### Task
- abstract, discrete units of work
- concurrent apps are organized around these tasks

#### Benefits
- program organization is simplified by being organized around divisions
(and subdivisions) of work.
- error recovery is facilitated by natural transaction boundaries
- concurrency is promoted by providing a structure that naturally
parallelizes work.

### Identifying Task Boundaries
Selecting good Task Boundaries + a "sensible" Task Execution Policy can help
achieve the primary goals of a distributed system
- independence
- sizing
- throughput/responsiveness
- graceful degradation

#### Independence
ideal tasks are independent activities
- no dependency on state, result or side effects of other tasks.

facilitates concurrency
- independent tasks are naturally parallelizable
    - (barring adequate processing resources)
    
#### Sizing
if each task represents a small fraction/division of the app's
processing capability
- greater flexibility in scheduling and load balancing

#### Throughput/Responsiveness
Server apps should exhibit good throughput/responsiveness
- support as many users as possible
- responsiveness => better user experience
- "good throughput" is ultimately a measure of how many users an application can support with
N degree of responsiveness

#### Graceful Degradation
This is the concept of a gradual means for handling excess load. 
- Poor applications tend to have a "binary" response to excess load
    - good load => good performance
    - over load => die, crash, end, FIRE!
- Good applications have remediation steps that attempt to allow the system to recover or
manage the additional load. 

#### Individual Client Requests - Natural Task Boundary
Many server applications accept requests from remote clients, which provides a "built-in"
task boundary
- (EX: web servers, fail servers, databases, etc)
- offers independence and appropriate task sizing 
    - processing message A isn't affected by other concurrent load
        - (unless the messages are pipelined)
- a single message requires a very small (insignificant?) amount of server resources

## 6.1.1 Executing Tasks Sequentially
Execution of Tasks on a single thread in the order for which they are received
- simplest policy for scheduling tasks in an application


    Example:
    
        class SingleThreadedWebServer {
            public static void main(String[] args) {
                ServerSocket socket = new ServerSocket(80);
                while (true) {
                    Socket connection = socket.accept();
                    handleRequest(connection);
                }
            }
        }

Example Explanation
- This is 'correct' and very simple to execute. 
- However, this will perform poorly, because tasks can only be executed one at a time. 

### MainThread Behavior In Sequential Model
- alternates between 
    - accepting connections
    - processing associated request
        - I/O (read request from socket and write the response to memory)
            - BLOCKING CALL
                - network congestion
                - connectivity issues
            - Other types of IO that might be necessary
                - File I/O 
                - Database requests
        - (new connections have to wait for this processing to complete before the main
        thread finishes the current request and can call accept() again)
- important rule
    - processing time is NEVER 0, so it must ALWAYS be evaluated. 
    - (This doesn't mean you can never do work on the same thread that accepts the task, 
    but it does mean that you have to consider the side effects of doing so)

### Disadvantages
- poor responsiveness while threads are blocked
- poor resource utilization
    - CPU sits idle while single threads wait for I/O to complete. 
    
### Advantages
- Sequential processing DOES offer simplicity and SAFETY
    - GUI, database threads can take advantage of this
    
    
## 6.1.2 Explicitly Creating Threads For Tasks
This approach tackles responsiveness by giving each request its own thread...
- improvement over sequential exec when:
    - load is light/moderate
    - as long as arrival rate doesn't exceed server's capacity to handle request
    

    Example
        
        class ThreadPerTaskWebServer {
            public static void main(String[] args) throws IOException {
                ServerSocket socket = new ServerSocket(80);
                while (true) {
                    final Socket connection = socket.accept();
                    Runnable task = new Runnable() {
                        public void run() {
                            handleRequest(connection);
                        }
                    }
                    new Thread(task).start();
                }
            }
        }

### Analysis of Code
SIMILARITIES
- structure is same
- we still alternate between 
    - accepting incoming connection
    - dispatching request

DIFFERENCES
- main loop creates a new Thread for each request, rather than just processing it. 

### Benefits
- by dispatching task onto a new thread, the main thread is able accept the next connection while
the previous connection is being processed
    - IMPROVES RESPONSIVENESS
- increases parallelism/concurrency
    - IMPROVES THROUGHPUT
        - if there are multiple processors
        - if tasks block because of:
            - I/O completion
            - lock acquisition
            - resource availability/inavailability
            
### Taxes you pay
-  more complicated implementation, because the task-handling code must be thread-safe
    - (it might be invoked at the same time for multiple tasks.)

## 6.1.3 Disadvantages of UNBOUNDED Thread Creation
There are a number of drawbacks to the thread-per-task (6.1.2) approach, especially when
a large number of threads might be created: 

### Thread lifecycle overhead
- thread creation/teardown is not 0
    - varies by platform
    - takes T amount of time that is represented as latency into request processing. 
- for lightweight requests that are frequent, the latency introduced by thread lifecycle might 
become a significant amount of your compute resources. 
    - (Lightweight means that they take little time in this case)

    
    EXAMPLE: 
    
        If latency is 1 ms, and processing time is 4 ms, then thread lifecycle is 25% of my 
        compute. That's a lot. 
        

### Resource Consumption
- Active threads consume resources
    - especially memory
- When "Runnable Threads" > "available procs", the threads sit idle
    - ties up memory
    - puts pressure on GC
    - having many threads competing for CPUs leads to idle/wait, which puts us back into the
    "blocking" operations problem of sequential execution.
    
### Stability
- there is a limit to the no. of threads that can be created. 
    - varies by platform, affected by factors such as
        - JVM invocation parameters
        - requested stack size in Thread constructor
        - limits on threads dictated by underlying OS
    - Result is usually "OutOfMemoryError"
        - easier to design programs to avoid hitting the limit than it is to 
        design them to recover from it. 
- Thread creation in the thread-per-task approach is dictated by the number of tasks/requests.
    - A lot of requests to the app essentially result in a DDoS attack, because eventually
    the app crashes due to the load. 
    - This means that thread-per-task FAILS distributed/concurrent application best practices
        - High Availability
        - Graceful Degradation

### Law of Diminishing Returns
- adding threads improves throughput up to a point. 
- once that point is reached, the application will slow down and eventually crash.


#### Avoiding The Drawbacks
- Place boundaries on thread creation within an application
    - NOTE: It is critical to test the app to ensure that it does NOT overload resources
    when this boundary is met. 
    

