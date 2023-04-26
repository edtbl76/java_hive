# METHODS OF THREAD CLASS


    public void start()
    
                - starts thread in a separate executino path
                - then invokes run() on 'this' Thread
                
    
    public void run()
    
                - if 'this' Thread was instantiate4d using a separate 'Runnable'
                target, the run() method is invoked on that Runnable object
                
    public final void setName(String name) 
    
                - changes name of 'this' Thread. 
                
    public String getName()
    
                - gets name of 'this' Thread
                
    public final void setPriority(int priority) 
    
                - sets priority of 'this' Thread to 'priority' (between 1 and 10)
                
    public final void setDaemon(Boolean on) 
    
                - set true to denote 'this' Thread as 'daemon' thread
                
    public final void join(long millisec) 
    
                - 'this' Thread invokes join() on a second thread, causing 
                'this' Thread to block until the second thread terminates
                or 'millisec' expires
                
    public void interrupt()
    
                - interrupts 'this' Thread, causing it to continue execution
                if it was blocked for any reason
                
    public final boolean isAlive()
    
                - returns true if 'this' Thread is alive
                - (this is any time after the thread has been called by start(), 
                but before it terminates)
                
                    - RUNNABLE, WAITING or TIMED WAITING
                    
### STATIC METHODS

    public static void yield()
                
                - causes 'this' Thread to yield to any other threads of
                the same priority that are waiting to be scheduled (i.e. given
                process time) 
                
    public static void sleep(long millisec) 
    
                - causes 'this' Thread to block for at least 'millisec'
                
    public static boolean holdsLock(Object x) 
    
                - returns true if 'this' Thread holds the lock on 'x'
                
    public static Thread currentThread()
    
                - returns a reference to 'this' Thread
                (useful when debugging a multithreaded application)
                
    public static void dumpStack()
    
                - prints stack trace for 'this' Thread
                (useful when debugging a multithreaded application)
                
    
                