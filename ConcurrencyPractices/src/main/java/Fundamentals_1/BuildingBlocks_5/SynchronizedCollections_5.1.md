# 5.1 Synchronized Collections

## List Of
- Vector, Hashtable (don't use these)
- synchronized wrapper classes
    - Collections.synchronizedXxx factory methods
    
### Thread Safety and Synchronized Collections
Sync. collections achieve thread safety by
- encapsulating state
- synchronizing all public methods so that only one thread at a time
can access the state of the collection
    - "Thread confinement"
    
## 5.1.1 Problems with Synchronized Collections

### Problem 1: Interleaving of Multiple Operations
- may required client-side locking to guard compound actions. 
    - **iteration** (fetching elements until we reach the "end" of the collection)
    - **navigation** (finding next element after current based on some ordering)
    - **conditional operations** (i.e. like put-if-absent, CHECK-THEN-ACT), if some condition, then do, else don't
    
    
    EX:
    
        public static Object getLast(Vector vector) {
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
        
        public static void deleteLast(Vector vector) {
            int lastIndex = vector.size() - 1;
            vector.remove(lastIndex);
        }
- These methods are compound actions
    - get last element
    - do the thing
- They demonstrate how the client might get inconsistent results
    - even thought the underlying vector is fine, it is possible for a client to get inconsistent results
    
    
    EX:
    
        Client calls getLast() then deleteLast()
        
        1.) each call ends up on separate threads (T1 and T2) 
        2.) both T1 and T2 get the size of the array, which is x
        3.) T2 isn't busy, so it jumps ahead, interleaves, and removes the element x. 
        4.) T1 catches up, still thinking that the last element is x, and tries to return it. 
        5.) it can't get the last value because it is gone and BOOM SHAKALAKA -> ArrayOutOfBounds
        
        
### Resolving Interleaving of two operations
- sync collections sync policy supports client-side locking. 
    - allows creation of new operations that are atomic to other collection operations
    - requires knowledge of which lock to use. 
    
    
    EX:
    
        public static Object getLast(Vector vector) {
            synchronized (vector) {
                int lastIndex = vector.size() - 1;
                return vector.get(lastIndex); 
            }
        }
        
        public static void removeLast(Vector vector) {
            synchronized (vector) {
                int lastIndex = vector.size() - 1;
                vector.remove(lastIndex);
            }
        }
        
### Problem 2: Iteration

    EX: 
    
        for (int i = 0; i < vector.size(); i++) {
            doTheThing(vector.get(i));
        }
        
- The vector itself is thread-safe, but the contents of the vector can be changed by different threads. 
- While iterating through vector, it is possible for the contents to be changed, resulting in the
same outcome as above (interleaving due to competing operations)

### Solution 2: Client Side Locking

        EX:
            synchronized (vector) {
                for (int i = 0; i < vector.size(); i++) {
                    doTheThing(vector.get(i));
                }
            }
- this isn't great, because we are holding a lock for the entire duration of the iteration
    - GOOD -> prevent other threads from modifying the vector
    - BAD -> prevent other threads from READING the vector
        - WHY BAD? This is bad because it decreases scalability.
        

## 5.1.2 Iterators and ConcurrentModificationException

### Iteration Best Practices
- Use an Iterator
- use for-each loop syntax (enhanced for loop)
- NOTE: You MUST use Iterator.remove() to remove items from a collection during iteration to 
avoid throwing ConcurrentModificationException.

### Iteration Characteristics
- the mechanisms mentioned above are not designed for concurrent modification. 
- They are **FAIL FAST**: they throw ConcurrentModificationException if the collection has changed
since the iteration began
    - This is best effort, It can also happen in single-threaded code when removing elements directly


    EX:
        
        // Code that might throw a ConcurrentModificationException
        
        List<Thing> thingList = Collections.synchronizedList(new ArrayList<>());
        
        for (Thing thing : thingList) {
            doTheThing(thing);
        }    

### Cons to Locking Collections During Iteration
- any thread that needs to read the state of the collection while it is iterating will block until the
iteration is complete. 
    - this could be a long time for large collections
- if there is a method being called w/ a LOCK held, it is possible for a deadlock to occur
- locking collections (as we discussed) hurts scalability
    - the longer a lock is held, the more likely there is for contention
    - the greater the number of threads being blocked waiting for a lock, decreases throughput
    and CPU utilization. 
    
### Simple Solution (Alternative to Locking)
- cloning a collection and iterating the copy
    - clones are thread-confined so no other thread can modify them
        - (eliminates ConcurrentModificationException)
    - (You MUST lock the collection while it is being cloned)

#### DOWNSIDE
- this has a performance cost. 
    - what is the size of the collection? 
    - how much work is done per element? 
    - how often is iteration performed relative to other collection operations? 
    - what are the responsiveness and throughput requirements? 
    
    
## 5.1.3 Hidden Iterators. 
- One of the disadvantages of using locking to prevent ConcurrentModificationExceptions on iterators is
that you have to remember to use a lock in every place that a shared collection might be iterated. 
    - 1 this is tedious even if you can find all of the iterators
    - 2 there are such circumstances as hidden iterators making it hard to identify all of 
    the potential failure points.



    EX:
        
        // DO NOT DO THIS.
        
        public class HiddenIterator {
            @GuardedBy("this")
            private final Set<Integer> set = new HashSet<>();
            
            public synchronized void add(Integer integer) {
                set.add(integer);
            }
            
            public synchronized void remove(Integer integer) {
                set.remove(integer);
            }
            
            public void addTenThings() {
                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    add(random.nextInt());
                }
                System.out.println("DEBUG: added ten elements to " + set);
            }
        }
