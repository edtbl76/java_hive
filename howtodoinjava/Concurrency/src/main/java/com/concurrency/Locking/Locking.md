# LOCKING

## SYNCHRONIZED 
- a block of code that can only be executed by one thread at a time. 
- since Java supports multiple threads being executed concurrently, it is  possible for 
two or more threads to access the same fields or objects at the same time. 

- SYNCHRONIZATION
    - the process which keeps concurrent threads in sync
    - avoids memory consistence errors due to inconsistent views of shared memory. 
    - when a method is declared as SYNCHRONIZED
        - the thread holds a MONITOR (aka LOCK OBJECT) for that method's object. 
        - id ANOTHER thread is executing the SYNCHRONIZED method, the thread is
        blocked until the original thread releases the MONITOR
        
## OBJECT LEVEL LOCKING
- mechanism used to synchronize NON-STATIC METHOD/CODE-BLOCK
    - only ONE thread will be able to execute the code block on a given instance of
    the class. 

- USE CASE: to make INSTANCE LEVEL DATA THREAD SAFE

    
    EX1:
    
        public class DemoClass {
            public synchronized void demoMethod() {
                // code to be thread safe
            }
        }
        
    EX2:
    
        public class DemoClass {
            public void demoMethod() {
                synchronized(this) {
                    //thread safe code
                }
            }
        }
        
    EX3:
    
        public class DemoClass {
            private final Object lock = new Object();
            public void demoMethod() {
                synchronized(lock) {
                    // other thread safe code
                }
            }
        }
        
## CLASS LEVEL LOCK
- this prevents multiple threads from entering SYNCHRONIZED block in any of all available
instance of that class at runtime. 
- this means that only one thread will be able to execute ONE INSTANCE of the demoMethod()
at any given time.
    - all other instances will be locked from creating threads.
    
- use case: to make STATIC DATA THREAD SAFE

    
    EX1:
        
        public class DemoClass {
            // Method is static
            public synchronized static void demoMethod() {
                // thread safe stuff
            }
        }  
        
    EX2:
    
        public class DemoClass {
            public void demoMethod() {
                // Acquire lock on .class reference
                synchronized(DemoClass.class) {
                    // thread safety
                }   
           }
        } 
        
    EX3:
    
        public class DemoClass {
            private final static Object lock = new Object();
            public void demoMethod() {
                // Lock object is static
                synchronized (lock) {
                    // other thread safe code.
                }
            }
        }
        
## OBJECT LEVEL vs. CLASS LEVEL
- SYNCHRONIZATION guarantees that NO TWO THREADS can execute a SYNCHRONIZED METHOD (which
requires the same lock) concurrently

- SYNCHRONIZED keyword
    - can only be used with METHODS and CODE BLOCKS
    - may be static, non-static or both
    - "RE-ENTRANT" in nautre
        - if a synchronized method calls ANOTHER synchronized method which 
        requires the same lock, then the current thread which is holding the lock
        can enter into that method WITHOUT ACQUIRING THE LOCK.
    
- Threads must ACQUIRE a lock when entering a SYNCHRONIZED method
- Threads must RELEASE a lock when leaving a SYNCHRONIZED method. 
    - locks are released even when thread leaves the method after exception/error
    
- throws NPE if the object used in SYNCHRONIZED block is null. 
    (i.e. if the LOCK in the above example is null)
    
- SYNCHRONIZED BLOCKS put a performance HIT on your application, because it BLOCKS
code from executing. 
    - use only when necessary.
    - often recommended only to introduce locks/blocking code where critical business logic 
    occurs
    
    - DONT FUCKING USE IT!!!! (Reactive Programming FTW!)
    
- NON-STATIC and STATIC SYNCHRONIZED METHODS can run concurrently because they lock
on different objects

- CONSTRUCTORS can't be SYNCHRONIZED
- DON'T SYNCHRONIZE on NON FINAL FIELDS on SYNCHRONIZED BLOCK
    - non-final fields may change at any time, and then different threads could be
    synchronizing on different objects (i.e. NOT F'N SYNCHRONIZED!)
    
- DO NOT use String literals because they can be referenced elsewhere in the application
due to String pool
   - causes DEADLOCK
   - Strings that are on heap (i.e. new keyword) are ok, because they don't share a
   reference w/ the String pool.
   - BEST PRACTICES
    - create new private scoped object instance OR
    - lock on shared variable itself which we want to protect. 