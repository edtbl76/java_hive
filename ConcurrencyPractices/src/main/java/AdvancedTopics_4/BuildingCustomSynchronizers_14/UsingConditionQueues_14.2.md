# 14.2 Using Condition Queues

### PRO
- make it easier to build efficient and responsive "state-dependent classes"

### CON
- still easy to screw up
    - many rules/invariants not enforced by Java platform and/or compiler.
    
    
    If you can get away with it, it is easier and safer to build on top of
    classes like:
    
        LinkedBlockingQueue
        CountDownLatch
        Semaphore
        FutureTask

        
## 14.2.1 Condition Predicate
- Key to correct usage for *condition queues* is id'ing the *condition predicates* that the
object may wait for


- often is source of confusion around **wait** and **notify**
    - no instantiation in API
    - nothing in JVM impl or lang spec that ensures its correct use
    - not mentioned AT ALL in lang spec or Javadoc
    
    
    Without Condition Predicate, condition waits wouln't work at all. 
    - Thanks Folks. 
    
### Defined

    The condition predicate is the precondition that makes an operation 
    state-dependent to begin with.
    
- EXAMPLE
    - In a BoundedBuffer, **take** can proceed only if the buffer is non-empty
    - The *condition predicate* for **take** is 
        - "the buffer isn't empty"
    - The *condition predicate* for **put** is
        - "the buffer isn't full."
        
        
- Condition Predicates are also expressions constructed from state variables of the 
class
    - we can "test" the "buffer not empty" statement by comparing **count** to 0
    - we can "test" the "buffer not full" statement by comparing **count** to "buffer_size"
    
    
    Document "Condition Predicates" associated w/ a condition queue and the operations 
    that wait on them. 
    
    
### 3-way relationship in condition wait
- involves
    - locking
    - **wait()** method
    - condition predicate


- EXPLAINED 
    - condition predicate involves state variables
    - state variables are guarded by a lock
        - before testing the condition predicate we must hold this lock
    - the lock object and the condition queue object must be the same object
        - (This is the object on which **wait** and **notify** will be invoked)
        
        
#### BoundedBuffer Example
- buffer state is guarded by buffer lock
- buffer object is used in the condition queue
- **take** method acquires the buffer lock, then tests the condition predicate
    - if the predicate tests true, then we perform the task
        - We are able to perform the action because the lock is still held when the
        predicate is true. 
    - if the predicate isn't true, then **take** has to wait until another
    thread changes the state var so that the predicate can become true
        - we call **wait** on the buffer's intrinsic *conditon queue*
        - This only works, because we still hold the lock. 
            - This is somewhat optimistic because we are "hoping" that the predicate is
            true so that we can atomically test the predicate and perform **take**.  
        - **wait** does the following:
            - RELEASES the lock 
            - blocks the current thread
            - waits until the specified timeout (if any) expires. 
            - (The purpose for this is to allow another thread to modify the state variable
            so that the waiting thread can progress forward) 
        - thread wakes up
        - **wait** reacquires the lock 
            - resuming threads get NO SPECIAL PRIORITY in reacquiring a lock
            - contents for the thread in the same manner as any thread entering
            a **synchronized** block
        - the thread completes its action and returns
        
---

- RELATIONSHIP's STATEMENT
    - EVERY call to **wait** is implicitly associate w/ a specific *condition predicate*
    - when this occurs:
        - the caller MUST ALREADY HOLD the lock associated w/ the *condition queue*
        - that lock must also guard the state variables from which the *condition 
        predicate* is derived. 
        
## 14.2.2 Waking Up Too Soon
- When wait returns, it doesn't necessarily mean that the *condition predicate* that the thread
is waiting for has become true.


    A single intrinsic condition queue may be used with more than one condition predicate

### notifyAll
- if a thread is awakened because **notifyAll** is called, it doesn't mean that the
condition you were waiting for has become true. 


    Imagine a toaster w/ many slots for different customers (like the one's used at 
    continental breakfasts).
    
        Just because we hear a "pop", doesn't mean that it is OUR toast that has 
        become ready...
        
        It just means SOME toast is now ready. 
        
- **wait** supports *spurious* returns, which means it can resume not in response to 
ANY thread calling **notify**

    
    To beat the toaster analogy to death, this is similar to when some little kid
    runs by and pushes down all of the toaster sliders without putting in bread, so 
    the damn thing goes off, but there's actually nothing there. 
    
### what happens when control resumes...
- The condition predicate MAY be true. 
- Maybe it was true when **notifyAll** was called, but has since become untrue
    - since there is no special priority for reacquiring the lock, other threads
    may have acquired the lock and modified the state variable between
        - when our thread is awakened AND
        - when **wait** resnagged the lock.
