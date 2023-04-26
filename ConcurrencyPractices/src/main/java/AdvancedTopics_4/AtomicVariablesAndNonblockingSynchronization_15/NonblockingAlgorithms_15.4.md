# NonBlocking Algorithms 15.4

### Locking Algorithms and Liveness Failures
- Certain phenomenon can delay a thread that is holding a lock in such a manner that no other
thread will make progress
    - EX:
        - blocking I/O
        - page fault
        - other
        

### Characteristics 
- **NonBlocking**
    - an algo whereby the failure/suspension of any thread can't cause the failure/suspension of
    another thread
    - (This is a form of *failure isolation*)
- **lock-free**
    - an algorithm whereby at each step, SOME thread can progress.


- PROS
    - immune to deadlock
    - immune to priority inversion
- CONS
    - repeated retries can lead to starvation and/or livelock
    
    
#### CAS Algos
- CAS can be both *nonblocking* and *lock-free* if:
    - the CAS algo is used EXCLUSIVELY for thread coordination
    - it is constructed correctly
  
    
- uncontended CAS always succeeds
- contended CAS always results in at least one "winner" that can rpogress. 
    - *lock-free*
    
### Use Cases for GOOD NBAs
- common data structures
    - stacks
    - queues
    - priority queues
    - hash tables
  
    
