# 3.5 Safe Publication

## What if we DO want to share objects across threads? 

Don't do this:


    EX:
    
    // unsafe publication
    public Holder holder;
    
    public void initialize() {
        holder = new Holder(42);
    }
    
Visibility Problems
- Holder could appear to other threads in an inconsistent state
(regardless of whether or not invariants were established correctly)
- other threads observe it as "Partially Constructed Object"

## 3.5.1  Improper Publication: When Good Objects Go Bad. 

### Partially Constructed Objects
- may appear in an inconsistent state to observing threads
- state appears to "suddenly change", despite no modifications
since publication


    EX: 
    
        "Class at Risk of Failure if Not Properly Published"
        
        public class Holder {
            private int number;
            
            public Holder(int number) {
                this.number = number;
            }
            
            public void assertSanity() {
                if (number != number) {
                    throw new AssertionError("False");
                }
            }
        }
        
- Technically Speaking, this should never fail, because we are
checking if a variable is equal to itself. 
- BUT, if we were to publish using the example in the intro section, 
it's actually possible for the "sudden change" to occur such that the
number becomes unequal to itself. 

### "Not Properly Published"
- This is the terminology used to describe a case where synchronization isn't
used to make a Holder visble to other threads. 
- PROBLEMS
    - other threads can see stale values for the holder field
        - null reference, older value, etc. 
    - other threads can see correct value for holder reference, but stale values for the STATE of the holder. 
    - thread can see stale value on first read, and updated value on next read
        - (i.e. assertSanity can actually throw an AssertionError)
        
## 3.5.2 Immutable Objects and Initialization Safety
- Java Memory Model offers "special guarantee" of INITIALIZATION SAFETY for
sharing immutable objects. 

### Mutable Objects...
- require synchronization to guarantee a consistent view of the object's state. 

### Immutable Objects...
- can be safely accessed "even when synchronization is not used to publish the object reference" 
- (reminder on rules for immutability)
    - unmodifiable state
    - all fields are final
    - proper object construction
- guarantee extends to values of ALL final fields of properly constructed objects
    - EXCEPTION
        - if a final field refers to a MUTABLE OBJECT, synchronization is
        still required to access the state of the objects the field refers
        to.
        
## 3.5.3 Safe Publication Idioms

### Safe Publication of mutable objects
- Requirements
    - synchronization by publishing thread
    - synchronization by consuming thread
- Visibility
    - reference to object AND object's state must be made visible to 
    other threads at the same time. 
    - (as long as the state isn't changed again, this is technically
    sufficient to ensure that any access is safe.)

### Ways to safely publish properly constructed object
- initialization of an object reference from a static initializer
- Storing a reference to it into
    - a volatile field
    - an AtomicReference
    - a final field (of a properly constructed object)
    - a field properly guarded by a lock. 

### Thread-safe libraries + Safe Publication Guarantees
- placing key/value in the following collections will safely publish it/them
to any thread that retrieves them from the structure 
    - (NOTE: this holds true directly OR via iterator)
    - Maps
        - Hashtable
        - synchronizedMap
        - ConcurrentMap
    - Collections
        - Vector
        - CopyOnWriteArrayList/Set
        - synchronizedList/Set
    - Queues
        - BlockingQueue
        - ConcurrentLinkedQueue
- Futures and Exchangers provide safe publication

### Static Initializer
- easiest/safest way to publish objects that can be statically constructed


    EX: 
        public static Holder holder = new Holder(42);
        
- Static initializers are exec'd by JVM at class init time. 
    - this uses internal JVM synchronization
    - objects init'd this way are guaranteed to be safely published. 
    
## 3.5.4 Effectively Immutable objects. 
- Effectively Immutable objects are those objects that are not declared
immutable, but are never modified after publication. 
    - i.e. if the fields/variables/objects are "treated" by an app
    as though they were immutable after being published, then 
    they will be thread safe
    - they don't meet the "strict" definition of immutability
    
PRO
- simplifies development
- improves performance (reduces need for synchronization)

CON
- challenging to enforce or even be aware of the invariant 
- (i.e. easy to accidentally make code modifications to 
the objects w/o realizing that they are intended to be effectively 
immutable)


    EX:     
        
        public Map<String, Date> lastLogin = Collections.synchronizedMap(
            new HashMap<String, Date>()
        );
        
- if the Date values are never modified after being placed in the Map, then 
the synchronization in the synchronizedMap is good enough to publish the Date values safely. 


## 3.5.5 Mutable Objects
- If an object can be modified after it is created, then safe publication only guarantees 
visibility of the "as-published state"
- synchronization must be used to:
    - publish mutable object
    - update object every time it is modified in order to guarantee that the changes are visible to
    other consuming threads. 
- i.e. 
    - "to share mutable objects safely"
        - the objects must be safely published AND
        - thread-safe/guarded by a lock
        
        
        
## Mutability + Safe Publication 

|   |   |
|---|---|
| Immutable Objects | published by any mechanism |
| Effectively Immutable Objects | must be safely published |
| Mutable Objects | safely published AND threadsafe or guarded by a lock |

## 3.5.6 Sharing Objects Safely

### Policies

**Thread Confined**: 
- a thread confined objects is
    - owned exclusively by and confined to one thread
    - AND can be modified by its owning thread. 

**Shared read-only**:
- Immutable and effectively immutable objects
- a type of object that can be accessed concurrently by multiple threads w/o additional synchronization
    - it CANNOT be modified by any thread
    
**Shared thread-safe**
- a type of object that performs synchronization internally
    - multiple threads can freely access this type of object via its public interface w/o further
    synchronizaiton
    
**Guarded**
- a type of object that can be accessed ONLY with a specific lock held
    - these types of objects are
        - objects encapsulated inside other thread-safe objects
        - published objects known to be guarded by a specific lock
   