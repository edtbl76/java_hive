# 14.4 Anatomy of a Synchronizer

### ReentrantLock vs. Semaphore
- Interface similarities
    - both classes act as a "gate"
        - threads arrive at the gate
            - threads are allowed to pass
                - (**lock** or **acquire** returns successfully)
            - threads are forced to wait
                - (**lock** or **acquire** blocks)
            - threads are turned away
                - (**tryLock/tryAcquire** return false)
                - indicates that lock/permit didn't become available within the given time budget
    - both allow various types of lock acquisition
        - interruptible
        - untinterruptible
        - timed acqusition
    - both support fair/nonfair queueing of waiting threads        
            
            
            
### Counting Semaphore Implemented using Lock
- (see Examples.SemaphoreOnLock)
    - NOTE: this is NOT the way that **java.util.concurrent.Semaphore** is impl'd
    - this is just a demonstration of how closely related the two classes are.
    
### (AQS) AbstractQueuedSynchronizer
- framework for building locks and synchronizers.
    - a common base class used for the implementation of many other synchronizers
        - ReentrantLock, Semaphore, ReentrantReadWriteLock, FutureTask

- handles the gory details of synchronizer implementation
    - FIFO queuing of waiting threads
    - provides the flexible criteria by which individual synchronizers use to 
    allow threads to pass or to require them to wait
    

#### Benefits of AQS
- specifically designed for scalability
- substantial reducation of impl 
- Synchronizers built w/ AQS only have a single point of contention, whereas those
built on top of another synchronizer typically have 2 or more. 
    - reduces context-switching overhead
    - (improves throughput!)

    
    
    EXAMPLE
        
        SemaphoreOnLock has two points of contention
            - (i.e. where the smeaphore might block when acquiring a permit) 
            
            1.) at the lock guarding the semaphore state
            2.) again if permit isn't available
            
        
        Synchronizers built w/ AQS have ONE point of contention
            - only point of contention is where the synchronizer might block.
        
    
    