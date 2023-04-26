# 3.2 Publication and Escape

## Publishing
- The process of making an object available to code outside of its current scope. 
    - EX:
        - storing a reference to it where other code can access it
        - returning it from a non-private method
        - passing it to a method in another class. 
        
        
    EX
    
        // publication of an object by storing a referecne in a public static field
        
        public static Set<Secret> knownSecretsl
        
        public void initialize() {
            knownSecrets = new HashSet<>();
        }
        
    



## Escape
- An object that is published when it should not have been. 
    - EX: 
        - the publishing of internal state that breaks object-oriented encapsulation
        - the publishing objects before they are constructed
            - breaks thread-safety
            
            
        EX:
            //  ESCAPE!
            //  This code allows internal mutable state to escape. DONT DO THIS! 
            
            class UnsafeStates {
                private String[] states = new String[] { ... };
                
                public String[] getStates() {
                    return states;
                }
            }
- In this example, callers can modify the contents of the 'states' array. 
    - the 'states' array has escaped its intended scope
        - intended 'private' state has been made public. 

### Considerations (Potential for Escaping)
- publishing "Internal State Variables" breaks encapsulation
    - broken encapsulation compromises the class invariants. 
- publishing one object EXPLICITLY, my indirectly publish others
    - in the example above, publishing the Set also publishes the secrets in the set. 
- publishing an objects also publishes any objects referred to by its non private fields
    - "any object reachable from a published object by following some chain of nonprivate field
    references and method calls has also been published"
- passing objects to an "alien method" is also considered publishing that object
    - ALIEN METHOD
        - "methods that aren't fully defined by a given class"
            - methods in other classes
            - overridable methods (not private or final)
        - Alien Methods are "black boxes". We don't know what code will be invoked, so it might retain
         a reference that might later be used from another thread
        - NOTE: this is one of the most compelling reasons to observe proper encapsulation
- publishing an inner class instance allows "this" reference to escape

    
    EX: 
    
        public class ThisEscape {
            public ThisEscape(EventSource eventSource) {
                source.registerListener(
                    new EventListener() {
                        public void onEvent(Event event) {
                            doTheThing(event);
                        }
                    }
                );
            }
        }
- This example demonstrates how "this" references can escape during construction
    - when the inner EventListener instance is published
    - so is the enclosing ThisEscape instance. 
        
## 3.2.1 Safe Construction Practices

## Not Properly Constructed
(Refer to ThisEscape example above)
- Objects are ONLY in predictable states when the constructor completes its task. 
    - if "this" reference escapes during construction, the object is considered "Not Properly Constructed"
    - this is true "even if the publication is the last statement in the constructor"
        - (i.e. it ain't over til' it's over)
- "the this references should not escape from the THREAD until after the constructor returns"
    - "the this reference can be stored somewhere by the constructor so long as it is not 'used' by
    another thread until after construction"
    
### Common Mistakes
- 1 Starting Threads from a constructor
    - when objects create threads from constructors, the 'this' reference is usually shared with the 
    new thread
        - EXPLICITLY 
            - passing it to the constructor
        - IMPLICITLY
            - Thread or Runnable is inner class of owning object
    - new Thread can see object before construction is complete 
        - BAAAAAD
    - NOTE: 
        - CREATING new thread is OK. 
        - STARTING it is not. 
    - WORKAROUND
        - expose start()/initialize() method that allows control over starting 
        
        
    EX:
    
        //  Use FactoryMethod to prevent 'this' Reference from escaping during construction
        public class SafeListener {
            private final EventListener eventListener;
            
            private SafeListener() {
                listener = new EventListener() {
                    public void onEvent(Event event) {
                        doTheThing(event);
                    }
                };
            }
           
            public static SafeListener newInstnce(EventSource eventSource) {
                SafeListener safeListener = new SafeListener();
                eventSource.registerListener(safeListener.listener);
                return safeListener;
            }
        }