- Maybe it was never trye
    - we don't know WHY some other thread called **notify/notifyAll**
    - could be that ANOTHER *condition predicate* associated w/ the same
    *condition queue* became true. 
    - NOTE: 
        - Multiple *condition predicates* per *condition queue* is quite common
        
        
        EXAMPLE: 
            BoundedBuffer uses the same condition queue for both condition predicates
                - not full
                - not empty
                
            This is deliberate because it is possible for BOTH of these condition predicates
            to be true at the same time. 
            
                When the "number of producers/consumers" > "buffer capacity"
                
#### Re-testing Condition Predicate
- when a thread wakes up from **wait**, we have to retest the *condition predicate*
    - (based on the reasons mentioned above)
- it is possible to wake up repeatedly w/o the *condition predicate* becoming true
    - therefore, always call **wait** from within a loop
    - test *condition predicate* for each iteration
    
  
  
### Canonical Form For State-Dependent Methods


    void stateDependentMethod() throws InterruptedException {
    
        // condition predicate must be guarded by lock
        synchronized(lock) {
            while (!conditionPredicate()) {
                lock.wait();
            }
            // Object is in desired state.
        }
    }

### Best Practices when using condition waits (Object.wait/Condition.wait)
- ALWAYS have a *condition predicate*
    - a test case for object state that MUST hold before proceeding
- ALWAYS test *condition predicate* before calling **wait**
- ALWAYS re-test *condition predicate* when returning from **wait**
- ALWAYS call **wait** in a loop
- Ensure that state variables making up *condition predicate* are guarded by
the lock associated w/ the *condition queue*
- Hold lock associated w/ *condition queue* when calling
    - **wait**
    - **notify/notifyAll**
- NEVER release the lock after checking *condition predicate* but before acting on it.

## 14.2.3 Missed Signals
- (Review Chapter 10: Liveness failures, i.e. deadlock/livelock)

### Missed Signal Definition
- A type of liveness failure that occurs when a thread must wait for a specific condition
that is already true, but fails to check the *condition predicate* before waiting
    - The thread is waiting for a notification that has already occurred. 
    
    
#### CONS/ISSUES
- The thread may end up waiting a very long time (potentially forever)
    - In order to wake up from this situation, another thread has to set the same condition, 
but this becomes a cascading problem, because each thread will end up being triggered by the
NEXT state change after theirs
    - This also leads to a conflict in ownership of the state that has been set. 
    - UGLY
    

    
- Notifications aren't STICKY
    - if Thread A notifies on a *condition queue*, and Thread B waits on the same *condition queue*
        - B doesn't wake up immediately, so another notification is required to wake B. 



- CAUSE
    - Missed Signals are typically caused by violating the best practices of the previous section
    (especially failing to test the *condition predicate* before calling **wait**)
    
    
### Best Practices (Avoiding Missed Signals)
- as long as you follow the semantics in the "Canonical Form for State-Dependent Methods"
mentioned above, you won't have issues with missed signals

## 14.2.3 Notification
- The first half of a condition wait: waiting
- The second half of a condition wait: notification.

### BoundedBuffer Example
- **take** blocks if called when buffer is empty
- **take** has to UNBLOCK when buffer becomes non-empty so that the elements can be consumed
    - when this takes place, we have to ensure that EVERY code path in which the buffer
    could become nonempty performs a notification
    

- in **BoundedBuffer** the only place to perform the previous notification is after **put**
    - when **put** successfully adds an object to the buffer, it calls **notifyAll** to let
    threads listening for a "non-empty" state know that "shit happened"
    
- notifications for "nonfull", similarly, have to occur after **take**
    - when **take** successfully consumes an object from the buffer, it calls **notifyAll** to 
    let threads listening for a "non-full" state know that "shit happened"
    
    
    Whenever there is going to be a "wait" on a condition
        - make sure that there is an entity that performs a notification when the
        condition predicate becomes true. 
        
### Notifications in Condition Queue API
- **notify**
    - causes JVM to select 1 thread waiting on that *condition queue* to wake up
- **notifyAll**
    - wakes up ALL threads waiting on that *condition queue*
    

#### Lock Management
- you must hold the lock associated w/ the *condition queue* object to call either notification
methods.
- waiting threads can't return from **wait** w/o reacquiring the lock. 


    Because of these statements: 
    
        The notifying thread should release the lock quickly to ensure that the 
        waiting threads are unblocked ASAP
        
#### notify v. notifyAll
- Since multiple threads can be waiting on the same *condition queue* for different *condition 
predicates*
    - **notify** is prone to missed signals (DANGEROUS)
    
    
