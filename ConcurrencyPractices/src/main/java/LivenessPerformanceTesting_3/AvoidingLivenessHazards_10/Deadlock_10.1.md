# 10.1 Deadlock
see Examples.DemonstrateDeadlock for an example of 
code that can deadlock...

### Dining Philosophers

    - Five Philosophers go out for Chinese Food
    - seated at a circular table
    - There are 5 chopsticks (not 5 pairs)
        - 1 chopstick is placed between each pair of diners
        
    - philosophers alternate between thinking and eating
        - each needs to acquire 2 chopsticks for long enough to 
        eat
        - then puts the chopsticks back and returns to thinking
        
    Approaches:
    
    1.) "Hungry Philospher"
    - attempts to grab BOTH adjacent chopsticks
        - if neither are available, try again after a wait 
        interval
        - if ONE isn't available, put the other one back, 
        and then  
    - lets everyone eat in a timely manner (more or less) 
    
    2.) "Starving Philosophers" - Deadlock
    - each philosopher grabs the chopstick to his left
    and waits for the chopstick to his right to be available
    before putting down his left. 
    
### "Deadly Embrace" (Deadlock)
- A thread holds a lock forever, other threads attempting to 
get that lock will block forever, waiting for it to be available.
- WORSE:
    - Thread A holds Lock L
    - Thread B holds Lock M. 
    - Thread A tries to acquire lock M and Thread B tries to
    acquire Lock L
        - Both threads block forever.
        
    
    Deadlock is a cyclic lock dependency:
    
        - Theads are nodes on a DIRECTED Graph
        - Edges representation the relationship 
            
            "Thread A is waiting for a resource held by
            Thread B". 
            
    When the graph is CYCLICAL, there is a deadlock.
    

### Database Systems
- designed to detect and recover from deadlock
    - transactions can acquire many locks
        - (locks held until txn commits)
        - not uncommon for 2 txns to deadlock
    - DETECTS deadlocked txns
        - searches "is-waiting-for" graph for cyclical relationships
    - RESOLVES deadlocked
        - selects a victim txn to abort
            - (randomly? based on weighting mechanism?)
            - releases locks, which allows other txns to move forward.
        - aborted txn can retry (or not)
            - should complete because deadlock was resolved (unless it is unlucky enough to 
            hit another deadlock)
    
### JVM
- not as helpful as db servers
- when a java thread deadlocks, those threads are permanently dead. Depending on what the threads do:
    - app stalls
    - subsystem stalls
    - crappy performance
- SOLUTION
    - abort, restart, pray
    
    
       Just because classes have potential deadlocks, doesn't mean that they will. 
       - however, if they can, the odds of it happening are when there are the most threads
       in use... you know... in production. 
       

## 10.1.1 Lock-Ordering Deadlocks

    Simple Lock-Ordering Deadlock ---- DON'T DO THIS
    
        public class LeftRightDeadlock {
            private final Object left = new Object();
            private final Object right = new Object();
            
            public void leftRight() {
                synchronized (left) {
                    synchronized (right) {
                        doSomething();
                    }
                }
            }
            
            
            public void rightLeft() {
                synchronized (right) {
                    synchronized (left) {
                        doSomethingElse();
                    }
                }
            }
        }
        
![Lock Ordering Deadlock](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/LivenessPerformanceTesting_3/AvoidingLivenessHazards_10/Images/Screen Shot 2020-12-10 at 3.52.30 PM.png)

PROBLEMS
- **leftRight** and **rightLeft** each acquire the "left" AND
"right" locks
    - if one thread calls **leftRight** and another calls
    **rightLeft** they'll deadlock.
    
WHY
- 2 threads attempt to acquire the same locks in a different order
    - this creates a cyclic dependency that results in the deadlock
    

### SOLUTION 

    A program will be free of lock-ordering deadlocks if ALL
    threads acquire the locks they need in a "FIXED GLOBAL ORDER"

