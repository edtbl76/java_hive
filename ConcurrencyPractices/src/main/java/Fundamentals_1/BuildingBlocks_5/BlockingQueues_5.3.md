# 5.3 Blocking Queues and the Producer-Consumer Pattern

## BlockingQueue
Provides methods that block indefinitely
- put()
- take()

Timed Equivalents (Blocks for a given interval of time)
- offer()
- poll()

Behavior
- put()/offer() will block when queue is full
    - waits for space to be available
- take()/poll() will block when queue is empty
    - waits for data to be available
    
## Producer-Consumer Pattern
- this pattern is focused on isolating
    - Identification of work to be done
        - placing work items in a todo list for later processing.
    - execution of that work.
    
### Benefits
- simplification of development 
    - decoupling of dependencies between producer/consumer classes
- simplifies workload management
    - decouples tasks that may produce/consume data at different ratesz
    
### Common Example: Thread Pool + Work Pool
This is the pattern is the foundation of the Executor task 
execution framework.
    
## Intersection: Producer-Consumer + BlockingQueue

### Producer
Relative label for an actor placing data on a queue as it becomes
available. 
- don't need to know anything about consumers
    - not the identity
    - not the number of consumers
- super simple job
    - place work on queue

### Consumer
Relative label for an actor retrieving data from the queue when the
actor is ready to take an appropriate action.
- don't need to know anything about producers
    - not where work came from
    - not the number of producers
- super simple job
    - get work from queue
    
#### Producer-Consumer Relativity
- The "label" of producer and consumer need not be explicit. It is
possible for services or actors to be ambiguous, such that at any given
time they may be a producer or consumer. 

    
    NOTE: 
        While many distributed systems will create explicit 
        services responsible for only Producer or Consumer actions, 
        depending on the time to scale these services, there may be
        a benefit for services that can do both. 
        
            If the time to add a new instance of a given type is 
            longer than any configured timeouts (poll/offer), 
            then it might make sense to 
            
            - have warm spares always available
            - have fatter, but multi-purpose services that can be a 
            producer OR consumer at any given time. 
    
### General Example: "You wash, I dry"
- Producer washes dishes and places it into dish rack 
- Consumer takes dishes from rack. 
- The most important concept is that the separation of concerns is
tied directly to the queue data structure. 
    - the queue allows each actor to interact ONLY with the queue. 
    - there is an added reliability benefit of not requiring the
    producer and consumer ot be up at the same time.  

### Utilization
Utilization is the measurement of threads (consumers or producers) 
actually doing work. 
- ideally, when using a blocking queue, we want:
    - consumers that never have to wait for work on the queue 
    - producers that never have to wait for space in the queue

If Producers are creating too much work
- consumers will fall behind. 
- in an unbounded queue
    - work will continue to be added to the queue until the system is
    unable to provide storage (memory) to the data structure. 
    - (i.e. Out of Memory)
- in a bounded queue
    - work will continue to be added to the queue until the queue is 
    filled to max capacity
    - the producer will then block until space becomes available
    
#### Shared Work Queue + Bounded Queue (Resource Management!)
The shared work queue that is the intersection of the actions within the Producer-Consumer
pattern provides indirect "behavioral" coupling between the actors of the pattern. 

    NOTE:
        Bounded Queues are a day 1, first-class citizen of reliable applications. Allowing actors 
        unchecked access to system resources is a considerable anti-pattern that jeopardizes the 
        reliability of the system. 
       
        It is important that this is part of the upfront design, as refactoring into this 
        pattern can become very difficult after it has been put into production. 
        
        If a BlockingQueue is not appropriate for the existing design, blocking data structures
        can be constructed from Semaphore as well. 
            - this is less desirable, but will achieve the same result. 

    
#### Time-constrained methods
offer() and poll() are time-constrained methods in BlockingQueue
that are designed to time-box how long the thread will block before
it decides that the operation can't be completed. 
- One of the values of time-constrained calls is that if the timer 
expires, the thread returns a failure status that 
    - an item can't be enqueued (offer())
    - the queue has nothing to be dequeued (poll())
    
#### Overload Management
The use of time-constrained methods allows for the creation of 
flexible policies for dealing with overload
- shedding (dropping the load)
    - which end to drop from? 
- persisting excess work
    - serializing work items
    - writing them to disk
    - processing persisted log at a later time.
- reducing producers
    - throttling
    - terminating
- scaling out consumers


### BlockingQueue Implementations

#### FIFO Queues
- LinkedBlockingQueue (like LinkedList)
- ArrayBlockingQueue (like ArrayList)

    
    These are just like LinkedList/ArrayList FIFO structures, but with better concurrent
    performance than their synchronized brethren.

#### Non-FIFO
- PriorityBlockingQueue 
    
    
    This is a BlockingQueue implemented as a sorted collection.
    - it allows comparing elements according to:
        - their naturla order of the elements implement Comparable
        - using a Comparator
    - This is NOT FIFO order. 
    
#### Not a Queue (Naming is Hard)
- SynchronousQueue

    
    This isn't a queue. 
    - there is no storage allocated for queued elements. 
    - it maintains a list of queued threads that are waiting to enqueue/dequeue elements. 
    
    In the dish wash/dry analogy
        - no disk rack, but hands directly to next available dryer. 
        
        
##### Benefits/Use Case for SynchronousQueue
PROS
- skipping the backing storage reduces the latency associated w/ moving the data from Producer to
Consumer.
- provides direct feedback to Producer that the task has been accepted for work, rather than in 
the queue.

CONS 
- this back pedals the decoupling a bit, by forcing closer coupling/collab between producer + consumer
    - decreased reliability
    

    Generally speaking, this implementation is only suitable for applications where performance
    and/or durability are absolutely required. 
    - we need the performance provided by removing the middle-man/storage
    - we need to know that the consumer has taken the work quickly, without having to 
    wait for any additional coordination with the queueing mechanism. 
    

