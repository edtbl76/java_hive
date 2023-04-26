# 4.2 Instance Confinement

Discussion of techniques for safe use of non-threadsafe objects in 
multithreaded programs
- thread confinement 
    - constraining object to single-threaded access
- object is guarded by a lock


## Encapsulation and Instance Confinement
- when encapsulating an object in another object, it has an interesting benefit
    - all code paths that access the encapsulated (compositional) object
    can be identified. 
- **it is easier to analyze/identify possible states for encapsulated objects
than it is if objects were accessible to the entire program**

PROS
- one of the easiest ways to build thread-safe classes
- allows flexibility in terms of locking strategy
    - supports different state variables using different types of locks. 
 

## Confinement + Locking
- the encapsulation of data inside an object confines access to the methods
of that object
    - this makes it MUCH easier to guarantee that data is always accessed
    w/ the given lock held
- providing an appropriate locking approach in conjunction w/ confinement
allows the possibility of thread-safe operation on otherwise non-thread-safe
objects. 


    EX:
    
        // Confinement used to ensure Thread Safety
        @ThreadSafe
        public class PersonSet {
            @GuardedBy("this")
            private final Set<Person> mySet = new HashSet<Person>();
            
            public synchronized void addPerson(Person person) {
                mySetadd(person);
            }
            
            public synchronized boolean hasPerson(Person person) {
                return mySet.contains(person);
            }
        }
        
- NOTE: if Person isn't threadsafe, and it is mutable, then we'd need
to provide additional synchronization when accessing it from the PersonSet
     - OPTION 1: make it thread-safe
     - OPTION 2: guard the Person objects w/ a lock and guarantee that clients
        follow the proper acquired/release mechanics for accessing it. 
            - (Less reliable, because it is client-controlled)
            
    
        

## Confined Objects
- must NOT escape intended scope
- Possible means of escape
    - publishing an object that isn't confined, despite being documented as such
        - (i.e. impl bug or documentation bug)
    - accidentally letting an object escape from intended scope due to impl bug
    - publishing OTHER objects (iterators/inner class instances) that may
    indirectly publish the confined objects. 

### Confinement Options/Scopes
- class instance
    - i.e. private class member
- lexical scope
    - local variable
- thread
    - object passed from method to method inside a thread, but not concurrently
    shared across threads. 


## Confinement examples
- ArrayList, HashMap not thread-safe
    - Collections.synchronizedList() (and others) are wrapper factory methods
    that allow the thread-safe objects to be used safely in multithreaded envs. 
    - Details
        - factory uses Decorator to wrap collection w/ synchronized wrapper object
        - wrapper impls each method of appropriate interface as a synchronized method
        - method forwards request to underlying collection object
        - as long as underlying collection is only reachable by the wrapper
            - is thread-safe. 
        - Javadoc contains verbage to the effect that the access to the
        underlying collection should be made through the wrapper. 
        
## 4.2.1 Java Monitor Pattern
- This is a pattern that defines Java objects that
    - encapsulates ALL mutable state
    - guards mutable state w/ object's own intrinsic lock
- used by many lib classes
    - Vector, HashTable
- PRO/ADVANTAGE
    - simplicity
    
    
    EX: 
    
        // Simple Thread-safe Counter
        // Uses "Java Monitor Pattern"
        
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
    

NOTE: The Java monitor pattern is a CONVENTION that just happens to use
an object's own intrinsic lock for the purposes of simplicity. 

Here is an example of a class using a private lock to guard its state. 
- This is NOT the Java monitor pattern
  
    
    EX:
    
        public class PrivateLock {
            private final Object myLock = new Object();
            @GuardedBy("myLock")
            Thing thing;
        
            void doIt() {
                synchronized(myLock) {
                    // Access/modify state of Thing
                }
            }
        }

### Advantages of Private vs. Public Locks
- Public locks (like intrinsic locks) are accessible by client code
    - requires client code to participate in synchronization policy
        - if client improperly acquires a lock it can cause LIVENESS problems
        - verifying proper use of public locks requires examining an entire
        program rather than single class. 

### "Monitor Locks/Monitors"
The Java monitor pattern is inspired by Hoare's work on **monitors**, though
there are significant differences between this pattern and a true monitor. <br>
The bytecode instructions for entering/exiting a synchronized block are 
even called **monitorenter** and **monitorexit**, and Java's built-in (intrinsic) locks are sometimes
called **monitor locks** or **monitors**.


## 4.2.2 Java Monitor Pattern - Continued
(See Classes.MutableVehicleTracker and Classes.MutablePoint)
- VehicleTracker classes
    - encapsulates identity + locations of known vehicles.
    - This provides the "Model" of an MVC GUI design
        - the model would be shared by multiple view and/or updater threads
- MutablePoint
    - not thread safe, but since the tracker is, we can control the 
    publication of the MutablePoint via the tracker. 
    - values are returned via a deepCopy
        - this creates a new Map that copies the elements (keys/values)
        from the original map.
        - REMEMBER
            - deepCopy can't just wrap the Map w/ an unmodifiable map
            because that only protects the collection/infrastructure
            from modification
                - callers would technically still be able to modify the values
            - a standard copy constructor wouldn't work because it
            would only copy the references to the points, rather than
            the objects themselves
        
        
    EX:
        
        /*
            view thread might fetch names of locations and render
            them for a screen/display
        */
        
        Map<String,Point> locations = vehicles.getLocations();
        for(String key: locations.keySet()) {
            renderVehicle(key, locations.get(key));
        }
        
    
        /*
            updater threads would modify locations
            w/ data received from devices or manually entered
            through some GUI
        */
        void vehicleMoved(VehicleMovedEvent event) {
            Point location = event.getNewLocation();
            vehicles.setLocation(event.getVehicleId(), location.x, location.y);
        }
        
- the view/updater threads might access/modify the data concurrently
    - threadsafety required!
    
### Thread Safety mechanism
- in this example, thread safety is maintained by copying mutable data
before returning it to the client.
    - this means that clients only get a copy of the data, not the
    original data. 

#### Potential Consequences of this approach 
- performance can be a problem if one of the sets of objects is very large
    - (in this case it is vehicles)
    - it is important to know your use cases/know your data. 
    - 99% of the time this isn't going to be an issue
    - However... since deepCopy() is called from a synchronized method
        - the tracker's intrinsic lock is going to be held for the duration of
        the copy (which will take a while for a large collection)
        - this might result in UI responsiveness degradation. 
- each call to getLocation() will return the same results even if
the underlying pieces change. 
    - this is strong consistency. 
    - getLocation() is getting a "snapshot" view of the data. The price of
    strong consistency is typically latency. 
    