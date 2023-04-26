# 4.1 Designing a Thread-safe Class
Thread safety requires a clear understanding of an object's
invariants and postconditions

## Encapsulation
One of the benefits of encapsulation is that it makes it possible to 
determine if a class is thread-safe w/o having to examine the entire
program

## Basic Elements of a Thread-safe class
- Identify variables that form the object's state
- identify invariants that constrain the state variables
- establish a policy for managing concurrent access to the object's state

### Object State Hierarchy
- If object's fields are primitive
    - the state is simple
- If object's fields are reference
    - the state is "complex", such that the object's state includes the
    fields from the referenced objects, and so on.
    
    
**synchronization policy**
defines how an object manages access to its state w/o breaking invariants
or postconditions
- specifies what combination of the following are used to maintain thread safety
    - immutability
    - thread confinement
    - locking
    - which variables that are guarded by locks
- this should be documented in order to ensure that the class can be maintained


    EX: 
    
        @ThreadSafe
        public final class Counter {
            @GuardedBy("this")
            private long value = 0;
            
            public synchronized long getValue() {
                return value;
            }
            
            public synchronized long increment() {
                if (value == Long.MAX_VALUE) {
                    throw new IllegalStateException("counter overflow");
                }
                return ++value;
            }
        }
        
## 4.1.1 Gathering Synchronization Requirements
- A thread safe class is one whose invariants hold true under concurrent
access

## State Space
- the range of possible states that objects/variables can take on. 
- "final fields" reduce the total state space, because immutable
attributes can only be in the state that they were init'd to.

### Validity
- Many classes can identify states as 'valid/invalid'
    - EX: a Counter that uses a long
        - ranges from Long.MIN_VALUE -> Long.MAX_VALUE
        - no negative numbers

### State Transitions
- similar to a state, in some situations, this may also be validated/invalidated
    - EX: a Counter that should monotonically increase can only move
    to the next whole number value
        - i.e. if current is 1, then next has to be 2

### State/State Transition Constraints + Concurrency
additional constraints/invariants means additional requirements to
synchronization and/or encapsulation
- this is especially true if an object/field constraint includes invalid states/transitions

If there are NOT additional constraints/invariants
- then this provides areas within the code that synchronization/encapsulation 
requirements can be relaxed
    - greater flexibility
    - performance optimization
    
### Multivariable invariants 
- some classes have invariants that provide constraints on multiple variables
    - EX: if there is a numeric range, the minimum has to be less than or
    equal to the maximum, and the maximum has to be greater than or equal to
    the minimum
- This creates atomicity requirements 
    - related variables must be updated/fetched in a single atomic operation

## 4.1.2 State-dependent Operations
- Class Invariants + Method Postconditions
    - constrain valid states/state transitions
    
### State-Dependent Operations
- these are methods that constraints on valid state when the operations
are invoked
- EXAMPLES
    - can't delete a file that doesn't exist
    - can't remove something from an empty queue
    - can't write a file to a folder if the folder doesn't exist.  
- in SINGLE-THREADED programs
    - state-dependent operations must fail if the preconditions aren't met
- in CONCURRENT programs
    - it is possible that an invalid precondition may become valid at
    some point in the future. 
    - this means the program can WAIT for a precondition to become valid
    and then proceed w/ the operation
    
#### Wait and Notify
- built-in mechanisms for efficiently waiting for state-dependent operations
to become true
- tightly bound to INTRINSIC LOCKING
- Recommended to use existing lib classes to create operations that wait
for preconditions to come true
    - blocking queues
    - semaphores
    - "synchronizers"
    
## 4.1.3 State Ownership
- object state consists only of the data that the object OWNS. 

### GC + Abstraction
- The GC in the JVM abstracts the need to think about ownership and
transferring ownership because it does the heavy lifting in terms of
clean up. 

### Ownership + Encapsulation
- object encapsulates the state it owns
- object owns the state it encapsulates

### Control is implied...but weakly
- ownership of a given state is implied
- publishing references to mutable objects relinquishes exclusivity to 
control and creates "shared ownership"
- classes are usually not designed to "own" the objects passed to 
its methods or constructors
    - (unless the method is designed EXPLICITLY to transfer ownership of
    objects passed in)
    - (EX) synchronized collection wrapper factory methods
    
### Collections and Split Ownership
- Collection objects owns the state of the collection infrastructure
- client code owns the object stored in the collection


    EX:
    
        ServletContext
        
            - Map-like object container service is provided to servlets
            - servlets register/retrieve app objects
                - setAttribute/getAttribute
                
            - ServletContext object
                - thread-safe, because it can be shared concurrently
            
            - Servlets don't need synchronization when calling setAttribute/getAttribute
            - BUT, they MAY need to use it when USING the objects stored in the ServletContext
            
        
 - REMINDER
 - in order to prevent interference from multiple threads accessing same
 object concurrently
    - thread-safe
    - effectively immutable
    - explicitly guarded by a lock. 
    