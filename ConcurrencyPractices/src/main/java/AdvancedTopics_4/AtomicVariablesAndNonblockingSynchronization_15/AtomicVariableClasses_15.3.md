# 15.3 Atomic Variable Classes

### Characteristics of Atomic Variables
- finer-grained, more light weight than locks
    - limit the scope of contention to a single variable
  
  
- critical for impl'ing high performance concurrent code on multiproc systems. 
    - the FAST (uncontended) path for updating atomic variables is no slower (and usually faster) 
    than the fast path for acquiring a lock.
    - the SLOW path for atomic variables is MUCH faster than the slow path for locks due to the 
    lack of suspension and rescheduling threads.
    
    
- using algorithms based on atomic variables (instead of locks)
    - threads are more likely to be able to proceed without delay 
        - (no suspension /rescheduling)
    - threads have an easier time recovering if/when they do experience contention.
    

- atomic variable classes provide a generalization of *volatile* vars to support atomic conditional *read-modify-write*
operations

### AtomicInteger (Example)
- represents an *int*
- provides **get()/set()** methods w/ same memory semantics as **reads/writes** to a 
*volatile int*
- provides atomic methods:
    - **compareAndSet**
        - when successful, has the memory effects of both reading and writing a *volatile* variable
    - add, increment, decrement etc.
    
    
- very similar to a Counter class
    - greater scalability under contention because it can exploit underlying
    hardware support for concurrency
    
    
### 12 Atomic Variable Classes/ 4 Groups

- Groups
    - Scalars
    - Arrays
    - Field Updaters
    - Compound Variables

#### Scalar Classes
- most commonly used atomic variables
- all have CAS support
- arithmetic support for Integer and Long
- don't redefine **hashCode** or **equals**


- List of classes
    - **AtomicInteger**
    - **AtomicLong**
    - **AtomicBoolean**
    - **AtomicReference**
    
    
- these classes extend **Number**, but not **Integer/Long**
    - they can't, because primitive wrapper classes are immutable
    - atomic variable classes are MUTABLE
        - (not good candidates for hash keys)


##### Simulating Atomic Variables of other Primitives
- cast *short* or *byte* values to/from *int*
- use **floatToIntBits/doubleToLongBits** for floating point numbers

#### Array Classes
- (supports *Integer*, *Long* and *Reference*)
- arrays whose elements can be updated atomically


- Provides *volatile* access semantics to elements in the array
    - (*volatile* arrays only provide *volatile* semantics for
    the array reference, not the elements.)


## 15.3.1 Atomics as "Better Variables"
- (Refer to **OneValueCache** and **VolatileCachedFactorizer**. Section 3.4.2)
    - a *volatile* reference to an immutable object is used to update multiple state variables
    atomically
    - used **check-then-act** semantics because there was a harmless race condition due to the use of
    an immutable holder AND the single touch per code path AND, losing an occasional update was
    acceptable
       
       
- Usually **check-then-act** semantics can compromise data integrity if they aren't atomic.
    
    
    EXAMPLE 
        See Fundamentals_1.ComposingObjects_4.Classes.NumberRange
        
        - This can't be safely impl'd w/ a volatile reference to an immutable holder for
        the upper/lower bounds
        
        - It can't be safely impl'd w/ atomic integers to store the bounds. 
        
        There is an Invariant in this example that constrains BOTH numbers 
        (upper and lower bounds) 
        - they can't be updated at the same time while perserving the invariant.
        
             
### Preserving Multivariable Invariants
- We can leverage the *immutable holder* technique (Section 3.4.2 - **OneValueCache**)
to close the race condition by ATOMICALLY updating the reference to an immutable object that
holds the multiple state variables participating in the invariant.
        

    EXAMPLE: Perserving Multivariable Invariants Using CAS
    
        public class CasNumberRange {
        
            private static class IntPair {
            
                // Invariant lower <= upper
                final int lower;
                final int upper;
                ...
            }
            
            private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0,0));
            
            public int getLower() {
                return values.get().lower;
            }
            
            public int getUpper() {
                return values.get().upper;
            }
            
            public void setLower(int number) {
                while (true) {
                    IntPair oldValue = values.get();
                    if (number > oldValue.upper) {
                        throw new IllegalArgumentException("Clever Message");
                    }
                    
                    IntPair newValue = new IntPair(number, oldValue.upper);
                    if (values.compareAndSet(oldValue, newVaue)) {
                        return;
                    }
                }
            }
        }
#### Explanation
- Uses an **AtomicReference** to an **IntPair** to hold the state
    - it can use **compareAndSet** to atomically update the upper or lower bounds w/o
    the race condition(s) of NumberRange