## 5.3.1 Desktop Search - Example
An agent that scans local drives for documents, then indexes them for later searching is
a good example of a program that may use producer-consumer. 
- GoogleDesktop
- Windows Indexing Service

(see DesktopSearchExample package for code examples)
- FileCrawler (producer)
    - searches file hierarchy/tree for files meeting a certain
indexing criterion
    - puts the names of the appropriate files on a work queue
- Indexer (consumer)
    - takes file names from queue and indexes them.
    
### Benefits 
- Producer-Consumer provides a "thread-friendly" means of 
breaking down the desktop search problem into simpler components
    - code is more reusable
    - code is more readable. 
    - each task "has only one job"
    - blocking queue manages flow control
- Performance benefits
    - producers and consumers can execute at the same time. 
    - if one is I/O bound, and the other is CPU bound, then 
    executing them at the same time yields better THROUGHPUT
    than sequential execution
    - if prod/consumer actions are parallelizable to different
    degrees, then tightly coupling them reduces parallelizability to
    that of the less parallelizable task
        - (i.e. instead of letting it do what it does best, we
        are forcing it to operate at the capacity of its
        weakest link)
        
## 5.3.2 - Serial Thread Confinement
This is a feature provided by BlockingQueues in Producer-Consumer
designs that allows the handing off of ownership of MUTABLE objects
from producers to consumers. 

### Thread-Confined Object
- owned EXCLUSIVELY by a single-thread
- ownership may be "transferred" by publishing it safely 
    - only ONE other thread will be able to gain access to the object
        - object's state will be visible to the new owner. 
    - the publishing thread can't access the object after the handoff
    has occurred. 
- after the handoff, the new owner can modify the object because it
is the exclusive owner

#### Exploit - Thread Lending
A common "cheat" is for a producer to "lend" an object to the
requesting thread. This can also be thought of as a special case
of thread-confinement where thread A transfers the object to Thread B, 
and then thread B transfer it back to thread A. 
- (If you think about the weeds, there are some ways to optimize this)

Requirements
- the thread pool must contain enough internal synchronization to 
publish the pooled object safely
- clients can NOT publish the pooled object
- clients can NOT use the object after returning it to the pool

#### Other publishing mechanisms
- atomic remove() method from ConcurrentMap
- compareAndSet() method of AtomicReference


    NOTE:
        The main point of any of these patterns is the
        safe publication
        
        1.) only one thread can receive the object being transferred
        2.) the original owner can't access the thread once it's been
        handed off. 
        
## 5.3.3 - Deques and Work Stealing

### Deque and BlockingDeque
A deque is a double-ended queue 
- extends Queue/BlockingQueue
- allows efficient insertion/removal from both head and tail of
the structure

    
    EXAMPLES:
    - ArrayDeque
    - LinkedBlockingDeque
    


While blocking queues are very well suited for the Producer-Consumer
pattern, the deque structure is very well suited for the Work Stealing
pattern.
- Blocking Queue -> Produce-Consumer
    - every consumer has a blocking queue
- Deque -> Work Stealing. 
    - every consumer has a deque

### Work Stealing
Work stealing provides a means to improve on the Producer-Consumer
pattern.
- Workers (Consumers and Producers) must contend for a shared work
queue.
    - producers all have to write to the same head
    - consumers all have to read from the same tail. 
- if a consumer has "exhausted" (completed) the work in its own 
deque, it can start to process work from the tail of another
consumer's deque. 
    - increased utilization of consumers.
    
#### Reduced Contention
The first reduction comes from the ability of a consumer to read 
from multiple data structures.
- initially it's own deque
- once it's own deque is exhausted, it will look for more work to do.
The second reduction comes from the ability to read from both ends of
the queue

#### Use Cases
Work Stealing is very useful in systems where workers can be either
consumers or producers.
- i.e. when performing a unit of work is likely to result in the
identification of more work. 

    
    WebCrawler Example
        - a worker may be asked to be a consumer to read a page
        - in the process of crawling that page, the consumer may
        have identified new pages to crawl, therefore placing those
        tasks on the same queue.
            - (Maybe another queue, but unlikely)

    
    GraphExploring Algorithms Example
        (i.e. marking heap during GC) 
        - allows for efficient parallelization w/ work stealing
        - worker id's a new unit of work, it can place it at the
        end of its own work. 
        

#### Work Sharing vs. Work Stealing
- Work stealing specifically occurs when 
    - a consumer/worker has run out of work on its own deque
    - locates a task at the TAIL of another consumer/worker's deque
    - adds that task to the TAIL of it's own deque. 
- Work sharing specifically occurs when
    - a consumer/worker has run out of work on its own deque
    - locates a task at the TAIL of another consumer/worker's deque
    - adds that task to the TAIL of another consumer/worker's deque. 
    
In both cases, the purpose is to increase utilization of threads. 
- threads are expensive resources, and the more they stay busy, the
better bang for buck. 

Something to consider is the access pattern. 
- if large batches of data are loaded in to a set of consumers that
must process the data, but the stream stops while the consumers
chew on the work, then it is likely that consumers that run out of work
will see a performance hit as they STEAL work. This happens because
instead of taking work + processing it, it must find work, move work, 
take work, process it for each unit of work. In terms of steps this is
2x much work. 
    - one potential solution is to load work in batches to reduce the
    amount of overhead. For example, when the "stealer" thread finds
    a deque with work to do, it could take half of the items off of
    the deque, distributing the work evenly between the stealer and
    the souerce. 
    - another potential solution is to use work sharing, where one or
    more threads that finish their work first will try to distribute
    work evenly across the other threads to maximize utilization.