- Verifying consistent lock ordering requires a GLOBAL analysis of a program's
locking behavior. 
    - inspecting code paths that acquire multiple locks individually isn't
    sufficient to expose the cyclical dependencies. 
    
## 10.1.2 Dynamic Lock Order Deadlocks

    
    Dynamic Lock-ordering Deadlock ---- Don't do this either.
    
    public void transferMoney(
            Account fromAccount, Account toAccount, DollarAmount amount) 
                throws InsufficientFundsException {
                
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }        
    }

Description of Code
- acquires locks on both **Account** objects before executing the transfer
     - ensures that balances are updated atomically
     - ensures that invariants aren't violated
        - (The **InsufficientFundsException**)
        

Where's the Gotcha?
- The hint is in the section name
- look for dynamically acquired locks...
    - the acquisition order of the locks depends on the order in which 
    the accounts are passed to **transferMoney**. 
    
    
    Deadlock Example:
    
        Thread A transfers money from Joe to Sally
        Thread B transfers money from Sally to Joe. 
        
        This is the same problem we started w/ in the previous section. 
        - the order of the locks are different, so the code can 
        deadlock. 
        
        - this can happen when the order of the locks are acquired 
        dynamically. 

### Catching The Little Bastards
- you've got to perform a global code analysis
    - (look for nested lock acquisitions like above)
    
### How the hell do you fix it?
- Right? We can't control the order of the arguments. 

#### Inducing Ordering
- when locks are dynamically acquired, we must INDUCE ordering on those
locks and then acquire them according to that ordering consistently 
throughout the application.

##### Method 1 - HashCode

     
     Inducing a Lock Ordering to prevent dynamic lock ordering 
     Deadlocks
     
        /*
            This is the tiebreaker in the rare occasion that there
            is a collision between the two hashcodes. 
        */
        private static final Object tieLock = new Object();
        
        public void transferMoney(
                    final Account fromAccount,
                    final Account toAccount,
                    final DollarAmount amount)
                throws InsufficientFundsException {
            
            class Helper {
                public void transfer() throws InsufficientFundsException {
                    if (fromAccount.getBalance().compareTo(amount) < 0) {
                        throw new InsufficientFundsException();
                    } else {
                        fromAccount.debit(amount);
                        toAccount.credit(amount);
                    }
                }                
            }
            
            int fromHash = System.identityHashCode(fromAccount);
            int toHash = System.identityHashCode(toAccount);
            
            if (fromHash < toHash) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    } 
                }
            } else if (fromHash > toHash) {
                synchronized (toAccount) {
                    synchronized (fromAccount) {
                        new Helper().transfer();
                    }
                }
            } else {
                synchronized (tieLock) {
                    synchronized (fromAccount) {
                        synchronized (toAccount) {
                            new Helper().transfer();
                        }
                    }
                }
            }
        }
Solution Explanation
- **System.identityHashCode** returns the same value that would be returned
by **Object.hashCode**
- CORNER CASE:
    - there is a very remote possibility that the two hash codes will be
    equal, leading to the possibility of a dead lock.
   
    
    Tie Breaker Lock
    - a tie breaker lock is a very simple (and easy to follow) way to handle
    dynamic lock collisions when using hashes. 
    
    By acquiring the "Tie Breaker Lock" before either of the account locks
    we ensure that only ONE thread (the tie breaker thread) performs
    the dangerous task of acquiring two locks in arbitrary order. 
    
    NOTE: 
    - This STILL eliminates a deadlock, but it must be used CONSISTENTLY.

DOWNSIDES
- verbose, slightly hard to understand

BENEFITS
- ELIMINATES the possibility of a deadlock
- hash collisions are VERY infrequent
    - little chance of concurrency bottleneck (like w/ a single program-wide lock)
    