## 15.3.2 Performance Comparison: Locks Vs. Atomic Variables
- For this example, the "next" number of the PRNG being extended
is a deterministic function of the previous number. 
    - since the PRNG must remember the previous number of part of its state this categorizes the
    invariant as a multivariable state invariant. 
    

### Code Examples
- 2 impls of a thread safe PRNG
    - **ReentrantLock** vs. **AtomicInteger**
    

- Test Driver invoked in a manner to simulate operations that have mixed portions that
operate on shared or thread-local states.  
    - each class is invoked repeatedly
    - each iteration generates a random number
        - (Fetches and modifies shared **seed** state)
        - also performs some busy-work iterations that operate strictly on 
        thread-local data
   



    CODE EXAMPLES
    
    1.) ReentrantLock Based PRNG
    
        public class ReentrantLockPseudoRandom extends PseudoRandom {
            private final Lock lock = new ReentrantLock(false);
            private int seed;
            
            ReentrantLockPsuedoRandom(int seed) {
                this.seed = seed;
            }
            
            public int nextInt(int num) {
                lock.lock();
                
                try {
                    int s = seed;
                    seed = calculateNext(s);
                    int remainder = s % num;
                    return remainder > 0 ? remainder : remainder + num;
                } finally {
                    lock.unlock();
                }
            }
        }
        
    2.) AtomicInteger based PRNG
    
        public class AtomicPseudoRandom extends PseudoRandom {
            private AtomicInteger seed;
            
            AtomicPseudoRandom(int seed) {
                this.seed = new AtomicInteger(seed);
            }
            
            public int nextInt(int num) {
                while (true) {
                    int s = seed.get();
                    int nextSeed = calculateNext(s);
                    if (seed.compareAndSet(s, nextSeed)) {
                        int remainder = s % num;
                        return remainder > 0 ? remainder : remainder + num;
                    }
                }
            }
        }
        
### Performance
- The following graphs show throughput w/ high/moderate levels of simulated operations in each iteration


![Figure 15.1](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/AtomicVariablesAndNonblockingSynchronization_15/Images/Figure15.1.png)
- NOTE: The contention levels in this chart are UNREALISTICALLY high
    - no practical application does nothing byt content for lock/atomic variable


![Figure 15.2](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/AtomicVariablesAndNonblockingSynchronization_15/Images/Figure15.2.png)

#### Observations
- ThreadLocal Compuation
    - low levels create heavy contention for both the lock and atomic variables
    - as it increases, the lock and atomic variables experience less contention
        - (because they are accessed less often by each thread)
        
        
- Comparisons
    - at high contention levels
        - locking tends to outperform atomic variables
    - at moderate (realistic) contention levels
        - atomic variables outperform locks
        
        
        NOTE:
        
            This is true in other domains
            
                Traffic lights provide better throughput for high traffic
                Rotaries provide better throughput for low traffic.
                
                The contention scheme for ethernet networks performs better at
                low traffic levels
                - Token ring does better w/ heavy traffic.
    
##### WHY? 
- Locks respond to contention by suspending threads
    - reduces CPU usage
    - reduces synchronization traffic on shared memory bus. 
    
    NOTE: (This is similar to how blocking producers in a producer/consumer design
        reduces load on consumers, letting them catch up while the producers are
        blocking) 
        

- Atomic Variables push contention management back to the caller
    - most CAS-based algos react to contention by retrying immediately
    - This is USUALLY the right approach, but at high-contention levels this
    just creates more contention. 
    
    
#### Verdict
- Atomic Variables tend tos cale better than locks because atomics handle TYPICAL contention
levels more effectively

##### LOW TO MODERATE CONTENTION
- Atomics offer better scalability

##### HIGH CONTENTION
- locks offer better contention avoidance

##### Single-CPU systems
- CAS-based algos outperform Lock-based algos because CAS ALWAYS succeeds on a single CPU system


    EXCEPTION: There is the remote case that the a thread will be preempted in the middle of
                a read-modify-write oepration.

##### ThreadLocal Curve
- The graphs above also shows an impl of **PseudoRandom** that uses **ThreadLocal** for state 
    - each thread will see its own private sequence of pseudorandom numbers
        - (instead of all threads sharing a single sequence)
    - This illustrates that it may be cheaper not to share state at all if it can be avoided.
    
    
    This is a critical demonstrate of scalability vs. contention
    
        We can IMPROVE scalability by dealing effectively w/ contention
        
        We can ACHIEVE true scalability only by ELIMINATION of contention
            