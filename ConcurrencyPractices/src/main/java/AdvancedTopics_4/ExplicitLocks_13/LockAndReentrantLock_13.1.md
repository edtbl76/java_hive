# 13.1 Lock and Reentrant Lock

### Lock Interface

    public interface Lock {
        
        void lock();
        
        void lockInterruptibly() throws InterruptedException;
        
        boolean tryLock();
        
        boolean trylock(long timeout, TimeUnit unit);
        
        void unlock();
        
        Condition newCondition();
    
    }

- interface that defines a handful of abstract locking operations


- Differences from INTRINSIC locking
    - offers a variety of lock acquisition methods to select from:
        - unconditional
        - polled
        - timed
        - interruptible
    - all lock/unlock operations are EXPLICIT
    
    
    
- Benefits
    - offers the same memory-visibility semantics as intrinsic locks
    - w/ the flexibility to differ in
        - locking semantics
        - scheduling algorithms
        - ordering guarantees
        - perf. characteristics
        
### Reentrant Lock
- implements Lock


- comparison to **synchronized**
    - same *mutual exclusion* and *memory semantics* guarantees
    - acquiring/releaseing ReentrantLock has same mem semantics as entering/exiting synchronized block
    - offers reentrant locking semantics similar to **synchronized** (Section 2.3.2)
    - provides MORE flexibility for dealing w/ *lock unavailability* than **synchronized** because
    it implements Lock (and inherits the various lock acquisition methods) 
    
    
    
    NOTE:
        Memory Visibility:  Section 3.1 and Chapter 16
        
        
### Functional Limitations of Intrinsic Locking
- not possible to interrupt a thread waiting to acquire a lock
- not possible to attempt to acquire a lock w/o being willing to wait forever. 
- must be released within the same block of code that they are acquired
    - PRO: 
        - simplifies coding
        - simple interaction w/ exception handling
    - CON:
        - makes it impossible to use a *non-blockstructured* locking discipline
- once acquisition of a lock has started, there is no way to cancel it
    - this presents a risk to the implementation of time-budgeted activities
        
        
#### Don't throw away the baby w/ the bath water
- Intrinsic locking + **synchronized** do have functional limitations, but in most use
cases they offer "good-enough" performance. 


- The main use case for explicit locks is when the added flexibility offers better liveness or
performance


### Using Lock

    Lock lock = new ReentrantLock();
    ...
    
    lock.lock();
    try {
        // update object state
        // catch exceptions and restore invariants if necessary
    } finally {
        lock.unlock();
    }

#### Compared to intrinisic locks
- slightly more complex
    - lock must be released in the finally block. 
    - (otherwise. if the guarded code throws an exception, the lock would never be released)
        - if you forget to do this, it WILL fail. 
        - Debugging this is incredibly difficult because there is no record of where the Lock 
        should have been released
        
        
        NOTE: FindBugs has a detector that can find this. So do others. 
        
            USE SCA


- slightly more dangerous
   - for the same reason mentioned above. It's easy to end up w/ unlreased locks

- must consider what happens when an exception is thrown outside of the **try** block
    - if object can be left in an inconsistent state then additional **try-catch**, 
    **try-finally** blocks may be needed
    
    
    NOTE: 
        - This really isn't specific to intrinsic or explicit locks. 
        - You should ALWAYS consider the affect of exceptions on guarded code.
        

## 13.1.1 Polled and Timed Lock Acquisition

### tryLock
- provides timed and polled acquisition modes
    - more sophisticated error recovery than unconditional acqusition
 
- deadlocks are fatal when using intrinsic locks
    - the only recovery is app restart
    - the only defense -> construct app so that inconsistent lock ordering is impossible
        - (This is HARD)
        
### Probabilistic Deadlock Avoidance
- alternative defense to deadlocks when using timed/polled locking.


- allows you to 
    - regain control if you cannot acquire all of the required locks
    - then release the locks you were able to acquire
    - take some follow up action
        - either try again from a fresh state
        - log failure and do something else (i.e. send notification?)
        
        
        
#### Example: Dynamic Ordering Deadlock 
- (See Section 10.1.2 for the original example)


        public boolean transferMoney(
                    Account fromAccount, Account toAccount, DollarAmount amount,
                    long timeout, TimeUnit unit)
                throws InsufficientFundsException, InterruptedException {
                
            long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
            long randomModulus = getRandomDelayModulusNanos(timeout, unit);
            long stopTime = System.nanoTime() + unit.toNanos(timeout);
            
            while (true) {
            
                // tryLock tries to acquire fromAccount lock
                if (fromAccount.lock.tryLock()) {
                    try {
                    
                        // tryLock triesd to acquire toAccount lock
                        if (toAccount.lock.tryLock()) {
                            try {
                                if (fromAccount.getBalance().compareTo(amount) < 0) {
                                    throw new InsufficientFundsException();
                                } else {
                                    fromAccount.debit(amount);
                                    toAccount.credit(amount);
                                    return true;
                                }
                            } finally {
                                toAccount.lock.unlock();
                            }
                        } finally {
                            fromAccount.lock.unlock();
                        }
                    }
                    
                    /*
                       Timeout.  
                    */
                    if (System.nanoTime() > stopTime) {
                        return false;
                    }
                    
                    /*
                        Randomization is used to reduce the chance of a livelock
                    */
                    NANOSECONDS.sleep(fixedDelay + random.nextLong() % randomModulus);
                }
            }        
        }