##### Method 2
- A slightly more intelligent way to approach the previous method is to avoid the tie-breaker altogether
    - the only way to avoid the hash collision is to guarantee IMMUTABILITY and UNIQUENESS in the
    Account objects
        - we can do this by ensuring a prefix (or the whole fuckin' account no.) is unique and 
        immutable
    
## 10.1.3 Deadlocks Between Cooperating Objects
The previous examples of "multiple lock acquisition" were easy to identify because both locks
were acquired by the same method.  

### Example of Cooperating Classes
See Examples.CooperatingClassesDeadLock for Example Code
- **Taxi** = individual taxicab w/ a location and destination
- **Dispatcher** represents a fleet of taxis
    
#### HOW IT FAILS
- None of the methods EXPLICITLY call two locks
    - making it harder to ID the problem
- There are several ways that multiple locks can be acquired. 
    
    
    1.) 
        A thread gets a GPS update for the position of a given taxi
        - it calls Taxi.setLocation()  (acquires a Taxi lock to do so) 
            - updates taxi's location
            - checks if it has reached its destination 
                 - if it has, it calls Dispatcher.notifyAvailable() 
                 to get a new one -> BOOM -> we acquire the Dispatcher lock to do this. 
        
    2.) Another thread calling Dispatcher.getImage() gets the Dispatchet lock
        - it then has to get each Taxi lock (iteratively). 
        
        
        Combining these two scenarios can eventually reach a deadlock if the two locks have been
        called in reverse order by 2 different threads. 
        

#### Alien Methods, and Identifying Lock Ordering Deadlocks among Cooperating Classes. 
- alien methods are intentional abstraction barriers to prevent us from needing to know what 
happens behind that abstraction
    - This is a fundamental concept of object-oriented programming
    - However, this also prevents me from knowing if locks are being held behind that abstraction,
    which makes it more challenging for me to decide whether or not it is a good/bad idea to 
    hold a lock while calling that alien method.

    
    Alien Methods MIGHT acquire other locks (Deadlock Risk) 
    
    Alien Methods might block for forever and a day, preventing other threads from 
    acquiring the lock being held by the call to the alien method. 
    
    Calling Alien Methods with a lock held is intentionally difficult to analyze, and therefore
    risky. 
    
## 10.1.4 Open Call
An open call is the act of calling a method w/ no locks held
- Classes that rely on open calls are "more well-behaved and composable" than classes that make
calls w/ locks held. 


- Using Open Calls to Avoid deadlock == Using encapsulation to provide thread safety
    - thread-safety analysis of a program that makes effective use of encapsulation is much easier
    than of one that doesn't
    - liveness analysis of a program that relies EXCLUSIVELY on open calls makes it much easier to
    ID code paths that acquire multiple locks
        - (and therefore ensure that locks are acquired in a consistent order)
        
        
        NOTE:
            - the need to rely on open calls + careful lock ordering reflects the 
            pain points of 
                
                composing synchronized objects  <-- don't do this.
                
                vs.
                
                synchronizing composed objects  <-- what you want
                
### Example Code
(see Examples.CooperatingClassesOpenCalls )
- These are the same methods with refactorings to use open calls 
    - (This eliminates the risk of deadlock)
    
#### Reducing Synchronized Methods to Synchronized Blocks
- Fine Grained concurrency ensures we are ONLY guarding operations that involved shared state. 
    - Taxi.setLocation is no longer synchronized
        - the new impl has a synchronized block to check if the Taxi is at its destination
        - this moves the call to Dispatcher.notifyAvailable (the ALIEN METHOD) out of the lock. 
        (This is what makes it an Open Call!)
        
    - Dispatcher.getImage is no longer synchronized
        - We first create a variable to store a defensive copy
        - we copy the taxis set to the new (unsynchronized) variable
            - (Remember? Copying mutable shared data means that you are only operating against
            the copy, not the actual shared data)
        - outside of the sync block, we iterate through the defensive copy and then make the
        getLocation call to each Taxi. 
            - Each call to Taxi will acquire a lock, but this happens OUTSIDE of the dispatcher's 
            lock. 
            
