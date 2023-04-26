# 2.5 Liveness and Performance

## Synchronizing Whole Methods
- Synchronizing an entire method comes at a considerable 
performance cost, because this means that the entire method is
hoarded by a single thread. 
- the goal of synchronization is just to guard shared mutable state
variables. 
    - guarding an entire method prevents other potential structures, 
    functions, etc. from being execute`d. 
    
### Preserve Atomicity
- NOTE: don't make sync blocks TOO small
    - never divide an operation that should be atomic into more
        than one sync block.
    

### Poor Concurrency
- the number of simultaneous invocations is limited by the
application structure 

### Good Concurrency
- the number of simultaneous invocations is limited by the 
availability of processing resources. 
    - this can be achieved by excluding long-running operations
    that don't affect shared state from sync blocks.
        - doesn't block threads from accessing shared state while
        long running tasks are working

### Lock Duration
- Avoid holding locks during
    - lengthy computations
    - operations that risk not completing quickly
- EX
    - network/console I/O

### Simplicity vs. Concurrency
- Simplicity
    - EX: synchronization of entire methods
    - offers poor concurrency
- Concurrency
    - EX: synchronizing shortest possible code paths
    - slightly more complicated
- BALANCE
    - the following example provides a balance of the two
    - decomposing sync blocks "perfectly" isn't always desired
        - (Example below would be putting ++hits into sync block)
        - acquiring/releasing locks increases overhead of concurrency

        



    EX: 
    
    @ThreadSafe
    public class CachedFactorizer implements Servlet {
        @GuardedBy("this")
        private BigInteger lastNumber;
        
        @GuardedBy("this") 
        private BigInteger[] lastFactors;
        
        @GuardedBy("this")
        private long hits;
        
        @GuardedBy("this") 
        private long cacheHits;
        
        public synchronized long getHits() {
            return hits;
        }
        
        public synchronized double getCacheHitRatio() {
            return (double) cacheHits / (double) hits;
        }
        
        public void service(ServletRequest request, ServletResponse response) {
            BigInteger bigInteger = extractFrom Request(request);
            BigInteger[] factors = null;
            
            synchronized(this) {
                ++hits;
                if (bigInteger.equals(lastNumber)) {
                    ++cacheHits;
                    factors = lastFactors.clone();
                }
            }
            if (factors == null) {
                factors = factor(bigInteger);
                synchronized(this) {
                    listNumber = bigInteger;
                    lastFactoers = factors.clone();
                }
            }
            encodeIntoResponse(response, factors);
        }
    }
- NOTES on changes:
    - AtomicLong was reverted to long
        - even though AtomicLong is thread-safe for single variables, 
        - there is less benefit here, because we are using sync blocks. 
        - mix-and-match can be confusing
        
#### Premature Optimization
- depending on the situation, how the vars are accessed etc., 
some code paths will be short enough that further decomposition into smaller
sync blocks will cost more (in terms of concurrency overhead) than they will
provide a benefit 
- RULE
    - there is frequent tension between SIMPLICITY and PERFORMANCE
    - avoid compromising safety for the sake of performance