- use **tryLock** to acquire both locks
- by implementing this in a while (loop) we can retry until it 
succeeds
    - requires the back off component
    - sleep is fixed + randomized (to avoid livelock due to synchronization of timers)
- to avoid an infinite loop we set a timeout (stopTime) which allows the system to 
release the locks it has already acquired and fail gracefully.

### Other Use Cases
- timed locks are useful for managing tasks that need to manage a time budget (Section 6.3.7)


- when tasks w/ a time budget call blocking methods, they can supply a timeout that corresponds to
the remaining time in the budget. 
    - this enables tasks to terminate early if they can't deliver the requested result in
    the given window. 
    

#### Example: Travel Portal
(See Chapter 6)
- the application creates a separate task for each car-rental company for which it is soliciting bids
    - bids probably come from a network-based request mechanism
        - (i.e. web service request)
    - it may ALSO require access to a scarce resource
        - such as a direct communications line to the company
        
        
##### Methods for ensuring serialized access to a resource
1. (Section 9.5)
    - use a single-threaded executor
1. (Use a Lock)
    - use an exclusive lock to guard access to the resource
    
    
    public boolean trySendOnSharedLine(String message, long timeout, TimeUnit unit) 
                throws InterruptedException {
        
        long nanosToLock = unit.toNanos(timeout) - estimatedNanosToSend(message);
        
        if (!lock.tryLock(nanosToLock, NANOSECONDS)) {
            return false;
        }    
        
        try {
            return sendOnSharedLine(message);
        } finally {
            lock.unlock();
        }      
    }
    
- EXPLANATION
    - we guard the communications line w/ a Lock. 
    - the code will attempt to send a message on that shared resource (the comm line)
    - The benefit of this methodology vs. a single-threaded executor is that
    it can be a shared line, and we aren't subject to the limitations of 
    an intrinsic lock
        - the timed **tryLock** allows us to interrupt the time-budgeted activity
        
## 13.1.2 Interruptible Lock Acquisition
- Similar concept as above
    - Timed Lock Acquisition allows exclusive locks to be used in time-bounded activities
    - Interruptible lock acquisition allows exclusive locks to be used in cancellable activities
    
    
- Section 7.1.6 (Non-Interruptible Blocking) identified that acquiring an intrinsic lock is
not responsive to interruption
    - makes it HARDER to impl cancellable tasks.
    
    
### lockInterruptibly
- this method allows attempts for acquiring locks while remaining responsive to interruption
    - its inclusion in the **Lock** interface prevents the creation of another category of
    non-interruptible blocking mechanisms. 
    - makes it EASIER to impl cancellable tasks
    
    
### Interruptible Lock Acquisition vs. Normal Lock Acquisition
- the interruptible type is slightly more complicated
    - it requires 2 **try** blocks
    - (however, if the interruptible lock acquisition is capable of throwing
    **InterruptibleException**, the standard locking idiom works)
        - standard -> **try-finally**
    
    
    EXAMPLE of Interruptible Lock Acquisition
    
        
        public boolean sendOnSharedLine(String message) throw InterruptedException {
        
            lock.lockInterruptibly();
            
            try {
                return cancellableSendOnSharedLine(message);
            } finally {
                lock.unlock();
            }
        }
        
        private boolean cancellableSendOnSharedLine(String message) throws InterruptedException {
            ...
        }

- This is a refactoring of the example from the previous section
    - we use **lockInterruptibly** to impl sendOnSharedLine so that it can be called from 
    a cancellable task
        - **cancellableSendOnSharedLine**
    - the timed **tryLock** is responsive to interruption
        - this means it can be used when you need an interruptible lock acquisition in time-bound
        activities
        

## 13.1.3 Non-block-structured locking

### Intrinsic Locks
- acquire-release pairs in intrinsic locks are always *block structured*
    - this means that a lock is always released in the same basic code block in which 
    it is acquired (regardless of how control exists the block)
    - This means that the locks are AUTOMATICALLY released
        - PROS:
            - easier to code
            - less error-prone
        - CONS:
            - this is inflexible, sometimes to a fault.
            
            
### Increasing Scalability via reducing lock granularity (Ch. 11)
- lock striping allows different hash chains in a hash-based collection to use different locks

#### Over-Hand Locking/Lock Coupling
- similar approach in a linked list
    - reduce locking granularity by using a separate lock for each LINK NODE
    - allows different threads to operate independently on different sections of the list
    - the lock for a given node:
        - guards BOTH the link pointers and the data stored in that node


- during modifications/travesals to/of list
    - hold the lock on one node until we acquire the lock on the next node
    - only after we acquire the lock on the second node can we release the lock on the first
        - this has a slight contention penalty as we will be holding two locks for a very
        brief time
        
        



    