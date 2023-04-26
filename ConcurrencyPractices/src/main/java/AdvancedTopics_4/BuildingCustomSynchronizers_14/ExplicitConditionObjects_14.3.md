# 14.3 Explicit Condition Objects

- explicit **Locks** can be useful in cases where
*intrinsic locks* are too inflexible. 

- a **Lock** is a generalization of an *intrinsic lock*
- a **Condition** is a generalization of *intrinsic condition queues*


    Condition interface
    
        public interface Condition {
        
            void await() throws InterruptedException;
            
            boolean await(long time, TimeUnit unit)
                    throws InterruptedException;
                    
            long awaitNanos(long nanosTimeout) throws InterruptedException;
            
            void awaitInterruptibly();
            
            boolean awaitUntil(Date deadline) throws InterruptedException;
            
            void signal();
            
            void signalAll();
        }

### Drawbacks of Intrinsic Condition Queues
- each *intrinsic lock* can only have 1 associated *condition queue*
    - multiple threads might wait on the same *condition queue* for different
    *condition predicates*
    - the most common pattern for locking involves exposing that *condition queue* object. 
  
    
- both of the factors mentioned above make it IMPOSSIBLE to enforce **uniform waiter**
requirement for a *single notification* (**notify**)

### Value of Condition and Lock
- provide more flexible alternatives to **intrinsic lock* and *intrinsic condition queue* for
writing concurrent objects that:
    - have multiple *condition predicates*
    - can exercise more control over the visibility of the *condition queue*
    
### Condition
- A **Condition** is associated w/ a single **Lock**
    - (as we've said, just like a *condition queue* is associated w/ 
    a single *intrinsic lock*)
    - BUT, you can have as many **Condition** objects per **Lock** as you want. 
   
    
- calling **Lock.newCondition** on the associated **Lock** object will create a new
**Condition**
    - **Lock** offers richer feature set than *intrinsic lock*
    - **Condition** offers richer feature set than *intrinsic condition queues*
        - multiple *wait sets* per lock
        - interruptible and uninterruptible condition waits
        - deadline-based waiting
        - fair/non-fair queueing options
            - **Conditions** inherit the fairness setting of the associated **Lock**
        - threads are released from **Condition.await** in FIFO order. 
        
#### Lock vs. Condition

| Lock | Condition |
| --- | --- |
| wait | await | 
| notify | signal | 
| notifyAll | signalAll |

#### BUT...
Since **Condition** extends **Object** it also has **wait/notify** methods
- DONT USE THEM


### BoundedBuffer Impl using Explicit Condition Variables 
- (see Examples.ConditionBoundedBuffer)    
- **ConditionBoundedBuffer** has the same behavior as **BoundedBuffer**

#### Benefits
- more readable use of *condition queues*
    - arguably much easier to analyze classes that use multiple **Conditions** than
    one that uses a single *intrinsic condition queue* w/ multiple *condition predicates*
    

- separating 2 *condition predicates* into separate *wait sets*, **Condition** makes it easier to
meet the requirements for a *single notification*.
    - this makes it easier to use **signal** instead of **signalAll**, which gives a 
    "have your cake and eat it too" scenario (performance AND safety)
        - reduces context switches and lock acquisitions that are triggered by each
        buffer operation
    - (this is similar to the **notify** vs. **notifyAll** situation, but we enable 
    guarantees for *single/conditional notification*)
    
#### Three-Way Relationship still exists
- participants
    - *condition predicate*
    - lock
    - condition state variable
    
    
- State variables involved in *condition predicate* must be guarded by the **Lock**
- **Lock** must be held when testing the **condition predicate**
- **Lock** must be held when calling **await**, **signal/signalAll**


    NOTE: 
    
        ReentrantLock requires that Lock be hend when calling signal/signalAll, but there are Lock 
        implementations that are allowed to create Condition objects that don't have
        this requirement.
    
    
#### Choosing Condition vs. intrinsic condition queues
- Use the same logic as you would for **ReentrantLock** vs. **synchronized**
    - use **Condition** only when you specifically need its advanced featur3es
    - otherwise prefer *intrinsic queues*
    
    
    NOTE: If you have already used ReentrantLock because you needed the advanced features for
    the locking side... the choice has already been made!
    

        