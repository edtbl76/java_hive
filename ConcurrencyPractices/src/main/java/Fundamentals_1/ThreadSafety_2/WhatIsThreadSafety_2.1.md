# 2.1 What is Thread Safety? 

"A class is thread safe if it behaves correctly when accessed from multiple
threads, regardless of the scheduling or interleaving of the execution of those
threads by the runtime environment, and with no additional synchronization or
other coordination on thepart of the calling code."

"No set of operations performed sequentially OR concurrently on instances of 
a thread-safe class can cause an instance to be in an invalid state"

NOTE: Thread safe classes ideally encapsulate required SYNCHRONIZATION so that
the clients don't need to provide their own.

## Correctness
- Thread safety is about CORRECTNESS 
- CORRECTNESS
    - means a class confirms to its specification
- Specification
    - defines INVARIANTS constraining an object's state
    - defines POSTCONDITIONS that outline the effect of the object's operations
    
## Stateless Objects are always thread safe

    @ThreadSafe
    public class StatelessFactorizer implements Servlet {
        public void service(ServletRequest request, ServletResponse response) {
            BigInteger bi = extractFromRequest(request);
            BigInteger[] factors = factor(bi);
            encodeIntoResponse(response, factors);
        }
    }
- Servlet is stateless
    - no fields of its own
    - doesn't references fields from other classes. 
- "transient" state
    - exists in context of a specific computation performed by the class
    - local variables only
        - therefore it is stored on the thread's stack
            - accessible only to THAT thread. 