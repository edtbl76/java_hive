# INTERTHREAD COMMUNICATION

    - similar concept to interprocess communication
    
    - important when you develop an app where two (or more) threads will
    exchange information
    
    
### IMPORTANT METHODS

    public void wait()
    
            - causes 'this' Thread to wait until another thread invokes
            notify()
            
    
    public void notify()
    
            - wakes up a single thread that is waiting on 'this' Object's 
            monitor
            
    public void notifyAll()
    
            - wakes up all the threads that called wait() on the same object
            
    
    these methods have been implemented as 'final' methods in java.lang.Object, 
    so they are available in all the classes. 
    
    NOTE: all three of these methods can ONLY be called from within a 
    synchronized context.
    
    