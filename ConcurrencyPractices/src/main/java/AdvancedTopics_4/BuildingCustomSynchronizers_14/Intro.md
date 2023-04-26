# Chapter 14: Building Custom Synchronizers

## State Dependent Classes
- (classes having *state-based preconditions*)
    - **FutureTask**
    - **Semaphore**
    - **BlockingQueue**


- Characteristics
    - can't remove an item from an empty queue
    - can't get the result of an incomplete task
    
    
    In order for certain operations to proceed, you have to wait for a queue to reach 
    some state that meets the conditions of the operations invariant to move forward. 
    
    
### Constructing State Dependent Classes
- The easiest approach is to build on top of an existing state-dependent lib class. 
    - EX: ValueLatch on top of CountDownLatch 
    - This approach only works if the existing class provides the functionality you need

- An alternative approach is to create synchronizers from low-level mechanisms exposed
by the lang/libs
    - intrinsic *condition queues*
    - explicit **Condition** objects
    - **AbstractQueuedSynchronizer** framework