- NOT FOR THE MEEK. (they're easy to fuck up)
    - considerably more complicated than lock-based equivalents
    
    
- some work is done speculatively and may need to be redone
    - This is the primary principle of *optimistic locking*
    
    
#### Best Practices
- figuring out how to limit the scope of atomic changes to a SINGLE variable while also 
maintaining data consistency

##### Linked Collection Classes
- (Ex: queues)
- expressing state transformations as changes to a single link
- use **AtomicReference** to represent each link that has to be updated atomically

---
## 15.4.1 NonBlocking Stack

### Stacks
- simplest linked data structure
    - each element refers to only one other element
    - each element is referred to by only ONE object reference
    
    
### Code Example
- (See Examples.ConcurrentStack) 
- aka "Treiber's Algorithm" - 1986

#### Explanation
- Stack constructed from a linked list of **Node** elements
    - root is at the **top**
    - each element contains a value and the link to the
    next element.
    
    
- **push()**
    - prepares a new link node
    - *next* refers to the current **top** of the stack
    - uses CAS to attempt to install the new link at the
    top of the stack
        - if same node is at the top of the stack
            - CAS succeeds
        - else
            - some other thread modified it
            - CAS fails and **push** updates the
            new node based on the CURRENT state
            - (and then tries again)
    - regardless of attempt, stack is in consistent
    state after the CAS
    
    
### Thread Safety of NBAs provided by compareAndSet
- **compareAndSet** provides
    - atomicity
    - visibility guarantees
    - (just like locking does, but without having to block)
    
    
- state modifications to stack
    - done through **compareAndSet** call
        - analogous to memory effects of a *volatile* write
  
    
- examining state 
    - calls **AtomicReference.get**
        - memory effects of *volatile* read
    
    
#### Result
- changes made by one thread are safely published to any other thread that
examines the state of the list
- the stack's backing linked list is modified w/ **compareAndSet**
    - either updates **top** reference 
    - (or) fails when it detects collision/interference from another thread.
    
---
## 15.4.2 A Nonblocking LinkedList


### Secret to NBAs (Refreshed)
- the trick to building NBA's is to "limit the scope of atomic changes to a single variable"
    - Easier for simple counters (and for simple datastructures like a stack)
    - Harder for complicated/more sophisticated data structures
        - queues, hashtables, trees.
        
### NonBuilding Linked Queue (vs. Stack)
- must support "fast access" to both the head and tail of the data structure
    - maintains separate pointers for both the head and tail
    - 2 pointers refer to the "tail" node
        - the **next** pointer of the "current" last element
        - the **tail** pointer
        
#### Challenges
- 2 tail pointers must be updated atomically in order to insert new elements
    - separate CAS operations are required to update the 2 pointers
        - if first succeeds/second fails
            - structure is left in an inconsistent state
        - if both succeed
            - it's possible that another thread could access the queue between the first/second
            CAS operations.
            
### Building a NonBlocking Linked Queue

#### TRICK 1: Ensure Data Structure is always in a consistent state
- especially in the middle of multi-step updates
    
    
    EXAMPLE
        Thread A is in the middle of a multi-step update
        
        Thread B arrives and wants to apply its update.
        - examines queue state and determines that the multi-step update
        isn't complete
        - repeatedly re-examines queue state
        
        Thread A finishes
        
        Thread B sees that the queue is no longer in the middle of a 
        multi-step update so it can begin to apply its own update. 
        
- PROBLEM:
    - if a thread fails in the middle of an update, the update will never be completed, which 
    effectively results in a deadlock
        - no other thread will be able to access the queue at all 
        - this violates both main properties of NBAs
            - the failure/suspension of one thread DOES cause the failure/suspension of other threads
                - (Therefore it is not *nonblocking*)
            - since NO thread can make progress, it isn't *lock-free* 

#### TRICK 2: Ensure that threads can progress 

    EXAMPLE
    
        Thread A is in the middle of a multi-step update
        
        Thread B arrives and wants to apply its update.
                - examines queue state and determines that the multi-step update
                isn't complete
                - repeatedly re-examines queue state
                
        <DIFFERENT>
        - Thread A FAILS (or is just really friggin' slow)
        
        There must be enough information stored in the data structure for Thread B
         (or another arriving thread) to FINISH THE UPDATE FOR Thread A    
        
- NOTE:
    - QUIESCENT state refers to a structure in a normal, consistent state
    - INTERMEDIATE state refers to a structure that is mid-update, and therefore inconsistent
        
- There are two advantages to this:
    - this provides the failure isolation from a failing thread. 
        - if A fails for some reason, other threads can give it an "assist".
    - this allows other threads to progress. 
        - if A is just REALLY slow, other threads allow work to progress in a more
        timely manner
        - if/when A completes its work, it will find it has already been completed. 
           
        
### Code Example    
(See Examples.LinkedQueue)
- This is the Michael and Scott nonblocking linked-queue
algorithm

#### Explanation
- uses a *sentinel*
    - a dummy node provided to represent an empty queue
        - (common in many queue algos)
    - **head/tail** pointers are init'd to refer to the sentinel
    

- **tail** pointer
    - points to *sentinel* in an EMPTY state
    - points to last element in queue if the structure is in QUIESCENT state
    - points to second-to-last element in queue if the structure is in an INTERMEDIATE state
    

- INSERTION steps (updating 2 pointers)
    - update **next** pointer of the current last element
        - links new node to the end of the list
    - update **tail** to point to the new last element.

##### Start (Quiescent)
![Figure 15.3](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/AtomicVariablesAndNonblockingSynchronization_15/Images/Figure15.3.png)
- This diagram shows the structure w/ 2 elements in a pre-transition quiescent state

##### Intermediate Step
![Figure 15.4](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/AtomicVariablesAndNonblockingSynchronization_15/Images/Figure15.4.png)
- This diagram shows the structure in an intermediate state after updating the **next** pointer of
the last element to point at the new node (rather than *null*)

##### End (Quiescent)
![Figure 15.5](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/AtomicVariablesAndNonblockingSynchronization_15/Images/Figure15.5.png)
- This diagram shows the structure has returned to a quiescent state after updating the **tail** point
to complete the insertion of a new node.

#### Observations
- The state of the queue can be determined by examining the state of **tail.next**
    - If **tail.next** is *null*
        - the queue is in a QUIESCENT state.
    - if **tail.next** is non-null
        - the queue is in an INTERMEDIATE state
        
        
- The state of the queue can be "finished/advanced" by advancing the **tail** pointer by one node
    - (moves it from intermediate -> quiescent)
    

- The CAS could fail if 2 threads attempt to insert a new element at the same time
    - This happens in "step C" of the code example, where we **compareAndSet** the new node. 
    - This is HARMLESS
        - The current thread will reload the original **tail** pointer and retry
    - Technically this may take a few attempts if there is high contention, but this is
    the "retry" effect of CAS/optimistic algos.
    
    
- EVENTUALLY the CAS will succeed.
    - NOTE: Step D in the code is considered "cleanup". Advancing the **tail** pointer is 
    going to happen by the original thread or some other thread based on the genius of the
    Michael and Scott algo. 
    - even if the inserting thread FAILS on step D, some other thread will come along and 
    finish the job.... again due to the genius of the Michael and Scott algo. 
        - this works because the first step of any thread is to examine the state
        of the structure. 
            - if a thread finds the structure in an INTERMEDIATE state, it advances the
            **tail** pointer.
            
            
        NOTE: It is possible for the "cleanup" step to occur multiple times. 
        

## 15.4.3 Atomic Field Updates

### ConcurrentLinkedQueue + Michael/Scott
- The Code Example (Examples.LinkedQueue) demonstrates the algo uses in 
**ConcurrentLinkedQueue**
    - different impl 
        - uses an ordinary *volatile* reference instead of an **AtomicReference**
        - (See below for reasons... this is an optimization to reduce the cost
        of inserts)
        
        
        Example : Atomic Field Updater in ConcurrentLinkedQueue
        
            private class Node<E> {
                private final E item;
                private volatile Node<E> next;
                
                public Node(E item) {
                    this.item = item;
                }
            } 
            
            private static AtomicReferenceFieldUpdater<Node, Node> nextUpdater = 
                AtomicReferenceFieldUpdater.newUdapter(
                    Node.class, Node.class, "next");
                    

### Atomic Field Updater Classes
- available in **Integer**, **Long** and **Reference**
- provide a *reflection-based* view of an existing *volatile* field
    - this allows CAS to be used on existing *volatile* fields
    

- created using **newUpdater** factory
    - (they don't have a constructor)
    - specify class and field name
    - not tied to a specific instance of the target class
        - one field updater class can be used to update the target field for any instance of the
        target class
        
#### Atomicity
- guarantees for updater classes are weaker than for "standard" atomic classes
    - we can't guarantee that the underlying fields won't be modified directly
    - **compareAndSet** and math methods only guarantee atomicity when other threads use
    the field updaters. 
    
    
### Explanation of Code Example (Above)
- updates to **Node.next** fields are applied via **nextUpdater.compareAndSet**
    - (done for performance reasons)
    
    
    For FREQUENTLY allocated SHORT-LIVED objects like queue link nodes
        - we can eliminate the creation (expensive) of **AtomicReference** 
        for each Node
        - (this is significant enough to provide to reduce the cost of insertion 
        operations)
        
    In MOST other situations
        - Atomic variables perform just fine
        
        
### Use Cases for Atomic Field Updaters
- 1.) As we saw... a performance optimization 
- 2.) to perform atomic updates while perserving the SERIALIZED FORM of an existing class. 

## 15.4.4 ABA Problem

### Definition
- common anomaly that occurs from naive use of **compare-and-swap** algos where nodes can be recycled
    - (primarily in environments w/o garbage collection)
    
#### Example Scenario
- CAS asks "is value of V still A?"
    - if True, proceeds w/ update. 
    - (usually OK)
    
- In some algorithms, changing V from A to B and back to A is a change that requires some
step to be repeated/performed.
    - INTENDED query => "Has value of V changed since I last observed it to be A"
        - (instead of "is value of V still A?")
        
### How/When/Where this occurs
- in algos that do their own memory management for link node objects
    - When the head of a list still refers to a previously observed node, this isn't enough
    to imply that the value hasn't been altered. 
    
    
### Best Solutions
1. Let the damned GC manage link nodes for you! :)
2. If that's not possible...
- Update a PAIR of values instead of just the value of a reference
    - *reference*
    - *version number*
    
#### Versioning
- Versioning makes it easier for examinations to determine that a change has occurred
    - even though the value is the same, the version number is different. 
    
### Atomic Classes w/ Versioning
- Classes that provide atomic conditional update on a PAIR of variables
    - (reference and version number)
    - **AtomicStampedReference**
        - updates an object reference INTEGER pair
        - allows "versioned" references that are immune to the ABA problem
            - (Theoretically the counter could wrap)
    - **AtomicMarkedReference**
        - updates an object reference BOOLEAN pair
        - used by some algos to allow a node REMAIN in a list while being marked as deleted
        
        
#### CAS2/CASX
- double-wide CAS operation provided by many processors
- can operate on a "pointer-integer pair"
- In theory this could make **AtomicStampedReference** and **AtomicMarkedReference** operations very
efficient.

     