# 3.4 Immutability

Immutable objects are always thread safe

## Immutable Objects
- an immutable object is one whose state can't be changed after construction
- they are inherently thread-safe
    - invariants are established by the constructor
- they are simple
    - they can only be in ONE state
- they are SAFER
    - immutable objects do not incur the same risks as mutable objects when being passed to 
    untrusted code. 
    - safe to share and publish FREELY. 
    
### Immutability
- An object is immutable if:
    - its state cannot be modified after construction
    - ALL of its fields are final. 
    - AND
    - it is PROPERLY CONSTRUCTED
        - "this" reference does/can not escape during construction
- as long as these invariants are preserved:
    - it is possible for immutable objects to use mutable objects internally to manage their state


    EX:
        
        // Immutable class built out of underlying mutable objects
        
        @Immutable
        public final class ThreeStooges {
            private final Set<String> stooges = new HashSet<>();
            
            public ThreeStooges() {
                stooges.add("Moe");
                stooges.add("Larry");
                stooges.add("Curly");
            }
            
            public boolean isStooge(String name) {
                return stooges.contains(name);
            }
        }
        
### Immutable Objects vs. Immutable Reference
- Program state can still be updated by "replacing" immutable objects w/ a new instance of that object
type that stores a NEW state. 

#### Performance Note:
- Allocation is not always the only cost concern
- immutable objects provide cost saves in terms of
    - reduced need for locking
    - reduced need for defensive copying
    - reduced impact on generational GC.
    
## 3.4.1 Final Fields
- 'final' is a keyword that supports the construction of immutable objects
    - a limited version of 'const' from C++
    - provides the possible guarantee of "Initialization Safety"
        - (allows immutable objects to be accessed/shared w/o synchronization)
- final fields can NOT be modified
    - objects that final fields refer to CAN be modified if the object is mutable
- communication tool to devs
    - "You shall not change!"
    
### Mutable Objects, w/ Final Fields
- even in mutable objects, final fields have value
    - the fields inside the mutable object can't be modified
    - this reduces the scope of possible states that the object can exist in. 
    
### private and final
- Just like private, it is considered best practices to make all fields final unless they
absolutely need to be mutable. 

## 3.4.2 Using Volatile to Publish Immutable Objects
- Using AtomicReferences only provides thread-safety when fields/values are unrelated. 
    - if multiple variables depend on one another they must be updated atomically
    - volatile variables has the same limitation
- the potential Race Conditions in accessing/modifying multiple related values can be
eliminated by storing all of the variables in an immutable objects

### Mutable vs. Immutable Holder. 
- A Mutable Holder class is one that stores multiple mutable variables to simplify the locking mechanism
when those related values need to be updated atomically
    - DISADVANTAGE: 
        - a lock is still needed to ensure synchronization of the mutable holder itself. 
    - ALTERNATIVE
        - instead of using a mutable holder, use an IMMUTABLE holder. 
        - if the variables need to be updated, then we create a new IMMUTABLE holder w/ the new
        values. 
        - this ensures that the changes are atomic as well as visible w/o reordering
            - even if a thread is working on the old holder, it will have a stale, but consistent
            view of ALL the variables. 
    
    
    EX:
    
    @Immutable
    class OneValueCache {
        private final BigInteger lastNumber;
        private final BigInteger[] lastFactors;
        
        public OneValueCache(BigInteger bigInteger, BigInteger[] factors) {
            lastNumber = bigInteger;
            lastFactors = Arrays.copyOf(factors, factors.length);
        }
        
        public BigInteger[] getFactors(BigInteger bigInteger) {
            if (lastNumber == null || !lastNumber.equals(bigInteger)) {
                throw NullPointerException();
            } else {
                return Arrays.copyOf(lastFactors, lastFactors.length);
            }
        }
    }
    

The following class uses the immutable class to store the mutable fields. 
- thread sets volatile cache to reference a new OneValueCache
    - (remember it has to be replaced, because it is immutable)
    - the cached data is IMMEDIATELY visible to other threads. 
    

    EX: 
    
    @ThreadSafe
    public class VolatileCachedFactorizer implements Servlet {
        private volatile OneValueCache cache = new OneValueCache(null, null);
        
        public void service(ServletRequest request, ServletResponse response) {
            BigInteger bigInteger = extractFromRequest(request);
            BigInteger[] factors = cache.getFactors(bigInteger);
            
            // Check Then Act
            if (factors == null) {
                factors = factor(bigInteger);
                cache = new OneValueCache(bigInteger, factors);
            }
            encodeIntoResponse(response, factors);
        }
    }
    
Cache operations won't interfere with each other
- cache is immutable
- cache field is touched once per code path. 

PRO
- This technique provides thread safety WITHOUT LOCKING!!!!
    - Immutable holder is used as a container for multiple state variables related by a single invariant
    - volatile referenced used to ensure the timely visibility of the holder. 


