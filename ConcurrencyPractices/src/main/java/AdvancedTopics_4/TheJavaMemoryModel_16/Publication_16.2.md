# 16.2 Publication

### Publication Review
- (See Chapter 3)
    - defines objects can be safely or improperly published
    - publication safety defined there get their guarantees
    from the JMM.
    - risks of improper publication are the consequences
    of the absence of *happens-before* ordering between 
        - the publishing of a shared object
        - accessing it from another thread.
    

## 16.2.1 Unsafe Publication

### Partially Constructed Objects
- (View Section 3.5)
- the absence of *happens-before* ordering means that the JVM
chooses ordering ("dealer's choice")
    - this is ok for independent/unshared data. 
    - for SHARED data that doesn't have sufficient *synchronization*
    it is possible for a thread to see *partially constructed objects*
    
    
        Init'ing an object requires writing to variables
            - (the fields of the new object) 
            
        Publishing a reference also requires writing to another variable
            - (the reference to the new object) 
            
- If publishing a shared reference doesn't have a *happens-before* ordering with another
thread LOADING that shared reference
    - it is possible that the thread attempting to read the new object will be able to read the
    reference to the object before it's fields have been updated
    
    
        The consuming thread will have
            - a current reference to the object
            - stale values/state for the object. 
            - (aka a Partially Constructed Object) 
            

### Incorrect Lazy Initialization

    DO NOT DO THIS:
    
        public class UnsafeLazyInitialization {
            
            private static Resource resource;
            
            public static Resource getInstance() {
                if (resource == null) {
                    
                    // NOOOOOOOOO!
                    resource = new Resource();
                }
                return resource;
            }
        }
#### Problem 1: Race Condition
- (SEE Section 2.2.2)
    - Under certain circumstances, we can overlook the race condition (and
    the inefficiency of accidentally creating **Resource** more than once)
        - (i.e. when all instances of **Resource** are identical)
        