##### Benefits
- reducing synchronized methods to synchronized blocks (specifically in our refactoring) results
in only a single lock required for the same operation that previously required two. 


- reducing synchronized methods to synchronized blocks decreases the scope of operations under 
which the lock is held
    - this has the added effect of improving scalability, because there are fewer conditions where
    code is locked (and therefore fewer circumstances that other threads need to wait for that lock)


#### Drawbacks
- specifically in our example
    - defensive copies of mutable data have a few disadvantages
        - the defensive copy isn't tied to the "gold" version of the data. For large sets of 
        data frequently changing, our information could be stale leading to data inconsistency
        - defensive copying takes O(N) time, which could be a performance problem 
        
- generically, reducing scope of a synchronized method has the consequence of making atomic
operations non-atomic.
    - Its important to make sure that this is ok. 
    - In our example, 
        - the synchronized METHOD of getImage() has 2 locks, but we get a time accurate view of 
        the fleet at any given time. 
        - the synchronized BLOCK version of getImage() is an open call w/ a single lock, therefore
        not prone to deadlock. However, its going to get the location of each taxi at different times
        therefore its possible that some taxis have actually reached their destination, even though
        our copy says otherwise. 
        
ALTERNATIVE:
- when loss of atomicity is an issue, you have to find a different way to make the operation atomic
    
    - use/create a Concurrent object so that only a single thread can execute the code path that
    follows the open call.
    
    - Rather than using locking to keep threads away from "mission critical shared access" code, 
    - we build protocols to prevent OTHER threads from getting in.
   
    EX: 
        Service Shutdown:
        
        - wait for in-progress operations to complete, then release resources used by the service
             1.) if I hold the lock while waiting for operations to complete - DEADLOCK 
             2.) Release Service Lock before service is shut down, would allow other threads
                    to start new operations, preventing shutdown - BROKEN FUNCTIONALITY
             3.) Hold the lock long enough to update service state to shutting down
                - any other threads trying to perform an operation on the service will see it is
                shutting down (FUNCTIONALITY PRESERVED) 
                - this allows us to wait for shutdown to complete
                    - ONLY the shutdown thread has access to the service state after the open 
                    call completes (THREAD CONFINEMENT SAFETY) 
    
### Best Practices

    Strive to use Open Calls throughout your program(s)
        - they are far easier to analyze for deadlock-freedom than those that allow
        alien methods with locks held.
        
## 10.1.5 Resource Deadlocks
- Previous examples had to do with threads deadlocking while waiting for a lock another thread
holds that it can't release. 
- This section is the same thing, but w/ resources instead of locks. 

### Resource Pools
- usually impl'd w/ semaphores to provide blocking when the pool is empty
    - (5.5.3 - Semaphores)

- The larger a resource pool is, the less likely it is to deadlock. 
    - if pool has N connections, then a deadlock requires N sets of cyclically waiting threads
    and a shitload of unlicky timing.

### Basic Resource Deadlock 

    We have 2 pooled resources
        - connection pool for Database A
        - connection pool for Database B
        
    CrazyTask requires a connections to both DBs
        - resources aren't always requested in same order (SAD FACE)
        
            If Thread A is holding a connection to DB1, while waiting for connection to DB2
            & Thread B is holding a connectino to DB2, while waiting for connection to DB1
            
                - Shit outta luck DEADLOCK.
                

### Thread-Starvation Deadlock
- This can occur when a task running in a single-threaded **Executor** submits a task and waits for
its result
    - The submitting task will wait forever, blocking itself and every other enqueued task waiting
    for that **Executor**
        - (The task it has submitted has been enqueued in the same "line", so it is impossible for
        it to ever be executed.)
        
    
    The Primary Source of Thread-Starvation based deadlocks are when tasks wait for the result of
    other tasks. 
    
    Also: 
        - Bounded Pools and Interdependent Tasks Don't Mix Well