- Prefer notifyAll to notify.

    
        Example: BoundedBuffer
        
            1.) condition queue is used for 2 different condition predicates
                - not full
                - not empty
                
            2.) Thread A waits on a condition queue for predicate PA
                Thread B waits on same condition queue for predicate PB
                
            3.) PB becomes true, and thread C performs notify
            
            4.) JVM wakes up ONE THREAD OF ITS OWN CHOOSING
                - it picks PA, tests the predicate (which hasn't become true) 
                - thread goes back to sleep
                
                - PB which COULD have made progress, but nobody woke him up.
- This is a "hijacked" signal, which is similar to a missed signal
    - a thread waiting for a signal that has (or should have) already occurred
    
#### When to use single notify
- ONLY one BOTH of the following conditions hold
    - Uniform Waiters
        - Only one *condition predicate* is associated w/ the condition queue
        - each thread executes the same logic when returning from **wait**
    - One-in/One-out
        - A notification on the condition variable enables at MOST one thread to proceed
     
        
    Example - Bounded Buffer
        - meets one-in/one-out requirement
        
        - does NOT meet "uniform waiters"
            - waiting threads might be waiting for either "not full" or "not empty"
            conditions.
            
    
    Example - violating one-in/one-out
        - Software Gates/Starting Gates (similar to a latch) that allow multiple threads
        to proceed violate this rule.
            
            
- Most classes DONT meet these requirements
    - in most cases, using **notifyAll** instead of **notify** is preferred.
    
#### Inefficiency of notifyAll
- even though this is SAFER, it can be less efficient
    - using **notifyAll** when only one thread in a *wait set* can progress leads to overhead. 
        - all of the other threads that can't progress will:
            - wake up in response to the **notifyAll** call
            - go right back to sleep. 
        - Overhead
            - context switches
            - contended lock acquisitions for each event that enables a single thread to make
            progress. 
            
    
    Worst Case:
    
        notifyAll's worst case can result in O(n^2) wakeups where O(n) is all that is 
        necessary.
    
- NOTE: 
    - This is another example where SAFETY concerns are a tradeoff with PERFORMANCE concerns.

### Optimizing Notifications
- **BoundedBuffer** uses a conservative mechanism to perform notifications when calling
**take** and **put**
    - every time an object is added-to/removed-from the buffer, a notification is performed
    
#### Optimization: Conditional Notification 
- We note that a thread can only be released from wait under the two specific conditions:
    - if buffer goes from EMPTY to NON-EMPTY
    - if buffer goes from FULL to NON-FULL. 
    
- *conditional notification* is an optimization that only generates a notification if a **put/take**
operation causes one of the forementioned state transitions.
    - performance optimization
    - usually increases complexity (very tricky to get right)
    - may complicate the impl of subclasses
    
    
        EXAMPLE: 
            
            Conditional Notification in BoundedBuffer.put()
            
            public synchronized void put(V value) throws InterruptedException {
                while (isFull()) {
                    wait();
                }
                
                boolean wasEmpty = isEmpty();
                doPut(value);
                
                if (wasEmpty) {
                    notifyAll();
                }
            }
            
### Optimization Best Practices
- *single notification* and *conditonal notification* are optimizations
    - it is VERY easy to introduce strange liveness problems by applying these optimizations
    incorrectly
    

    First make it right, and then make it fast - if it already isn't fast enough 

## 14.2.5 Example: A Gate Class

### Binary Latch Review
(See Test Harness in Section 5.5)
- constructed w/ an initial count of one. 
    - it has two states:
        - initial state
        - terminal state
- prevents threads from passing the starting gate until it is opened
    - once the gate is opened, ALL threads may pass through. 
    
#### CONS
- gates constructed in this manner can't be reclosed once they are opened. 

### Recloseable Gate using Wait and NotifyAll

    
    Example: 
    
        public class ThreadGate {
            // CONDITION-PREDICATE: opened-since (n) (isOpen || generation > n)
            private boolean isOpen;
            private int generation;
            
            public synchronized void close() {
                isOpen = false;
            }
            
            public synchronized void open() {
                ++generation;
                isOpen = true;
                notifyAll();
            }
            
            // BLOCKS-UNTIL: opened-since(generation on entry)
            public synchronized void await() throws Interrupted Exception {
                int arrivalGeneration = generation;
                while (!isOpen && arrivalGeneration == generation) {
                    wait();
                }
            }
        
        } 
#### Explanation
- **ThreadGate** allows gate be opened AND closed
- **await** method is provided that blocks until the gate is opened. 
    - the *condition predicate* is more complex than just testing **isOpen**
        - if N threads are waiting at the time the gate is opened, they must ALL
        be allowed to proceed. 
        - if gate is rapidly opened and closed:
            - all threads might not be released if **await** only examines **isOpen**
            - the gate may have closed again by the time that each thread:
                - receives the notification
                - reacquires the lock
                - emerges from **wait**
        - **generation** counter is incremented each time the gate is closed so that 
        threads can distinguish between this open/close pair and another one. 
            - threads are enabled to pass **await** as long as the gate was open whenb
            the thread arrived at the gate. 
- **open** method has to use **notifyAll** because the structure fails the
    one-in/one-out test for *single notification*
    
    
- ThreadGate only supports waiting for the gate to open
    - this is why the notification is only provided in **open**
    - in order to support *wait for close* operations, we would have to 
    notify in both **open** and **close** methods, making the class much more 
    complex and fragile
    
## 14.2.6 Subclass Safety Issues
- subclassing can be complicated by the use of *single/condition notification* mechanisms.

### Using Subclassing
- using subclasses at all requires that the class is structed so that subclasses can
add appropriate notification on behalf of the base class if it is subclasses in a way that
it violates one of the conditions for *single/conditional notification*


    A state-dependent class should either 
    
        fully expose (and document) its waiting and notification protocols to 
        subclasses
    
            or
        
        prevent subclasses from using them at all. 
        
    
    NOTE: This is an extension of the premise "design and document for inheritance, 
    or else prohibit it"
    
#### Requirements for Designing For Inheritance
- exposing *condition queues* and *locks*
- documenting *condition predicates* and *synchronization policy*
- (in most cases) exposing underlying state variables


- WORST CASE
    - expose state to subclasses WITHOUT documenting protocols for waiting and notiifcation.
    - considered akin to a class exposing state variables w/o documenting the invariants
    
    
#### Prohibiting Subclasses (The Easy Way... Don't Do It.) 
- make class **final**
- alternative to making class **final** is to hide the pertinent entities from 
subclasses
    - *condition queues*
    - *locks*
    - state variables
    
#### Alternative - Repairing the Damage
- if subclasses are allowed to perform *single/conditional notification" in a way that 
breaks the *uniform waiters* and *one-in/one-out* constraints, it must be able to "repair the damage"


    Example:
    
        1.) consider an unbounded blocking stack that meets the requirements for 
            single notification
            - pop() blocks if stack is empty
            - push() may ALWAYS proceed
            
        2.) subclass is added that adds a "pop two consecutive elements" method
            - we now have 2 classes of waiters (non-uniform) 
                - those waiting to pop 1 element
                - those waiting to pop 2.
                
        - if the base class properly exposes the condition queue and documents the means/protocols
        for using it, the subclass can override the push method
            - (it will use  notifyAll to preserve thread safety) 
            
            