#### Problem 2: Partially Constructed Object
- the class is still not thread safe because the lack of a *happens-before* constraint allows
additional threads to observe a partially constructed **Resource**
    - (regardless of whether or not we ignore the race condition)
    
    
    EXAMPLE
        - Thread A invokes getInstance()
            - resource == null
            - Thread A instantiates a new Resource
            - sets resource to reference the new object. 
        - Thread B later invokes getInstance(0
            - resource != null
            - uses constructed Resource. 
            
        

        - Resource constructor initialized the fields of the newly created
        Resource instance from default to initial states. 
        
        - Neither Thread A or Thread B  uses synchronization, 
        therefore B could see A's actions in a different order than they were
        performed
        
        NOTE: 
            - This means that even if A initializes the Resource and then SUBSEQUENTLY
            sets resource to reference object...
            - B might not see the actions in that order. 
                - (This is the concept of Partialy Constructed resources) 
                
                
### Best Practices
- It is not safe to use a mutable object that has been initialized by a different thread
unless:
    - the PUBLICATION of the object *happens-before* the consuming thread uses it .
    
   
        Immutable objects don't have this problem, as their state can't change once 
        modified. 
        
## 16.2.2 Safe Publication

### Refresh (Chapter 3)
- safe publication methodologies ensure that
    - a published object is VISIBLE to other threads 
    - because they ensure that
        - Publication *happens-before* the consuming thread loads a reference to 
        that published object
        
#### Example 1

    Thread A places object O on a BlockingQueue
    - no other thread modifies O
    
    Thread B gets O from the queue
    - B is guaranteed to see O in the state it held when it was enqueued. 
    
- This holds true because **BlockingQueue** impls have sufficient internal
*synchronization* to guarantee that enqueue *happens-before** dequeue. 

#### Example 2
- using a shared variable that is guarded by a lock or a shared volatile variable ensures
that reads/writes of that variable are ordered by *happens-before*

### Happens Before vs. Safe Publication
- *happens-before* provides a stronger assurance of visibility and ordering than
safe publication. 

    
    Object O is safely published from Thread A to Thread B
    
        - Safe Publication guarantees the visibility of the state of O, but 
        not any other variables that A may have interacted with. 
        
    
    Thread A enqueuing O HAPPENS-BEFORE Thread B dequeues it.
    
        - Assuing that O wasn't subsequently modified by A or another thread, 
        then Thread B is guaranteed to see O in the state it held at the
        time Thread A enqueued it. 
        - Thread B also sees ALL actions taken by Thread A before the
        junction of the HAPPENS-BEFORE relation. 
        
        
        NOTE: The JMM guarantee of HAPPENS-BEFORE is only held up to the junction of
        the relation. Any actions taken by the intial thread that occurs AFTER that
        junction may/may not be visible. 

#### Comparisons
- Thinking in terms of "handing off" object ownership and publication is easier to 
understand in terms of program/development design
    - (as opposed to thinking in terms of the visibility of individual memory
    writes.)
    

- The "happens-before" ordering operates at the level of INDIVIDUAL memory accesses
    - i.e. "Concurrency Assembly Language"
    
- Safe Publication operates at/near the level of application or platform design. 


## 16.2.3 Safe Initialization Idioms
    
### Lazy Init
- This is a software technique where init'ing of expensive objects is deferred until they 
are actually needed in order to speed up application start. 

#### Fixing Lazy Initialization Mistakes

    EXAMPLE 
    
        Thread-safe Lazy Initialization
        
            public class SafeLazyInitialization {
                
                private static Resource resource;
                
                public synchronized static Resource getInstance() {
                    if (resource == null) {
                        resource = new Resource();
                    }
                    return resource;
                }
            }
- This fixes the potential ordering issues from **UnsafeLazyInitialization** by making the
**getInstance** method *synchronized*
    - the JVM will insert the necessary *memory barriers* in order to establish the proper
    *happens-before* ordering between the if and the result. 
    
    
- The code path is small enough that applying *synchronized* to the entire method should
supply sufficient performance, but! there are better solutions....


### Static Fields and Thread Safety 
- static fields w/ initializers (or fields whose value are init'd in a static init block) are
a special case that provides additional thread-safety


- *static* initializers are run by the JVM at class init time
    - after class is loaded
    - before class is used by any thread. 
- JVM acquires a lock during initialization
    - this lock is acquired at least once by each thread to ensure that the class has been loaded
    - this ensures that any memory writes made during static initialization are automatically 
    visible to all threads. 
    
    
- statically initialized objects REQUIRE NO EXPLICIT SYNCHRONIZATION 
    - this is true when these objects are being created or referenced
    - this is true for "as-created" state
        - for mutable objects, *synchronization* is still required by readers/writers in order to
        ensure that any SUBSEQUENT modifications are visible
            - (and to avoid data corruption)
            

### Eager Initialization

    public class EagerInitialization {
        private static Resource resource = new Resource();
    
        public static Resource getResource() {
            return resource;
        }
    }
- Eager Initialization is basically the default creation process. 
    - objects aren't deferred
    

#### Eager Init and Thread Safety
- Eager Init allows the elimination of *synchronization" cost because the object is statically
initialized


### Lazy Init Holder Class

    Lazy Initialization Holder Class 
    
        public class ResourceFactory {
            private static class ResourceHolder {
                public static Resource resource = new Resource();
            }
            
            public static Resource getResource() {
                return ResourceHolder.resource;
            }
        }
- This is a Lazy initialization technique that is a combination of
    - Eager Initialization
    - JVM's lazy class loading
    
    
- this technique requires no *synchronization* on the common code path. 
    - ...because **Resource** is statically initialized.
    

- the only purpose of the class is to initialize the **Resource**
    - JVM defers initializing the **ResourceHolder** until it is actually used
    - the first call to **getResource** by any thread will result in the loading of 
    **ResourceHolder**
        - this is a static initialization, so no **sync** required.
        
        
## 16.2.4 DCL (Double-checked Locking) Antipattern

    DONT DO THIS
    
    public class DoubleCheckedLocking {
        
        private static Resource resource;
        
        public static Resource getInstance() {
            
            if (resource == null) {
                synchronized (DoubleCheckedLocking.class) {
                    if (resource == null) {
                        resource = new Resource();
                    }
                }
                return resource;
            }
        }
        
    }
    
- This was an ugly "trick" that resulted from early attempts to work around the synchronization
costs of early JVMs. 


- lazy initialization was commonly used to reduce expensive operations or reduce startup time
    - lazy init requires *synchronization*
    - *synchronization* was slow
    
### DCL
- intended to provide lazy initialization w/o paying the *sync* costs on the common code path.

#### Explanation
- first it checked whether init was needed at all
    - if the resource reference was non-null, then the object exists, and we can just use it.
    - otherwise, *synchronize* and check again for initalization.
        - requires that we guarantee only one thread actually initialized the shared **Resource**
        
        
#### Problem 1
- the common code path doesn't use *synchronization*
    - the common code path checks if the object has been initialized by determining if the REFERENCE
    to the object exists. 
    - since there is no *synchronization*, there is no *happens-before* ordering between the 
    creation of the reference and the initialization of the objects fields. 


- DCL creates the potential for a thread to see a partially constructed **Resource**
    - up to date reference w/ stale/invalid object state. 
    
    
        Very Bad

### DCL w/ volatile 
- The JMM was modified to allow DCL to work properly if the object reference is made *volatile*
    - slight performance impact
        - (*volatile* reads are only slightly more expensive than *nonvolatile* reads)
        
        
### Best Practices

    
    Prefer Lazy Init Holder > DCL. 
    - easier to understand for the same benefits
