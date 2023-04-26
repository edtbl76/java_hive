# 11.4 Reducing Lock Contention

### Intro
- Serialization Hurts Scalability
- Context Switches hurt performance

    
    Lock Contention causes both Serialization and Context
    Switching, 
    
        therefore reducing lock contention can improve both
        performance and scalability
        
### Exclusive Resource Lock 
- An exclusive resource lock allows one thread to access a resource at a given time. 
- by definition, this means that access to the resource is serialized, therefore limiting the ability
to scale it out.


### 2 Factors That influence the probability of lock contention or "Little's Law"
1. How Often A Lock Is Requested
2. How Long A Lock Is Held, Once Acquired

- If the product of 1 and 2 is relatively small
    - Most attempts to acquire a lock will be uncontended, reducing the potential impact on 
    scalability
- else, it is high
    - threads will block waiting to acquire locks
    - (in the worst case, procs will sit idle waiting for locks, while there is plenty of work to be done)
    
    
#### Little's Law

    The average number of customers in a stable system 
    
        is equal to 
        
            their average arrival rate
            
                multiplied by
            
            their average time in the system. 

### 3 Approaches for Reducing Lock Contention 
1. reduce the duration for which locks are held
1. reduce the frequency w/ which locks are requested
1. replace exclusive locks w/ coordination mechanisms that permit greater concurrency


## 11.3.1 "Get In, Get Out" - Narrowing Lock Scope. 
- the less time we spend in the lock, the less of a chance there is that the lock will be
contended
    - REMEDIATION
        - move code that doesn't need the 'lock', out of the **synchronized** 'block'
            - especially for:
                - expensive computational operations
                - blocking operations (I/O) 


    EXAMPLE:
    
        X = 1000/T
        
            (X = throughput in terms of operations per second)
            (T = time that a lock is held in microsends)
    
        Characteristics:
            
            - we have an operation that holds an exclusive lock for 2 ms
            - every operation requires that lock
            
            - throughput can be no greater than 500 operations/second
                - (regardless of the number of processors)
                
            X = 1000/2 = 500 operations/second.
            
        If we somehow cut the time held by an operation to 1 ms, we can recalculate:
        
            X = 1000/1 = 1000 operations/second. 
            
            
        TRAIT: 
        
            Cutting the time an exclusive lock is held in half, effectively doubles the 
            theoretical throughput.
            
        
        NOTE: 
            - technically the increase is even GREATER, because we are ignoring the increased
            overhead in terms of "context switching" that is created by more lock contention. 
            
            (As Lock contention increases, so does context switching, which decreases the actual 
            availability of the thread to its overall time quantum)
            
        
        


### Overheld Lock Example


        public class AttributeStore {
            private final Map<String, String> attributes = new HashMap<>();
            
            public synchronized boolean userLocationMatches(String name, String regEx) {
                String key = "users." + name + ".location";
                String location = attributes.get(key);
                if (location == null) {
                    return false;
                } else {
                    return Pattern.matches(regEx, location);
                }
            }
        }

#### Explanation
- **userLocationMatches** is a method that looks up a user's location in a Map 
    - it uses regex to see if the resulting value matches the supplied pattern.
- PROBLEM:
    - The entire method is **synchronized** but the only part of the code that needs the lock 
    is **Map.get**   
    
### Remediated Example Code


        public class BetterAttributeStore {
            private final Map<String, String> attributes = new HashMap<>();
            
            public boolean userLocationMatches(String name, String regexp) {
                
                String key = "users." + name + ".location";
                String location;
                
                synchronized (this) {
                    location = attributes.get(key);
                }    
                
                if (location == null) {
                    return false;
                } else {
                    return Pattern.matches(regexp, location);
                }
            }
        }     
        
#### Why This is Better
- NOTE: For this small example, using a String is fine. However, if this was going to be an 
operation that was called over and over, it is better to use StringBuilder.

        
        EXAMPLE:
        
            StringBuilder sb = new StringBuilder();
            sb.append("users.").append(name).append(".location");
            
- NOTE2: As of JDK 8, when using the String Concatenation operator, the compiler MAY use
**StringBuilder** or **StringBuffer** (Thread-Safe) to reduce the number of intermediate 
String objects that are created by evaluation of an expression.
            

- The easiest way to reduce the lock duration is to remove the **synchronized** keyword from the
method signature, and to wrap the assignment of the location variable in a **synchronized** block
    - Since the call to **Map.get** is the only operation that accesses shared state, assigning it to the
    variable we created is the only step that needs to be guarded with a lock.
    - NO DILLY DALLY for the other operations.
    
