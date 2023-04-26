# Locks
- Lock interface provides an alternate mechanism for handling the SYNCHRONIZATION of blocks of code
    - since it is an interface, you have to use one of is implementations 
        - common use is ReentrantLock
- Locks are considered more flexible and more sophisticated than a synchronized block. 


    EX:
        
        Lock lock = new ReentrantLock();
        
        lock.lock();
        
        // synchronized code
        
        lock.unlock();
        
- When a lock is created, the synchronization begins when its lock() method is called. 
    - this "locks" the lock instance, so that any other thread calling lock() is
    blocked until unlock() is called
- unlock() is called once the critical code is executed. 
    - now threads can access the Lock. 
    
## Lock (Interface) vs. synchronized (keyword)
TIMEOUTS
- It is IMPOSSIBLE to have a timeout to get access to a synchronized block of code
- Lock.tryLock(long timeout, TimeUnit timeUnit) provides a timeout 

FLEXIBILITY
- the synchronized block must be fully contained within a single method
- a Lock can put lock() and unlock() calls in separate methods. 