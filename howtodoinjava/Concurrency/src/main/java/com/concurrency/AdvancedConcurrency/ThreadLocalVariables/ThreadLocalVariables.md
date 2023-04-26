# ThreadLocal
- When you create a thread that implements Runnable interface, and THEN start various 
"Thread" objects using the same "Runnable" objec
    - ALL threads share the SAME attributes that are defined in the Runnable object. 
    
    - if you change an attribute in one thread, ALL threads are affected by this change, and
    will see the changed value. 
    
    - sometimes this is desirable
        - i.e. inc/dec the same counter variable
        
    - other times, we want a thread to work on its OWN data. 
    
## WHEN TO USE
ThreadLocal variable is often used for unique identifiers (i.e. something like a transaction id)
- this allows a Controlling application to get this unique information from the thread 

## ThreadLocal class

    
    public class ThreadLocal<T> extends Object {...}
    
 A ThreadLocal variable differs from a non-local variable 
- each thread that access a ThreadLocal variable (via get()/set()) has its OWN
    INITIALIZED COPY OF THE VARIABLE. 
- instances are usually PRIVATE STATIC fields in classes that want to associate some sort
of state w/ the thread (i.e. USER ID, TRANSACTION ID,e tc.)

### THREAD LOCAL METHODS
get()
- returns value in currentThread's copy of THIS thread-local var

initialValue()
- returns currentThread's "initial value" for THIS thread-local var

remove()
- removes currentThread's value for THIS thread-local var

set(T value)
- sets currentThread's copy of THIS thread-local var to the specified 'value'