- This improvement is driven both by Amdahl's Law and Little's Law. 

#### Additional Improvement - "Delegating Thread Safety"
- There is only 1 state variable in the class (attributes)
     - this can be replaced w/ a thread-safe Map-based data structure
        - **Hashtable**
        - **synchronizedMap**
        - **ConcurrentHashMap**  <-- My Preference
- Using Thread-Safe Collections is almost ALWAYS preferred


- Delegating thread safety to a thread-safe collection in this case ELIMINATES the need for any explicit
synchronization
    - this reduces the scope of the lock to only the duration of the Map access
        - (this is even shorter than the **synchronized** block above)
    - addt'l bonus:
        - removes the risk that a future dev will undermine thread safety by forgetting to acquire 
        the appropriate lock before accessing attributes.
        
#### Limitations to This Approach
- Generically speaking, reducing a synchronized method to a synchronized block is an easy win in terms of
scalability


- There are cases where a **synchronized** block is too small. 
    - atomic operations must be contained in a single **synchronized block**
        - (i.e. updating MULTIPLE variables that participate in an INVARIANT)
    - Since the cost of synchronization is non-zero (even though a single circumstance is very tiny), 
    there are diminishing returns in terms of performance when cutting up large synchronized blocks into
    many smaller ones to free up operations that don't need to be guarded, but are within the scope of
    the complex atomic operations. 
    
    
        NOTE: 
        - Don't forget Lock Coarsening
        
            It is entirely possible that the JVM will undo work done in this area.

### Best Practices
- Reducing the size of synchronized blocks/methods makes the most sense when you can move "substantial"
computation or blocking operations OUT of those blocks. 

    
## 11.4.2 Reducing Lock Granularity
- Another way to reduce the time a lock is held, is to ask for it less often
    - (which means there is less contention for it.)
    
### Lock Splitting and Lock Striping
- mechanisms that use separate locks to guard multiple independent state variables that were previously
guarded by a single lock
    - PROS:
        - reduces the granularity at which locking occurs (improves scalability)
    - CONS:
        - using more locks increases the risk of deadlock
        
#### "One Lock To Rule Them All" Thought Experiment
- If there was a single lock for an entire application, then the execution of any **synchronized** blocks 
would be serialized. 
    - Many Threads, One Lock = Higher Probability that there is contention for the lock


