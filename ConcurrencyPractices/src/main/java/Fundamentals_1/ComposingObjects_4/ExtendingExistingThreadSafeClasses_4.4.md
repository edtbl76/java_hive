# 4.4 Adding Functionality to Existing Thread-safe Classes. 

## Reuse!
- typically preferred to creating new things because it reduces
    - development effort
    - development risk (because components are already tested)
    - maint. cost
   
### Scenarios
- Best case scenario is that there is a thread-safe class that does
everything we need it to
    - unicorns might also fly out of my ears
- Most likely, we'll find something that does most of what we need it
to do, requiring some degree of augmentation to complete our use case

### Watch for Atomic "Bombs"
An atomic bomb (my term) is when we need to augment a class w/ some
kind of multi-step operation
- EX: 
    - put-if-absent 
        - (i.e. check if it isn't there, and then put)
        - Did i trick you??? No? 
        - This is just CHECK-THEN-ACT.  
        
### Safe Modification
- The best case scenario is that you'll modify an existing class
to support the desired operation
    - this is only possible if you have access to the source code or you
    can modify it. 
    - However, once again, unicorns. Since we are "reusing" code, there is
    a good chance that the code is from a 3rd party source, so modification
    isn't necessarily possible or advisable. 

### Safe Extension
- If we can't modify the source directly, the next best option is to 
extend it
    - (assuming the class supports extension)


    EX:
       // extending Vector with  an atomic check-then-act method
       
       @ThreadSafe
       public class ExtendedVector<E> extends Vector<E> {
         public synchronized boolean putIfAbsent(E e) {
            boolean absent = !contains(e);
            if (absent) {
                add(e);
            }
            return absent;
         }
       } 
            
    
### Fragility
- extending code for the purposes of concurrent programming is 
considered fragile because it fragments the synchronization policy
across multiple disparate files/classes
    - changes to the parent's lock or concurrency measures will
    break the subclasses. 

## 4.4.1 Client-side Locking
The previous two examples won't work w/ thread-safe **synchronized wrapper 
factories** because the client code doesn't know the class of the objects 
returned from those **synchronized wrapper factories**

### Extending Functionality of the Class, without Extending the Class
This "third strategy" works around the problems mentioned above by putting 
the extension code in a helper class

    EX: 
    
        Non-Thread Safe Attempt - DONT DO THIS
        
        @NotThreadSafe
        public class ListHelper<E> {
            public List<E> list = Collections.synchronizedList(new ArrayList<E>()):
            
            // .... other code
            
            public synchronized boolean putIfAbsent(E e) {
                boolean absent = !list.contains(e);
                if (absent) {
                    list.add(e);
                }
                return absent;
            }
        }
Why doesn't this work? 
- the synchronization code is happening on the wrong lock
    - the ListHelper provides **illusion of synchronization**
    - remember, that the operations that ACT on the list are the ones
    that need to acquire/release locks. 
        - these are all different locks
    - This means that putIfAbsent() isn't atomic relative to all of 
    the operations on the list. 
    
#### External Locking (a.k.a. Client-Side Locking)
- external locking means that we must guard client code that uses an object
w/ a lock that the OBJECT uses to guard its own state. 
    - we have to know what lock the object uses. 

    
    EX:
    
        In order to use client-side locking and a helper class, 
        we must use the SAME lock that the object uses. 
        - since the list is the object we want to guard, we need
        to use it's lock. 
    
        @ThreadSafe
        public class ListHelper<E> {
            public List<E> list = Collections.synchronizedList(new ArrayList<E>());
            
            // ... other code
            
            public boolean putIfAbsent(E e) {
                synchronized(list) {
                    boolean absent = !list.contains(e);
                    if (absent) {
                        list.add(e);
                    }
                    return absent;
                }
            }
        }
        
### Fragility
Extension through Helper class is even more fragile than Extension because
the locking code for a class is placed in a separate unrelated class

### Client-side locking vs. Class Extension
- similarities
    - couple behavior of dervied class to implemenation of base class
- differences
    - class extension violates encapsulation of IMPLEMENTATION
    - client side locking violates encapsulation of SYNCHRONIZATION POLICY
    
## 4.4.2 Composition
- least fragile alternative for adding atomic operations to an existing class


    EX: 
        @ThreadSafe
        public class ImprovedList<T> implements List<T> {
            private final List<T> list;
            
            public ImprovedList<T>(List<T> list) {
                this.list = list;
            }
            
            public synchronized boolean putIfAbsent(T t) {
                boolean contains = list.contains(t);
                if (contains) {
                    list.add(t);
                }
                return !contains;
                
            }
            
            public synchronized void clear() {
                list.clear();
            }
        }
        
### APPROACH
- The example demonstrates that we delegate our List<> operations to an underlying list
- we then create an atomic putIfAbsent() method (by synchronization)
    - this is basically how Collections.synchronizedList() works
- similar to the wrappers in Collections, the value is that we are assuming that once the list
has been past to the method's constructor
    - **The client will not use the underlying List again**
    - this is important. The client code **MUST** refer to the list through the ImprovedList, 
    any further access to the underlying collection is a bad escape
