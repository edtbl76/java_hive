# 2.2 Atomicity

    EX: 
    
        @NotThreadSafe
        public class UnsafeCountingFactorizer implements Servlet {
            private long count = 0;
            
            public long getCount() {
                return count;
            }
            
            public void service(ServletRequest request, ServletResponse response) {
                BigInteger bi = extractFromRequest(request);
                BigInteger[] factors = factor(bi);
                +count;
                encodeIntoResponse(response, factors);
            }
        }
    
- This isn't threadsafe
- incr/decr operators aren't atomic. They are examples of
read-modify-write operations

### Read-Modify-Write
- read-modify-write is a type of operation in which 
    - the resulting state is derived from the previous state. 
- they are "short hand" for a sequence of three discrete operations
    - fetch current value
    - modify it
    - write new value back
- NOTE: Read-Modify-Write operations are NOT atomic
    - failure to synchronize these types of operations can result in 
    LOST UPDATES (a form of race condition)

## 2.2.1. Race Conditions
- A race condition occurs when the correctness of a computation depends on 
the relative timing or interleaving of multiple threads b¥ the runtime
    - i.e. "getting the right answer relies on lucky timing"
    
### Check-Then-Act
- Type of compound operation that is most susceptible to a race condition 
    - "check" (observe some state to be true)
    - "act" (take action based on the observation)
- potentially "stale" observation is used to make a decision on what to do
next
    - in a concurrent system where the individual operations of the compound operation are executed on 
    different threads it is possible for the state of the system to change before the action is taken.
- possible problems as a result
    - unexpected exception
    - overwritten data
    - file corruption
    
### Race Condition vs. Data Race
- data race arises when synchronization isn't use to coordinate ALL access to a shared nonfinal field
    - this can happen when (in the case of 2 unsynchronized threads)
        - a thread writes a variable that might next be read by another thread
        - (or) reads a variable that might have last been written by another thread
    
## 2.2.2 Race Conditions in Lazy Initialization
- Lazy Init
    - defers initialization of an object until it is actually needed.
    - ensures that initialization only occurs once. 
    
    
    EX:
        // Don't Do This. (Race condition w/ Lazy Init) 
        
        @NotThreadSafe
        public class LazyInitRace {
            private ExpensiveObject instance = null;
            
            public ExpensiveObject getInstance() {
                if (instance == null) {
                    instance = new ExpensiveObject();
                }
                return instance;
            }
        }
        
## 2.2.3 Compound Actions
- A compound action is a sequence of atomic operations
- execution of compound actions in concurrent scenarios is susceptible to race conditions.
- EXAMPLES
    - check-then-act
    - read-modify-write

### Atomic Operations
- an operation that is "indivisible" with respect to all operations (including itself) that operate
on the same state


    EX:
    
        @ThreadSafe
        public class CountingFactorizer implements Servlet {
            private final AtomicLong count = new AtomicLong(0);
            
            public long getCount() {
                return count.get();
            }
            
            public void service(ServletRequest request, ServletResponse response) {
                BigInteger bigInteger = extractFromRequest(request);
                BigInteger[] factors = factor(bigInteger);
                count.incrementAndGet();
                encodeIntoResponse(response, factors);
            }
        }
        
- previous example solves the race condition by using atomic variables
    - java.util.concurrent.atomic package contains "atomic variable" classes
        - using atomic var classes ensures that all actions that access the state managed by that var
        are atomic

### Stateless Class
- When a SINGLE element of state is added to a stateless class, the class will be thread-safe IF
the state is entirely managed by a thread-safe object

