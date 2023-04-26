# Concurrent Collections

### Poor Concurrency in Synchronized Collections
Synchronized Collections achieve thread safety by serializing all 
access to the collection's state. 
- If multiple threads contend for the collection-wide lock, throughput
suffers. 

### Concurrent Collections Improvements 
- designed for concurrent access from multiple threads

    
    Example:
    
        Java 5 Improvements
        --------------------
        
        ConcurrentHashMap       (Replaces Sync hash-based Map impls) 
        CopyOnWriteArrayList    (Replacement for sync'd List impls,
                                usually where traversal is the dominant
                                operation)
        
        ConcurrentMap           interface that provides support for
                                common compound actions
                                - "put-if-absent"
                                - "replace"
                                - "conditional remove"
                                
        Java 6 Improvements
        -------------------
        
        ConcurrentSkipListMap   (replacement for SortedMap)
        ConcurrentSKipListSet   (replacement for SortedSet)
        
            (This might be a TreeMap/Set wrapped w/ synchronizedMap)
                                
        
                                
### Best Practices
- Replacing Synchronized Collections (Bad) with Concurrent Collections 
offers dramatic scalability improvements w/ little risk. 

### Queue and BlockingQueue

#### Queue
Intended to temporarily hold a Set of elements while waiting to be
processed.
- These are NON BLOCKING CALLS
- retrieval/get operations return NULL if queue is empty


    NOTE: 
        LinkedList implements Queue. 
        - this eliminiates the random access requirements of a List, 
        which provides more efficient concurrency

##### ConcurrentLinkedQueue
classic FIFO 

##### PriorityQueue
priority ordered queue (NOT CONCURRENT)

#### Blocking Queue
- extends Queue to add support for blocking insert/retrieval
- if queue is empty
    - gets will block until an element is available
- if queue is full (for bounded queues)
    - insert blocks until space is available. 
    
    
    NOTE: 
        BlockingQueues are very useful to the 
        producer-consumer pattern
    
## 5.2.1 - ConcurrentHashMap
- Sync collections hold a lock for duration of each operation.
    - every method is synchronized on a common lock
    - (restricts access to 1 thread at a time.)
- Remember: 
    - if hash function doesn't evenly distribute values, the
    elements will turn a hash table into a linked list, leading
    to poor traversal performance. 
    - during latent traversals, the collection is inaccessible 
    (Blocked) from other threads. 
    
### Lock Striping
This is a locking strategy that provides for better concurrency/scalability.
- more fine-grained, allowing a greater degree of shared access. 
- higher throughput
    - many reading threads can have concurrent access
    - readers and writers can access map concurrently
    - very little perf hit for single-threaded access. 
    
### Resilient Iterators (Weakly Consistent > Fail Fast)

#### Synchronized collections used Fail Fast iterators
    - these throw ConcurrentModificationException when there is an attempt to change data
    while the collection is being iterated. 
    - this forces the collection to be LOCKED during iteration
    
#### Weakly Consistent Iterator
- These can tolerate concurrent modification
- traverses elements "as they existed when iterator was constructed"
- MAY reflect modification to collection after construction of iterator
    - (This is not a guarantee)
    
### Tradeoffs between Synchronized and Concurrent Collections

#### For Better...
- the weakened consistency of operations that represent quantities that are moving targets (see
below) are done so to improve the performance of
    - get(), put(), containsKey() and remove()

#### ... or for worse.
- semantics of methods that operate on entire Map are weakened to reflect concurrent nature of
collection
    - i.e. operations reflect information that may not be current. 
    

    Examples:
    
        size() could be stale by the time it is computed. 
            - the result is only an estimate/approximation of the actually value
            
        isEmpty()
            - maybe not actually. 
            
        
#### Exclusive Access, synchronized vs. concurrent

ConcurrentHashMap
- doesn't support locking the map for exclusive access
- concurrent collections are EXPECTED to change their contents continuously

synchronizedMap
- can lock map for exclusive access
- "unusual cases"
    - adding several mappings atomically
    - iterating Map several times and needing to see the same elements in the same order.
    

    
    NOTE:
        When designing for scale, there are only two scenarios where it makes sense to 
        choose synchronizedMaps > ConcurrentHashMap
        
            1.) When the map needs to be locked for exclusive access
            2.) If we are relying on the synchronization side effects of the synchronized Map
            implementations
            

## 5.2.2 - Additional Atomic Map Operations


    Problem
    -------
        - since ConcurrentHashMap doesn't support locking for exclusive access, it can't use
        client-side locking to create new atomic operations (e.g. "put-if-absent")
        
    Solution
    --------
        
        ConcurrentMap (iface) provides a number of common compound operations that have
                              atomic operations
                              
            - put-if-absent
            - remove-if-equal
            - replace-if-equal
            
    
        public interface ConcurrentMap<K,V> extends Map<K,V> {
            // Insert into map only if no value is mapped from K
            V putIfAbsent(K key, V Value);
            
            // Remove only if K is mapped to V
            boolean remove(K key, V value);
            
            // Replace value only if K is mapped to oldValue
            boolean replace(K key, V oldValue, V newValue);
            
            // Replace value only if K is mapped to some value
            V replace(K key, V newValue);
        }
            

## 5.2.3 CopyOnWriteArrayList
This is a concurrent replacement for synchronized List.
- better concurrency in common situations
- eliminates need to lock/copy the collection during iteration


    NOTE:
        CopyOnWriteArraySet is a concurrent replacement for a synchronized Set. Unless
        otherwise specified within this document, the comparisons may be used 
        interchangeably
        
### Thread-Safety Mechanism
- as long as an "effectively immutable object" is properly published, then no further
synchronization is required when being accessed. 

- Mutability implementation
    - a collection is created and a new copy is republished every time the collection is 
    modified
- Iteration
    - iterators retain a ref to the backing array that was current at the start of iteration
    - since that never changes, there is only a need to briefly synchronize in order to ensure 
    visibility of array contents
    - multiple threads can iterate the collection w/o interference from one another (or from
    threads who want to modify the collection)
    - no ConcurrentModificationExceptions
    - elements are returned exactly as they were at the time the iterator was CREATED (regardless
    of any subsequent modifications)
    
#### Copy Cost
- Copying the backing array every time there is a modification can be expensive, especially as
the collection grows considerably large. 

    
    TIP:
        copy-on-write collections should only be used when ITERATION is for more 
        common than MODIFICATION. 
        
    NOTE:
        This pattern is very common in event-notification systems, where delivering a
        notification requires:
        
        a.) iterating a list of registered users
        b.) calling each one
        
        registering/unregistering is FAR less common than receiving event notifications.
     
  
        





