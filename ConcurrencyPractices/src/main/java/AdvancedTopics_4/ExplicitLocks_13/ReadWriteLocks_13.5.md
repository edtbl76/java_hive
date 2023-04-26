# 13.5 Read-Write Locks

### Reentrant Lock 
- impls a standard mutex lock
    - at most: 1 thread at a time can hold a **ReentrantLock**
   
#### Mutual Exclusion
   
    Mutual Exclusion is usually a stronger locking method than is needed to preserve
    data integrity
    
        Therefore it ALSO limits concurrency more than necessary.
        
- conservative locking strategy
    - prevents writer/writer overlap
    - " " writer/reader overlap
    - " " reader/reader overlap
    
### Practical Use Cases
- MANY circumstances use data structures that are *read-mostly*
    - (mutable data structures that are occasionally modified, but mostly read.)
    
#### Locking vs. Performance 
- This is a case where the locking requirements of a mutex are probably too much.
    - a relaxed locking mechanism can allow MULTIPLE readers to access the data structure
    at the same time
        - INVARIANTS
            - each thread must be guaranteed an up-to-date view of the data
            - no OTHER thread can modify the data while the readers are viewing it.
        - BENEFIT
            - Better throughput
            

### Read-Write-Lock
- A relaxed lock intended to increase throughput for "read-mostly" data structures
- allows a resource to be accessed by multiple readers or a single writer at a time, but not both


    Example: 
    
        public interface ReadWriteLock {
            Lock readLock();
            Lock writeLock();
        }
#### Explanation
- This is NOT 2 separate locks
- The ReadWriteLock exposes 2 methods that provide different views of an integrated read-write lock object
    - the readLock() provides a view that allows a reader to read data guarded by the **ReadWriteLock**
    - the writeLock() provides a view that that allows a writer to write data guarded by the **ReadWriteLock**
- allows 
    - multiple concurrent readers
    - ONE writer (the Highlander)
- permits similar impls (like **Lock**) that may vary by:
    - performance
    - scheduling guarantees
    - acquisition preference
    - fairness
    - locking semantics 
    
#### Use Case 
- the main purpose of a **ReadWriteLock** is as a perf optimization
    - situational/opportunistic way to enable greater concurrency.
    - beyond the requirements listed below, the increased complexity will actually
    reduce performance.
    
 
- REQUIREMENTS 
    - frequently accessed *read-mostly* data structures
    - multiprocessor systems. 
        

- PROFILE!
    - There is no "clear" pattern for using a **ReadWriteLock**, so the most deterministic process
    is to profile the app w/ a **ReadWriteLock** and an exclusive lock, and select which one does the
    best job. 
    
    
### Implementation Options for ReadWriteLocks

#### Release Preference
- selecting preference of lock acqusition after writers release the write lock and the work 
queue consists of both readers and writers
    - readers? 
    - writers? 
    - "I asked you first?"


#### Reader Barging
- if a lock is held by readers, but there are writers enqueued, should readers be allowed
to barge or be queued behind writers? 
    - PRO: Reader barging can enhance concurrency
    - CON: It is possible to starve writers. 
    
#### Reentrancy
- Are read and write locks reentrant? 

#### Downgrading
- if a thread holds the write lock, can it acquire the read lock w/o releasing the write lock? 
    - USE CASE: reading your own writes
    - PRO: allows a writer to *downgrade* to a read lock w/o allowing other writers to modify the
    resource. 
        - (maintains the invariants)
        
#### Upgrading
- Can a read lock be upgraded to a write lock immediately? 
    - (preferring the acquisition of the existing thread vs. those enqueued)
    
    
    NOTE: 
        Most read-write-lock impls don't support upgrading
        
        - w/o an explicit upgrade operation, it is prone to deadlocks
        - if 2 readers try to upgrade a write lock at the same time, neither will 
        release the read lock. 

### ReentrantReadWriteLock
- provides reentrant locking semantics for both locks (read and write)

#### Lock Fairness
- Fair Locks
    - preference is given to the thread that has been waiting the longest
    - if a lock is held by readers and a thread requests a write lock
        - no more readers are allowed to acquire the read lock until the write lock 
        has been scheduled and releases the write lock


- Nonfair Locks
    - order in which threads are granted access is unspecified.
    - supports *downgrading* from   (writer -> reader) 
    - does NOT support *upgrading*  (reader -> writer)
    
    
#### Write Lock
- (Like **Reentrant Lock**), **ReentrantReadWriteLock** provides a write lock that has a *unique owner*
    - it can ONLY be released by the thread that acquired it.
    
#### Read Lock
- maintains count of active readers as well as the identity of the threads that have been granted 
the read lock


    NOTE: 
        - This is important. 
        - Enabling the lock to be able to ID the thread allows it to distinguish 
        between an initial or reentrant lock request
        
        Failure to distinguish between these requests would make fair read-write 
        requests deadlock prone.


### Examples
- Read-Write locks improve concurrency best when locks are 
    - held for moderately long time
    - and most operations don't modify guarded resources
    
    
    Example: 
    
        public class ReadWriteMap<K,V> {
            
            private final Map<K,V> map;
            private final ReadWriteLock lock = new ReentrantReadWriteLock();
            private final Lock read = lock.readLock();
            private final Lock write = lock.writeLock();
            
            public ReadWriteMap(Map<K,V> map) {
                this.map = map;
            }
            
            public V put(K key, V Value) {
                write.lock();
                try {
                    return map.put(key, value);
                } finally {
                    write.unlock();
                }
            }
            /*
                rinse and repeat for 
                    - remove()
                    - putAll()
                    - clear()    
            */
            
            public V get(Object key) {
                read.lock();
                try {
                    return map.get(key);
                } finally {
                    read.unlock();
                }
            }
            /*
                Do the same for other read-only Map methods
            */
        
        }

#### Explanation
- uses a **ReentrantReadWriteLock** to wrap a **Map**
    - it can be shared safely by multiple *readers* 
    - guards against *reader-writer* or *writer-writer* conflicts
    
    
    NOTE: 
    
        ConcurrentHashMap performance is so good that it could most likely be used 
        instead of the ReadWriteLock if you only needed a concurrent hash-based map.      

- USE CASE
    - useful for providing better concurrency to an alternate Map impl
        - (e.g. **LinkedHashMap**)        
        
        
#### Performance Comparison between ReentrantLock and ReentrantReadWriteLock
![ReadWriteLock Comparison](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/ExplicitLocks_13/Images/Screen Shot 2021-01-09 at 12.38.00 AM.png)