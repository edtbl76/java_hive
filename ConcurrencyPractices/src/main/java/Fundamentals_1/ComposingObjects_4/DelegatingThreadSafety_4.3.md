# 4.3 Delegating Thread Safety

Most objects are composite objects, which might already be thread-safe. Java
monitor pattern is really meant for creating new classes or building compositional 
containers for non-thread safe objects

## Safe Composites
- A safe composite is a class built of thread-safe components that is
thread-safe. 

### (See Classes.CountingFactorizer)
- AtomicLong added to stateless object
- resulting composite object is STILL thread safe. 
    - state of CountingFactorizer IS the state of the AtomicLong
    - CountingFactorizer imposes no additional validity constraints 
- CountingFactorizer **delegates** its thread safety to the AtomicLong
    - i.e. CountingFactorizer is thread safe BECAUSE AtomicLong is

#### A note on non-final attributes
- if the Atomiclong (count) were NOT final, then determining thread-safety would be more 
challenging
    - it is possible that CountingFactorizer would be able to 
    modify count to reference a different AtomicLong. 
        - any modification to this attribute would require communicability to
        all threads that might access it
        - modifications to count should not be able to cause a race condition


### Delegation Example (See Classes.DelegatingVehicleTracker)
- This is similar to the MonitorVehicleTracking w/ a few differences
    - no explicit synchronization is necessary
    - The locations are tracked w/ a thread-safe map impl (ConcurrentHashMap)
        - all access to state is managed via this collection
        - all keys/values of this map are immutable.
    - MutablePoint is replaced w/ an immutable Point class. 
        - Point is thread safe
        - since Point is immutable, it can be freely shared and published
        - this means we don't have to perform the deepCopy operation

#### What if we used MutablePoint?
- this breaks encapsulation, because getLocations() would allow the
publishing of a reference to non-threadsafe mutable state. 

#### Consistency
- Another major difference is that the returned result is a LIVE/LATEST
immutable result. 
- depending on use case, this can be good/bad
    - your view of the data will be very current, but it's possible to 
    end up w/ an inconsistent view of the data. 
    
    
Strong Consistency - Instance Confinement 
- calls to getLocation() return the same result even if the underlying
values change

Eventual Consistency - Delegation
- calls to getLocation() always return the latest data. 

Strong Consistency - Delegation
- this can be achieved by providing a shallow copy of the locations map. 
    - since the map contents are immutable, we can get away w/ just
    copying the structure fo the map. 
    
    
    EX:
        We can achieve Strong Consistency in delegation by
        replacing the getLocations() method in DelegatingVehicleTracker
        w/ the following
        
        public Map<String, Point> getLocations() {
            return Collections.unmodifiableMap(
                new HashMap<>(locations));
            )
        }
    

## 4.3.2 Independent State Variables
- Examples above demonstrate delegation to 
    - "single thread-safe state variable"

### Multiple State Variables
- The way to delegate thread safety to more than one underlying state var is
to ensure that they are **independent** (I.e. no additional invariant is
imposed upon those variables)


    EX: see Classes.VisualComponent
- there is no relationship between KeyListeners and MouseListeners
    - therefore VisualComponent is threadsafe because it delegates its
    thread safety to two independent thread-safe collections
    - (NOTE: CopyOnnWriteArrayList() is the thread-safe impl)
    
    
## 4.3.3 When Delegation Fails
(See Classes.NumberRange)
- Contains 2 thread-safe state variables
    - includes an additional constraint that 'lower' < 'upper'
- mutators for lower and upper "try" to preserve invariant
    - however they are CHECK-THEN-SET actions, which on their own
    do not provide sufficient locking to make them atomic
    
### Remediation
- locking could be introduced to preserve the multivariable invariant
    - a common lock could guard 'lower' and 'upper'
- the class would need to encapsulate the variables so as to not
publish them
    - publishing the variables separately would allow clients to
    subvert the invariant
    
### Delegates and Volatile-Only
(review 3.1 Visibility) <br>
Declaring a variable volatile only is only supported if it doesn't 
participate in a multivariable invariant
- this is similar to delegation 

## 4.3.4. Publishing underlying state variables
General rule:
- a state variable can be safely published if the following are true:
    - it is thread-safe
    - it doesn't participate in any invariants that constrain its value
    - it has no prohibited state transitions for any of its operations
    
### Thread Safe doesn't always mean a class can be safely published
In some cases, a private variable is thread safe, because the class
preserves it's invariants. 
- publishing that variable by making it (or methods that access/modify it)
public could allow clients to violate the invariant
    - by modifying values to an invalid state
    - (technically isn't this a form of escaping??)
    
    
    EX 1: 
        Classes.VisualComponent satisfies the three constraints above, 
        so it can be safely published w/o compromising thread safety
        
### Safe Publishing of Multivariable Invariants using Private Constructor Capture Idiom 
(see Classes.PublishingVehicleTracker and Classes.SafePoint)
- SafePoint is similar to MutablePoint, in that the fields are mutable, 
but the class itself is thread safe
    - This uses a trick known as the **private constructor capture idiom**
    where the constructor takes all dependent variables of a multivariable
    invariant as arguments, and/or returns them all simultaneously as
    elements of an array
        - this trick prevents a race condition from occurring due to 
        CHECK-THEN-ACT operations on one variable in a multivariable invariant
- Comparison w/ Basic Delegate Solution
    - the class still delegates it's thread safety to the underlying 
    ConcurrenthashMap
        - however in the Basic implementation, the contents are immutable
        - in this example, the contents are "thread-safe mutable points"
    - getLocation() still returns an unmodifiable collection preventing
    client code from adding/removing objects
        - It is possible for the client code to change locations directly in
        the SafePoint
        - This means that changes are immediately available which can 
        result in consistency challenges. 