## 14.2.7 Encapsulating Condition Queues
- Generally speaking, it is best to encapsulate the *condition queue* so that it
isn't accessible outside of the class hierarchy in which it is used. 
    - prevents callers/users from misunderstanding/misusing the protocols for
    waiting and notification.

### Pitfalls
- it is impossible to enforce *uniform waiters* requirement for *single notification* 
unless *condition queue* object is inacessible to code you don't control. 


- if alien code accidentally/mistakenly waits on your *condition queue* it may violate the
constraints (*uniform waiters* and *one-in/one-out*) required for *single/conditional notification*
    - causes *hijacked signal*
    
    
### Inconsistencies
- The most common pattern for thread-safe patterns is using an object's *intrinsic lock* to
guard its state. 
    - This is NOT consistent w/ the best practices mentioned above. 
    

- **BoundedBuffer** example
    - the buffer object acts as both the *intrinsic lock* and the *condition queue*
    - ALTERNATIVE:
        - we could easily restructure the class to use a private lock isolated from the 
        *condition queue*
        - CON:
            - This removes any support for client-side locking 

## 14.2.8 Entry and Exit Protocols
- A different characterization of the proper of use of **wait** and **notify** is described in
terms of *entry/exit protocols*
    - (See Andrew Wellings - Concurrency and Real-Time Programming in Java, 2004)
    
    
- An entry AND exit protocol should be documented for each
    - state-dependent operation
    - each operation that modifies state on which ANOTHER operation has a state dependency
  
  
- ENTRY PROTOCOL
    - each operation's *condition predicate*
- EXIT PROTOCOL
    - involves the examination of any state variables that have been modified by the operation to 
    determine if some other *condition predicate* has become true
    - responsible for notifying on the associated *condition queue* for any *condition 
    predicate* that has become true as the result of a modification of a given state variable
   
### AbstractQueuedSynchronizer [Section 14.4]
- this is the base for most state-dependent classes in *java.util.concurrent*


- this is a lib-level exploitation of the concept of *exit protocol*
    - requires synchronizer METHODS to return a value indicating whether its action might have
    unblocked one-or-more waiting threads
        - (rather than letting synchronizer CLASSES perform their own notifications)
    - this is an EXPLICIT API requirement that makes it harder to forget to notify on 
    some state transitions
        - (less missed notifications.)