- If we distribute locks throughout the app, this means that there will be the possibility for
a temporal "miss" (i.e. a thread requests a lock, and there isn't one in place!)
    - Many Threads, Many Locks = Lower Probability that there is contention for the lock
    
    
#### Using Multiple Locks ==> LOCK SPLITTING!
- if a lock guards more than one INDEPENDENT state variable (i.e. it doesn't collaborate w/ other
variables in an invariant)
    - you MAY be able to improve scalability by splitting it into multiple locks that each guard
    different variables
    - each lock is requested less often 
    
### Example: Candidate for Lock Splitting

    public class ServerStatus {
        
        public final Set<String> users;
        public final Set<String> queries;
        
        public synchronized void addUser(String user) {
            users.add(user);
        }
        
        public synchronized void addQuery(String query) {
            queries.add(query);
        }
        
        public synchronized void removeUser(String user) {
            users.remove(user);
        }
        
        public synchronized void removeQuery(String query) {
            queries.remove(query);
        }
    
    }
    
- This is part of a monitoring interface for a db server
    - maintains sets of 
        - logged on users
    - currently executing queries
- The methods are using the same intrinsic lock (the ServerStatus class lock)

- NOTE:
    - there is NO relationship between user login/logoff and start/stop of query execution (outside of
    correlating those values for investigative work!)
    

### Same Example - Refactored to use Split Locks


    public class ServerStatus {
        public final Set<String> users;
        public final Set<String> queries;
        
        public void addUser(String user) {
            synchronized (users) {
                users.add(user);
            }
        }
        
        public void removeUser(String user) {
            synchronized (users) {
                users.remove(user);
            }
        }
        
        public void addQuery(String query) {
            synchronized (queries) {
                queries.add(query);
            }
        }
        
        public void removeQuery(String query) {
            synchronized (queries) {
                queries.remove(query);
            }
        }
    }
#### Explanation
- locks are split, since each variable is INDEPENDENT
    - this is a "less-coarse", "finer-grained" concurrency design

##### Possibilities For Improvement
- Sweet Spot for Lock Splitting -> Moderate (not Heavy) Contention
    - this (usually) has the effect of turning these contended locks into mostly 
    uncontended locks
    - Best outcome for perf/scalability


- If there is little contention:
    - very little gain in terms of perf or throughput
    - DOES increase load threshold at which performance starts to degrade due to contention
        - makes system more RESILIENT to contended locks
    
    
### Same Example - Implicit Lock Splitting

    public class ServerStatus {
        Set<String> concurrentUsers = ConcurrentHashMap.newKeySet();
        Set<String> concurrentQueries = ConcurrentHashMap.newKeySet();
        
        public void addUser(String user) {
            concurrentUsers.add(user);
        }
        
        public void removeUser(String user) {
            concurrentUsers.remove(user);
        }
        
        public void addQuery(String query) {
            concurrentQueries.add(query);
        }
        
        public void removeQuery(String query) {
            concurrentQueries.remove(query);
        }
    }
#### Explanation
- This is slightly better, because it makes the code easier to manage. 
    - we are delegating thread safety to a concurrent collection (**ConcurrentHashMap**) 
        - **newKeySet** is used to derive the collection to a thread-safe impl of a Set.
    - variable naming is used to communicate to other maintainers that this is THREAD SAFE.
        - this removes the need to use lock splitting, because the independent concurrent collections
        are automatically using lock splitting
        
- This is MY personal preference.


## 11.4.3 - Lock Striping

### Lock Splitting Limitations
- Lock Splitting provides different benefits for moderate or light contention

- Splitting heavily contended locks into 2 or more:
    - is still likely to create 2 or more heavily contended locks
    - the bottom line is that it doesn't DRAMATICALLY improve concurrency
    - (the juice ain't worth the squeeze)

    
    One of the biggest challenges of Lock Splitting is that it is very much like trying to get
    cool in the summer. 
    
        - you can only take so many layers off before you are naked. 
        - i.e. if I have 2 independent variables sharing a lock, I can only make ONE optimization:
            - split the lock into 2. 
            
            If the initial lock is very heavily contended, then the following locks may still be
            heavily contended w/o a way to split them further. 
            

### Lock Striping
- This is a type of Lock Splitting that can be extended to "partition locking" on a variable sized
set of independent objects. 


    EXAMPLE:
    
        ConcurrentHashMap's impl uses an array of 16 locks
        - each lock guards 1/16 of the hash buckets in the structure
            - Bucket "N" is guarded by "lock N mod 16"
        - this should reduce the demand for any given lock by roughly a factor of 16
            - (This assumes that the hash function provides reasonable spreading 
            characteristics and the keys are accessed uniformly) 
                
        THIS is how ConcurrentHashMap can support up to 16 concurrent writers. 
        
            NOTE: the no. of locks can be increased to be provide better concurrency 
                under HEAVY access on systems w/ very high CPU counts
                
                As always, increasing values like this beyond a default should only be
                performed when there is a DATA-DRIVEN REASON TO DO SO. 
                
                There MUST be evidence that concurrent writers are generating enough 
                contention to warrant raising the limit. 
                

#### Downsides to Lock Striping
- locking a collection for exclusive access (with multiple locks) is more challenging and 
expensive than doing so with a single lock. 
- there are some operations that need to be performed against the entire collection
    - this means you have to acquire ALL of the locks
    - (EX: when you expand a map, you need to rehash the values into a larger set of buckets)
    
    
    NOTE: The only way to acquire an arbitrary set of locks is through recursion. 
    

### Example of Lock Striping

    public class Striped Map {
        
        // Synchronization policy: buckets[n] guarded by locks [n % N_LOCKS]
        private static final int N_LOCKS = 16;
        private final Node[] buckets;
        private final Object[] locks;
        
        public StripedMap(int numBuckets) {
            buckets = new Node[numBuckets];
            locks = new Object[N_LOCKS];
            for (int i = 0; i < N_LOCKS; i++) {
                locks[i] = new Object();
            }
        }
        
        private final int hash(Object key) {
            return Math.abs(key.hashCode() % buckets.length);
        }
        
        public Object get(Object key) {
            int hash = hash(key);
            synchronized locks[hash % N_LOCKS] {
                
                for (Node node = buckets[hash]; node != null; node = node.next()) {
                    if (node.key.equals(key)) {
                        return node.value();
                    }
                }
            }
            return null;
        }
        
        public void clear() {
            
            for (int i = 0; i < buckets.length; i++) {
                synchronized (locks[i % N_LOCKS]) {
                    buckets[i] = null;
                }
            }
        }
    }
#### Explanation
- There are N_LOCKS locks
    - as described above each lock guards a subset of the total buckets. 
    - most methods like **get()** only need a single bucket lock. 
    - some methods need all of the locks. 
        - however, just because a method needs to acquire all locks, doesn't mean that the method
        has to acquire them all simultaneously
            - **clear()** demonstrates this
        
            
        NOTE: 
            
            clear() 
           
            - This is a NON-ATOMIC solution
                - There isn't necessarily a time when the structure is empty
                - (other threads may be adding elements to other buckets, even those that have just
                been cleared!) 
                
            - ATOMIC SOLUTION:
                - All locks would have to be acquired at once, which would prevent any threads from doing
                any work while the structure was cleansed. 
                
            
            - Strangely, clients typically can't lock for exclusive access so methods like 
            
                size() and isEmpty()
                
            may be stale by the time they return, so the non-atomic behavior is actually usually
            acceptable.
            
## 11.4.4 - Avoiding Hot Fields
A Hot Field is one that is used so frequently, lock granularity can't be reduced easily

### Rehash of Lock Splitting/Striping
- way to  improve scalability because they allow different threads to 
operate on different data without interfering with each other.
    - (or different areas of the same data structure in terms of Lock Striping)
    
### Identifying Where Lock Splitting Can Help
- When there is an app or program that exhibits contention for the LOCK more often than the DATA guarded
by that lock.

### Conditions In Which Lock Granularity cannot be Reduced
- where variables are required for every operations (Hot Fields!)
- many optimizations (especially something like caching) can create these kinds of
hot fields that limit scalability.
    

    EX: 
    
        implementing how "size()" computes the number of entries in a Map
        
            - O(n) = count the entries every time it is called. 
            
            - O(1) = update a separate counter every time elements are added/removed and then
                    just read the value when we need it.
                    
#### Disadvantages of Accumulators/Counters in Multi-Threaded Apps
- They work fine in 
    - single-threaded apps
    - fully synchronized impls (methods/blocks)
    
    
- Very hard to improve scalability w/ the O(1) algorithm, because every operation that 
adds/removes an element, also updates the shared counter. 
    - These are no longer INDEPENDENT variables, they are part of a larger invariant
    
- Even if Lock Striping is used for the hash chains, synchronizing access to the counter
reintroduces the initial scalability limitations of exclusive locking 
    - (i.e. it raises contention for the lock without raising contention for the data) 
    - we're extending overhead. 


### Solution (i.e. how ConcurrentHashMap does it in size())
- size() doesn't maintain a global counter that is updated on create/delete operations. 
 
 
- size enumerates the stripes 
    - adds up the number of elements in each stripe
    
- ConcurrentHashMap maintains a separate count field for each stripe
    - this is guarded by the stripe lock 
    - this helps prevent size from having to count all of the elements. It just has to 
    calculate the sum of the elements in each stripe
    
#### Further Optimization
- If size() were called excessively compared to mutative changes, then we'd be moving towards the
threshold of losing performance 
    - This can be optimized by storing the collection size in a **volatile** variable on each 
    call to **size**
    - any time the collection is  modified, the cache would need to be invalidated (set to -1 )
        - if cached value isn't negative when **size** is called, it's accurate. 
        - else, it is recomputed. 
        
## 11.4.5 Alternatives To Exclusive Locks
- Third option to mitigating lock contention:
    - use a more concurrency-friendly means of managing shared state instead of exclusive locks
    
### ReadWriteLock
- multi-reader/single-writer locking discipline
    - more than one reader can access shared resources at the same time as long as there is no
    attempt to modify
    - WRITERS must acquire the lock exclusively


- provides significant benefits to read-heavy workloads/data structures

- NOTE: Remember that read-only data structures can be IMMUTABLE, which eliminates the need for
locking entirely 
    - this means that there is ZERO lock contention
    - best case scenario


### Atomic Variables
- reduce the cost of updating "Hot Fields"
    - statistics counters
    - sequence generators
    - reference to the first node in a linked data structure
    
- atomic variables provide VERY fine-grained atomic operations on integers or object references
    - more scalable
    - uses LOW-LEVEL concurrency primitives provided by most modern processors.
        - i.e. "compare-and-swap"
        
- useful if classes have a small number of independent hot fields
    - (i.e. NOT multi-variable invariants)
    - MAY improve scalability
    
    
    NOTE: 
        - Atomic Variables REDUCE the cost of updating hot fields, but they don't 
        eliminate it. 
        
        Typically, 
            removing or reducing the number of hot fields used in an algorithm or 
            class has a larger impact on scalability than using atomic variables.
            
            
## 11.4.6 Monitoring CPU Utilization
        
### Goals for CPU
- when testing for scalability we want CPUs to be "fully utilized"
- avoid "asymmetry"
    - some CPUs are hot and some are not

### Tools
- vmstat
- mpstat
- iostat
- perfmon (Windows - barf)

### Asymmetrical Utilization
- Work is only being performed by a small set of threads

#### Causes
##### Insufficient Load
- The CPU is handling all of the work, so we more than likely have more CPUs than we need
- OR, we aren't testing capacity with enough load. 
    - it's possible that the clients aren't capable of generating enough load to 
    fully max out the system
    
##### I/O Bound
- use "iostat" to see if the app is "disk-bound"
- monitor network traffic to determine if the app is b/w limited

##### Externally Bound
- use profiler/db admin tools to determine how much time is being spent
waiting for responses from external systems
    - 3rd party app
    - middleware
    - db
    
##### Lock Contention
- use profiling tools to determine
    - how much lock contention an app is experiencing
    - which locks are "hot"
    - (NOTE: this can also be done through random sampling by triggering some thread dumps and
    looking for threads that are contended for locks.)
   
    
- "waiting to lock monitor"...
    - this shows up in stack frames of a thread dump when threads are blocked waiting for locks
    
    
- The benefit of sampling via thread dump is that uncontended locks rarely show up, and since
heavily contended locks almost always result in blocked threads, they tend to show up.

----
### Do we need more CPUs? 
- if app is keeping CPUs symmetrically hot, then its possible that our issues are in the opposite direction
    - Are there actually runnable threads to take advantage of additional cores? 
         - i.e. if I have 4 threads on a 4-way system, doubling the cores isn't going to do anything 
         unless I rework the program.
         
#### Reworking a program to take advantage of more threads
- create more threads (this is obvious)
- reconfigure program to be able to divide its workload over more threads
    - increased thread pool size
    - reactive/concurrency patterns
    
    
- "vmstat" reports the number of threads that are "runnable but not currently running" because
there aren't enough CPUs. 


    If CPU utilization is high and there are always runnable threads waiting for a CPU, 
    you'd probably benefit from more CPUs.
    

## 11.4.7 Just Say No To Object Pooling
- object allocation in Java is actually faster than malloc is in C, but it was very slow historically

### Work around for "slow" object lifecycles
- Object pools
    - objects are recycled instead of being GC'd and allocated when needed
    - even when you consider the reduced GC overhead, it is a performance loss.
    
#### Object Pooling Sucks in Single Threaded Apps...
- performance loss in ALL but the very most expensive objects in single-thread programs
    - SERIOUS loss in light/medium weight objects
- pool sizing is challenging (especially in dynamic situations)
    - if pool is too small, it has no benefit
    - if pool is too large, it puts too much pressure on the GC
        - (mem is wastefully retained where it could be used effectively elsewhere)
- risk associated that reusing objects may not be reset to the newly allocated state
    - data/stage leakage
    - BUGs
- risk that a thread will return an object to a pool and continue using it. 
    - state problems. 
    - increases work of GC by encouraging a pattern of OLD -> YOUNG references which
    is against the current from the way GCs have been optimized to work

#### ... and its even worse in concurrent code
- when threads allocate new blocks, there is very little inter-thread coordination
    - allocators use thread-local allocation blocks to eliminate most synchronization on 
    heap data structures.


- if threads request an object from a pool, there will be some synchronization required to coordinate 
access to the pooled data structure
    - Blocking a thread due to lock contention is 100 x more expensive than an allocation 
    - even a SMALL amount of pool-induced contention creates a scalability bottleneck
    - even UNCONTENDED synchronization is (usually) more expensive than object allocation
    
    
    Blocking a thread due to lock contention is 100 x more expensive than an allocation
    
        IMPORTANT. 
        

#### When to use Object Pooling
- It DOES have its uses, but its outside of the scope of concurrency. 
    - J2ME/RTSJ targets (i.e. OLD SHIT) may use object pooling for effective memory
    management or to manage responsiveness. 
    
    
    Allocating Objects is ALMOST ALWAYS cheaper than synchronizing.