# 13.4  Choosing Between Synchronized and Reentrant Lock

### Reentrant Lock refresher
- same locking and memory semantics as intrinsic locking
- provides ADDITIONAL features not present w/ intrinsic locking 
    - timed lock waits
    - interruptible lock waits
    - fairness
    - ability to impl non-block structured locking
- provides much better performance than intrinsic locking
    - (This varies from one release to another within Java.)
    
    
    "Reentrant Lock is an advanced tool for situations where intrinsic locking 
    isn't practical. 
    
    Use it if you need its advanced features:
        - timed/polled/interruptible lock acquisition
        - fair queueing
        - non-block structured locking
        
    Otherwise, prefer the use of synchronized
    
    
### Advantages of Intrinsic Locks (over exclusive locks)
- (I'm not sure I agree w/ this... but I digress.. these are the opinions of the author)
- familiarity of notation (ok...)
- terse annotation
- many programs already exist w/ intrinsic locking
    - mixing the two could be confusing and error-prone
- safer than Reentrant Lock
    - Reentrant Lock doesn't automatically release, so you have to make sure to 
    wrap the **unlock** call in a **finally** block.
    
    
    
- The non-block-structured nature of **ReentrantLock** still means that lock acquisitions can't 
    be tied to specific frames (intrinsic locks can)
    - this makes debugging ever-so-slightly easier. 
- **synchronized** is part of the JVM, which supports optimizations
    - lock elision for thread-confined lock objects
    - lock coarsening to eliminate synchronization w/ intrinsic locks (Section 11.3.2)  

  
