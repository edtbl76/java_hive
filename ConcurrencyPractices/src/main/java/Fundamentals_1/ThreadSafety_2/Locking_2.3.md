# 2.3 Locking

## Single State Variable + Thread Safety
- adding a SINGLE state variable to a stateless class can be made thread-safe by using 
a thread-safe object to manage the entire state
    - ex. java.util.concurrent.atomic
    
## Multiple State Variables

    EX:
        // Close but no cigar. 
    
        @NotThreadSafe
        public class UnsafeCachingFactorizer implements Servlet {
            private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
            private final AtomicReference<BigInteger[]> lastFactors = new AtomicReferences<BigInteger[]>();
            
            public void service(ServletRequest request, ServletResponse response) {
                BigInteger bigInteger = extractFromRequest(request);
                if (bigInteger.equals(lastNumber.get())) {
                    encodeIntoResponse(response, lastFactors.get());
                } else {
                    BigInteger[] factors = factor(bigInteger);
                    lastNumber.set(bigInteger);
                    lastFactors.set(factors);
                    encodeIntoResponse(response, factors);
                }
            }
        }
- The "Single Variable" approach doesn't always work when there are multiple variables
    - if each variable is independent
        - (This means that a change in one variable has no effect on another)   
        - as long as each variable is individually thread safe, the class will still be thread safe
    - if each variable is NOT independent
        - (This means that the value of one variable constrains the allowed value(s) of another)
        - i.e. "multiple variables participate in an invariant"
        - updating one variable requires that the other variable be updated in 
        "The Same Atomic Operation"   
- RULE: "To preserve state consistency, update related state variables in a SINGLE ATOMIC OPERATION"

## 2.3.1 Intrinsic Locks

### synchronized block
- Java built-in locking mechanism for enforcing atomicity
- a.k.a. "intrinsic lock" or "monitor lock"

#### Synchronized Method
- Synchronized method is a synchronized block that spans an entire method body
- the lock is the object on which the method is being invoked 

#### Synchronized Block structure
- 1 The "lock"
    - this is a reference to an object
    - automatically ACQUIRED by executing thread before entering sync'd block
        - the ONLY way to acquire an intrinsic lock is to enter sync'd block (or method guarded by it.)
    - automatically RELEASED by executing thread when control exits sync'd block.
        - normal code path OR thrown exception.
- 2 "Guarded code"
    - this is the code guarded by that lock
    
    
    EX:
        synchronized (lock) {
            // Access or modify shared state guarded by lock
        }

### Mutex (Mutual Exclusion Locks)
- This is a type of lock that only allows a single thread to own the lock. 
- Java intrinsic locks are mutexes
- If a thread attempts to acquire a lock that is already held by another thread
    - the attempting thread will BLOCK (wait) until the desired lock is released. 
    - if the lock is never released, the attempting thread will BLOCK forever

#### Atomicity + MUTEX
- Mutual Exclusions guard code by allowing only one thread to access the code guarded by the lock at a time
    - the sync block(s) guarded by the same lock are executed ATOMICALLY w/ respect to each other
    - this means the statements appear to execute as a single, indivisible unit.


    EX:
        // we have improved the race condition from the previous example by synchronizing the 
        // entire method. 
        //
        // We are now ThreadSafe, but we will have performance problems. Don't do this.
    
    @ThreadSafe
    public class SynchronizedFactorizer implements Servlet {
        @GuardedBy("this")
        private BigInteger lastNumber;
        @GuardedBy("this")
        private BigInteger[] lastFactors;
        
        public synchronized void service(ServletRequest request, ServletResponse response) {
            BigInteger bigInteger = extractFromRequest(request);
            if (bigInteger.equals(lastNumber)) {
                encodeIntoResponse(response, lastFactors);
            } else {
                BigInteger[] factors = factor(bigInteger);
                lastNumbers = bigInteger;
                lastFactors = factors;
                encodeIntoResponse(response, factors);
            }
        }
    }
        
- PERFORMANCE ISSUE
    - when synchronizing an entire method, a lock is placed on that method.
    - This means that all threads have to wait for the entire method to be released in order to 
    access it. 
        - for a method that has a single code path, this is unavoidable. 
        - however, for methods w/ multiple code paths, its possible that other threads won't need
        the guarded section of code, so we should try to constrain the synchronization to JUST
        the code we want to guard.
        
## 2.3.2 Reentrancy
- This is the concept whereby if a thread tries to acquire a lock that it already holds, it will succeed. 
    - Locks are ACQUIRED on a per THREAD basis 
        - (not a per INVOCATION basis)
    - NOTE: Posix threads (pthread mutex) are granted on a PER-INVOCATION BASIS.
- provides encapsulation to locking behavior
    - makes it easier to produce concurrent OOP code.
        
### Reentrancy Mechanics
- each lock is associated w/
    - acquisition count
    - owning thread
- when count is reduced to 0
    - lock is "unheld" (released) 
- when new thread acquires a previously unheld thread
    - JVM records owner
    - sets acquisition count to 1
- if same thread tries to re-acquire the lock
    - operation succeeds
    - acquisition count is incremented
- when owning thread exits sync block
    - count is decremented
    - if count == 0
        - lock released
        
        
### Example Code
- The following example would deadlock if intrinsic locks didn't permit reentrancy


    EX: 
        public class Widget {
            public synchronized void doSomething() {
                // ... 
            }
        }    
        
        public class LoggingWidget extends Widget {
            public synchronized void doSomething() {
                System.out.println(toString() + ": calling doSomething");
                super.doSomething();
            }
        }
        
