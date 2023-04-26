# 3.3 Thread Confinement

## Thread Confinement
- Access shared mutable data requires using synchronization
     - Workaround: Don't Share
- Thread confinement is the use of UNSHARED mutable data
    - sharing = allowing data to be accessed from multiple threads
    - UNSHARED = confining data to a single thread. 
        - no need for synchronization
- If an object is confined to a single thread
    - access to the object is thread-safe even if the 
    confined object is NOT. 
- Examples
    - Swing
    - Pooled JDBC (Java Database Connectivity) Connection objects
- Java language doesn't inherently provide thread confinement
    - just like it has no mechanism for enforcing that variables are
    GuardedBy a lock
    
### Considerations
- Using thread confinement is sometimes a consequences of selecting
a particular subsystem/framework
    - (ie. using a GUI which is single-threaded)
    
## 3.3.1 Ad-hoc Thread Confinement
- describes the scenario when the responsibility for maintaining 
thread confinement falls entirely on the implementation
- very fragile, use sparingly
    - prefer alternatives
        - stack confinement
        - ThreadLocal
        
### Volatile Variables: Special Case
- "it is safe to perform Read-Modify-Write operations on shared
volatile vars as long as you ensure that the volatile var is only
written from a single thread."
    - confines MODIFICATION to a single thread to prevent race 
    conditions
    - use of volatile provides visibility guarantee

### Downsides
- Very fragile because it can't rely on languages features (
i.e. visibility modifiers or local vars) to confine the object to 
the target thread. 
    - NOTE: sometimes the use of single-threaded subsystems 
    provides simplicity in a manner that it outweighs the
    fragility of ad-hoc thread confinement
    
## 3.3.2 Stack Confinement
- (a.k.a Within-Thread or Thread-Local usage)
    - not the same as ThreadLocal class.
- flavor of thread confinement in which an object can only be
accessed through local variables. 
    - (encapsulation can make it easier to preserve invariants...)
    - (local variables can make it easier to confine objects to threads...)
    
### Local Variables
- local vars are confined to the executed thread by default. 
- they only exist on the executing thread's stack
    - inaccessible to other threads. 

### PROS
- simpler to maintain than ad-hoc thread confinement
- less fragile than ad-hoc thread confinement
    
### Example
- This example demonstrates how stack confinement provides a more stable method of confinement
than ad-hoc
    - the use of local variables provides a language specific semantic that ensures that primitive
    local variables are ALWAYS stack confined. 
    

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;
        
        /*
            Animals MUST remain confined to the method
            - this tree set is attached to the LOCAL variable 'animals'
            - however, 
                - if we publish the set
                - OR ANY OF ITS INTERNALS
                - we'd break the thread confinement, and "the animals would escape"
        */
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        
        for (Animal animal: animals) {
            if (candidate == null || !candidate.isPotentialMate(animal)) {
                candidate = animal;
            } else {
                ark.load(new AnimalPair(candidate, animal));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }

- There are additional challenges here. 
    - see the doc comment in the example for information on preventing "escape" of local variables
- Using a NON-THREAD SAFE object in a Stack Confinement is still THREAD-SAFE
    - just make sure that the USE CASE of the stack confinement is clearly documented so 
    modifications made by other developers (or calls by other devs) don't allow the
    references to escape. 
    
## 3.3.3 ThreadLocal
- a more formal means of maintaining thread confinement
    - allows the association of a "per-thread" value w/ a "value-holding object"
    - provides get() and set() methods that maintain a separate copy of the value for each
    thread that uses it. 
        - get() returns the most recent value passed to set() from the CURRENTLY EXECUTING THREAD
- IMPL DETAIL
    - threads-specific values are stored in the Thread object itself
    - when thread terminates, the thread-specific values are released to be GC'd
- Avoid using ThreadLocal as a way to use global variables. 
    - global vars are typically an antipattern
        - poor reusability
        - introduce tight coupling. 

### USE CASES
- most common use case
    - prevent sharing in designs based on 
        - Mutable Singletons
        - Global Variables
- commonly used in application frameworks
        
        
    EX:
    
        private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>() {
            public Connection initialValue() {
                return DriverManager.getConnection(DB_URL);
            }
        };
        
        public static Connection getConnection() {
            return connectionHolder.get();
        }
- a Benefit of this technique
    - can be used when a frequently used task requires a temp. object (i.e. a buffer)
        - avoids the reallocation of the temp. object on each invocation

### Thread-Local Variables (Single to Multi Thread App Conversion)
- ThreadLocal can be used to preserve thread safety by
    - converting shared global variables into ThreadLocals
    - an app-wide cache wouldn't be as useful as if it were turned into a number of thread-local caches
    

